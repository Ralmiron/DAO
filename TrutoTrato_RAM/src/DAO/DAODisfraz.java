package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DAO.Conexion;
import POJO.Disfraz;

public class DAODisfraz {

	private final String selectSQLall = "select codigoDisfraz, talla, nombre, descripcion from disfraz";
	private final String selectSQLPOk = "select COUNT(*) from disfraz WHERE codigoDisfraz = ";

	public List<Disfraz> read() {

		List<Disfraz> disfraces = new ArrayList<Disfraz>();
		String finalSqlSelect = selectSQLall + ";";

		Conexion conexion = new Conexion();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(finalSqlSelect);
			while (rs.next()) {
				Integer codigoDisfraz = rs.getInt(1);
				Integer talla = rs.getInt(2);
				String nombre = rs.getString(3);
				String descripcion = rs.getString(4);
				Disfraz disfraz = new Disfraz(codigoDisfraz, talla, nombre, descripcion);
				disfraces.add(disfraz);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		return disfraces;
	}

	// Per implementar l'alumne TODO
	public List<Disfraz> read(Disfraz v) {
		return null;
	}
	
	public boolean buscarPk(Integer codigo) {
		Conexion conexion = new Conexion();
		Integer codigoDisfraz = 0;
		
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(selectSQLPOk.concat(""+ codigo +""));
			while (rs.next()) {
				codigoDisfraz = rs.getInt(1);				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		
		if (codigoDisfraz > 0) {
			return true;
		}
		else {
			return false;
		}		
	}

	public void update(List<Disfraz> cs) {
		for (Disfraz d : cs) {
			update(d);
		}
	}

	public void update(Disfraz d) {
		String updateTable = "UPDATE disfraz ";
		String updateSET = crearCondicionesUpdateSet(d);
		String whereSQL = crearCondicionesSQLWhere(d);

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

	public void insert(Disfraz d) {
		String updateTable = "INSERT INTO disfraz (codigoDisfraz, talla, nombre, descripcion) VALUES ";

		String finalSqlUpdate = updateTable + "("+d.getCodigoDisfraz() + ", " + d.getTalla() + ", '" + d.getNombre() + "', '"+ d.getDescripcion() + "');";

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
	
	public void delete(Disfraz d) {		
		String updateTable = "DELETE FROM disfraz ";
		String whereSQL = crearCondicionesSQLWhere(d);
		
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
	private String crearCondicionesUpdateSet(Disfraz d) {
		ArrayList<String> params = parametrosNOpk(d);
		String result = " SET " + String.join(", ", params);
		return result;
	}

	// Presuponemos que si un valor es null NO queremos buscar por �l
	private String crearCondicionesSQLWhere(Disfraz d) {
		String result = "";
		ArrayList<String> params;
		if (HasPK(d)) {
			params = parametrosPk(d);
		} else {
			params = parametrosNOpk(d);
		}

		if (params.size() > 0) {
			result = " WHERE " + String.join(" AND ", params);
		}

		return result;
	}

	// Con PrimaryKey
	private ArrayList<String> parametrosPk(Disfraz d) {
		ArrayList<String> params = new ArrayList<String>();
		params.add("codigoDisfraz= " + d.getCodigoDisfraz());
		return params;
	}

	// Sin PrimaryKey
	private ArrayList<String> parametrosNOpk(Disfraz d) {
		ArrayList<String> params = new ArrayList<String>();
		String nombre = d.getNombre();
		String descripcion = d.getDescripcion();
		Integer talla = d.getTalla();

		if (NotNullOrEmpty(nombre)) {
			params.add("nombre= '" + nombre+"'");
		}
		if (IntegerNotNullAndGreaterZero(talla)) {
			params.add("talla= " + talla);
		}
		if (NotNullOrEmpty(descripcion)) {
			params.add("descripcion= '" + descripcion + "'");
		}

		return params;
	}

	private boolean HasPK(Disfraz d) {
		if (IntegerNotNullAndGreaterZero(d.getCodigoDisfraz())) {
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
