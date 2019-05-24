package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DAO.Conexion;
import POJO.Pieza;

public class DAOPieza {

	private final String selectSQLall = "select codigoPieza, color, nombre, descripcion, codigoDisfraz from pieza";
	private final String selectSQLPOk = "select COUNT(*) from pieza WHERE codigoPieza = ";

	public List<Pieza> read() {

		List<Pieza> piezas = new ArrayList<Pieza>();
		String finalSqlSelect = selectSQLall + ";";

		Conexion conexion = new Conexion();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(finalSqlSelect);
			while (rs.next()) {
				Integer codigoPieza = rs.getInt(1);
				String color = rs.getString(2);
				String nombre = rs.getString(3);
				String descripcion = rs.getString(4);
				Integer codigoDisfraz = rs.getInt(5);
				Pieza pieza = new Pieza(codigoPieza, color, nombre, descripcion, codigoDisfraz);
				piezas.add(pieza);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		return piezas;
	}

	// Per implementar l'alumne TODO
	public List<Pieza> read(Pieza p) {
		return null;
	}
	
	public boolean buscarPk(Integer codigo) {
		Conexion conexion = new Conexion();
		Integer codigoPieza = 0;
		
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(selectSQLPOk.concat(""+ codigo + ""));
			while (rs.next()) {
				codigoPieza = rs.getInt(1);				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		
		if (codigoPieza > 0) {
			return true;
		}
		else {
			return false;
		}		
	}

	public void update(List<Pieza> cs) {
		for (Pieza p : cs) {
			update(p);
		}
	}

	public void update(Pieza p) {
		String updateTable = "UPDATE pieza ";
		String updateSET = crearCondicionesUpdateSet(p);
		String whereSQL = crearCondicionesSQLWhere(p);

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

	public void insert(Pieza p) {
		String updateTable = "INSERT INTO pieza (codigoPieza, color, nombre, descripcion, codigoDisfraz) VALUES ";

		String finalSqlUpdate = updateTable + "("+p.getCodigoPieza() + ", '" + p.getColor() + "', '" + p.getNombre() + "', '" + p.getDescripcion() + "', " + p.getCodigoDisfraz()+");";

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

	public void delete(Pieza p) {
		String updateTable = "DELETE FROM pieza ";
		String whereSQL = crearCondicionesSQLWhere(p);
		
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
	private String crearCondicionesUpdateSet(Pieza p) {
		ArrayList<String> params = parametrosNOpk(p);
		String result = " SET " + String.join(", ", params);
		return result;
	}

	// Presuponemos que si un valor es null NO queremos buscar por �l
	private String crearCondicionesSQLWhere(Pieza p) {
		String result = "";
		ArrayList<String> params;
		if (HasPK(p)) {
			params = parametrosPk(p);
		} else {
			params = parametrosNOpk(p);
		}

		if (params.size() > 0) {
			result = " WHERE " + String.join(" AND ", params);
		}

		return result;
	}

	// Con PrimaryKey
	private ArrayList<String> parametrosPk(Pieza p) {
		ArrayList<String> params = new ArrayList<String>();
		params.add("codigoPieza= " + p.getCodigoPieza());
		return params;
	}

	// Sin PrimaryKey
	private ArrayList<String> parametrosNOpk(Pieza p) {
		ArrayList<String> params = new ArrayList<String>();
		String color = p.getColor();
		String nombre = p.getNombre();
		String descripcion = p.getDescripcion();
		Integer codigoDisfraz = p.getCodigoDisfraz();

		if (IntegerNotNullAndGreaterZero(codigoDisfraz)) {
			params.add("codigoDisfraz= " + codigoDisfraz);
		}
		if (NotNullOrEmpty(color)) {
			params.add("color= '" + color + "'");
		}
		if (NotNullOrEmpty(nombre)) {
			params.add("nombre= '" + nombre + "'");
		}
		if (NotNullOrEmpty(descripcion)) {
			params.add("descripcion= '" + descripcion + "'");
		}
		return params;
	}

	private boolean HasPK(Pieza p) {
		if (IntegerNotNullAndGreaterZero(p.getCodigoPieza())) {
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
