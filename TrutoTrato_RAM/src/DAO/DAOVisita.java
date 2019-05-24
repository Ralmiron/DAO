package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DAO.Conexion;
import POJO.Visita;

public class DAOVisita {

	private final String selectSQLall = "select chuches, nombreVecino, nombrenene from visita";
	private final String selectSQLPOk = "select COUNT(*) from visita WHERE nombreVecino = ";

	public List<Visita> read() {

		List<Visita> visitas = new ArrayList<Visita>();
		String finalSqlSelect = selectSQLall + ";";

		Conexion conexion = new Conexion();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(finalSqlSelect);
			while (rs.next()) {
				String nombreVecino = rs.getString(1);
				String nombreNene = rs.getString(2);
				String chuches = rs.getString(3);
				Visita visita = new Visita(nombreVecino, nombreNene, chuches);
				visitas.add(visita);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		return visitas;
	}

	// Per implementar l'alumne TODO
	public List<Visita> read(Visita v) {
		return null;
	}

	public boolean buscarPk(String nombreV, String nombreN) {
		Conexion conexion = new Conexion();
		Integer nombreVecino = 0;
		Integer nombreNene = 0;
		
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(selectSQLPOk.concat(""+ nombreV + " AND nombreNene =" + nombreN+""));
			while (rs.next()) {
				nombreVecino = rs.getInt(1);				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		
		if (nombreVecino > 0 || nombreNene > 0) {
			return true;
		}
		else {
			return false;
		}		
	}
	
	public void update(List<Visita> cs) {
		for (Visita v : cs) {
			update(v);
		}
	}

	public void update(Visita v) {
		String updateTable = "UPDATE visita ";
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

	public void insert(Visita v) { //REVISAR //
		String updateTable = "INSERT INTO visita (nombreVecino, nombreNene, chuches) VALUES ";

		String finalSqlUpdate = updateTable + " ('" +v.getNombreVecino() + "', '" + v.getNombreNene() + "', '" + v.getChuches() + "');";

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
	
	public void delete(Visita v) {		
		String updateTable = "DELETE FROM visita ";
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
	private String crearCondicionesUpdateSet(Visita v) {
		ArrayList<String> params = parametrosNOpk(v);
		String result = " SET " + String.join(", '", params);
		return result;
	}

	// Presuponemos que si un valor es null NO queremos buscar por �l
	private String crearCondicionesSQLWhere(Visita v) {
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
	private ArrayList<String> parametrosPk(Visita v) {
		ArrayList<String> params = new ArrayList<String>();
		params.add("nombreVecino= '" + v.getNombreVecino()+"' AND nombreNene= '" + v.getNombreNene() +"'");
		return params;
	}

	// Sin PrimaryKey
	private ArrayList<String> parametrosNOpk(Visita v) {
		ArrayList<String> params = new ArrayList<String>();
		String chuches = v.getChuches();

		if (NotNullOrEmpty(chuches)) {
			params.add("chuches= '" + chuches +"'");
		}

		return params;
	}

	private boolean HasPK(Visita v) {
		if (NotNullOrEmpty(v.getNombreVecino()) && NotNullOrEmpty(v.getNombreNene())) {
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
