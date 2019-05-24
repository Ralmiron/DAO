package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DAO.Conexion;
import POJO.Vecino;

public class DAOVecino {

	private final String selectSQLall = "select nombreVecino, piso, puerta from vecino";
	private final String selectSQLPOk = "select COUNT(*) from vecino WHERE nombreVecino = ";

	public List<Vecino> read() {

		List<Vecino> vecinos = new ArrayList<Vecino>();
		String finalSqlSelect = selectSQLall + ";";

		Conexion conexion = new Conexion();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(finalSqlSelect);
			while (rs.next()) {
				String nombreVecino = rs.getString(1);
				Integer piso = rs.getInt(2);
				Integer puerta = rs.getInt(3);
				Vecino vecino = new Vecino(nombreVecino, piso, puerta);
				vecinos.add(vecino);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		return vecinos;
	}

	// Per implementar l'alumne TODO
	public List<Vecino> read(Vecino v) {
		return null;
	}
	
	public boolean buscarPk(String nombre) {
		Conexion conexion = new Conexion();
		Integer nombreVecino = 0;
		
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(selectSQLPOk.concat(""+ nombre + ""));
			while (rs.next()) {
				nombreVecino = rs.getInt(1);				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		
		if (nombreVecino > 0) {
			return true;
		}
		else {
			return false;
		}		
	}

	public void update(List<Vecino> cs) {
		for (Vecino v : cs) {
			update(v);
		}
	}

	public void update(Vecino v) {
		String updateTable = "UPDATE vecino ";
		String updateSET = crearCondicionesUpdateSet(v);
		String whereSQL = crearCondicionesSQLWhere(v);

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

	public void insert(Vecino v) {
		String updateTable = "INSERT INTO vecino (nombreVecino, piso, puerta) VALUES ";

		String finalSqlUpdate = updateTable + " ('" +v.getNombreVecino() + "', " + v.getPiso() + ", " + v.getPuerta() + ");";

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
	
	public void delete(Vecino v) {		
		String updateTable = "DELETE FROM vecino ";
		String whereSQL = crearCondicionesSQLWhere(v);
		
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
	private String crearCondicionesUpdateSet(Vecino v) {
		ArrayList<String> params = parametrosNOpk(v);
		String result = " SET " + String.join(", ", params);
		return result;
	}

	// Presuponemos que si un valor es null NO queremos buscar por �l
	private String crearCondicionesSQLWhere(Vecino v) {
		String result = "";
		ArrayList<String> params;
		if (HasPK(v)) {
			params = parametrosPk(v);
		} else {
			params = parametrosNOpk(v);
		}

		if (params.size() > 0) {
			result = " WHERE " + String.join(" AND ", params);
		}

		return result;
	}

	// Con PrimaryKey
	private ArrayList<String> parametrosPk(Vecino v) {
		ArrayList<String> params = new ArrayList<String>();
		params.add("nombreVecino= '" + v.getNombreVecino()+"'");
		return params;
	}

	// Sin PrimaryKey
	private ArrayList<String> parametrosNOpk(Vecino v) {
		ArrayList<String> params = new ArrayList<String>();
		Integer piso = v.getPiso();
		Integer puerta = v.getPuerta();

		if (IntegerNotNullAndGreaterZero(piso)) {
			params.add("piso= " + piso);
		}
		if (IntegerNotNullAndGreaterZero(puerta)) {
			params.add("puerta= " + puerta);
		}
		return params;
	}

	private boolean HasPK(Vecino v) {
		if (NotNullOrEmpty(v.getNombreVecino())) {
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
