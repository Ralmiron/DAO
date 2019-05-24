package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DAO.Conexion;
import POJO.Nene;

public class DAONene {
	private final String selectSQLall = "select nombreNene, edad, genero, codigoDisfraz from nene";
	private final String selectSQLPOk = "select COUNT(*) from nene WHERE nombreNene = ";


	public List<Nene> read() {

		List<Nene> nenes = new ArrayList<Nene>();
		String finalSqlSelect = selectSQLall + ";";

		Conexion conexion = new Conexion();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(finalSqlSelect);
			while (rs.next()) {
				String nombreNene = rs.getString(1);
				Integer edad = rs.getInt(2);
				String genero = rs.getString(3);
				Integer codigoDisfraz = rs.getInt(4);
				Nene nene = new Nene(nombreNene, edad, genero, codigoDisfraz);
				nenes.add(nene);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		return nenes;
	}

	// Per implementar l'alumne TODO
	public List<Nene> read(Nene n) {
		return null;
	}
	
	
	/**
	 * Busca si existe un registro en base de datos
	 * @param nombre
	 * @return
	 */
	public boolean buscarPk(String nombre) {
		Conexion conexion = new Conexion();
		Integer nombreNene = 0;
		
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(selectSQLPOk.concat("'"+ nombre + "'"));
			while (rs.next()) {
				nombreNene = rs.getInt(1);				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		
		if (nombreNene > 0) {
			return true;
		}
		else {
			return false;
		}		
	}

		
	public void update(List<Nene> cs) {
		for (Nene n : cs) {
			update(n);
		}
	}

	public void update(Nene c) {
		String updateTable = "UPDATE nene ";
		String updateSET = crearCondicionesUpdateSet(c);
		String whereSQL = crearCondicionesSQLWhere(c);

		String finalSqlUpdate = updateTable + updateSET + whereSQL + ";";

		Conexion conexion = new Conexion();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeUpdate(finalSqlUpdate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
	}

	public void insert(Nene n) {
		String updateTable = "INSERT INTO nene (nombreNene, edad, genero, codigoDisfraz) VALUES ";

		String finalSqlUpdate = updateTable + "('"+n.getNombreNene() + "', " + n.getEdad() + ", '" + n.getGenero() + "', " + n.getCodigoDisfraz() + ");";

		Conexion conexion = new Conexion();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeUpdate(finalSqlUpdate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		
	}

	public void delete(Nene n) {
		String updateTable = "DELETE FROM nene ";
		String whereSQL = crearCondicionesSQLWhere(n);
		
		String finalSqlUpdate = updateTable + whereSQL + ";";
		
		Conexion conexion = new Conexion();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeUpdate(finalSqlUpdate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
	}

	// Presuponemos que si un valor es null NO queremos hacer SET igual a null
	private String crearCondicionesUpdateSet(Nene n) {
		ArrayList<String> params = parametrosNOpk(n);
		String result = " SET " + String.join(", ", params);
		return result;
	}

	// Presuponemos que si un valor es null NO queremos buscar por �l
	private String crearCondicionesSQLWhere(Nene n) {
		String result = "";
		ArrayList<String> params;
		if (HasPK(n)) {
			params = parametrosPk(n);
		} else {
			params = parametrosNOpk(n);
		}

		if (params.size() > 0) {
			result = " WHERE " + String.join(" AND ", params);
		}

		return result;
	}

	// Con PrimaryKey
	private ArrayList<String> parametrosPk(Nene n) {
		ArrayList<String> params = new ArrayList<String>();
		params.add("nombreNene= '" + n.getNombreNene()+"'");
		return params;
	}
	
	// Sin PrimaryKey
	private ArrayList<String> parametrosNOpk(Nene n) {
		ArrayList<String> params = new ArrayList<String>();
		Integer edad = n.getEdad();
		String genero = n.getGenero();
		Integer codigoDisfraz = n.getCodigoDisfraz();

		if (IntegerNotNullAndGreaterZero(edad)) {
			params.add("edad= " + edad);
		}
		if (IntegerNotNullAndGreaterZero(codigoDisfraz)) {
			params.add("codigoDisfraz= " + codigoDisfraz);
		}
		if (NotNullOrEmpty(genero)) {
			params.add("genero= '" + genero + "'");
		}
		return params;
	}

	private boolean HasPK(Nene n) {
		if (NotNullOrEmpty(n.getNombreNene())) {
			return true;
		}
		return false;
	}

	private boolean IntegerNotNullAndGreaterZero(Integer i) {
		if (i == null || i <= 0) {
			return false;
		}
		return true;

	}

	private boolean NotNullOrEmpty(String str) {
		if (str != null && !str.trim().isEmpty()) {
			return true;
		}
		return false;
	}
}
