package POJO;

public class Disfraz {
	private int codigoDisfraz; // PrimaryKey de la tabla
	private int talla;
	private String nombre;
	private String descripcion;
	
	// Constructor con todos los campos
	public Disfraz(int codigoDisfraz, int talla, String nombre, String descripcion) {
		super();
		this.codigoDisfraz = codigoDisfraz;
		this.talla = talla;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	// Constructor sin la PrimaryKey
	public Disfraz(int talla, String nombre, String descripcion) {
		super();
		this.talla = talla;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	// Constructor sin parametros
	public Disfraz() {
		super();
	}

	public int getCodigoDisfraz() {
		return codigoDisfraz;
	}

	public void setCodigoDisfraz(int codigoDisfraz) {
		this.codigoDisfraz = codigoDisfraz;
	}

	public int getTalla() {
		return talla;
	}

	public void setTalla(int talla) {
		this.talla = talla;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Disfraz [codigoDisfraz=" + codigoDisfraz + ", talla=" + talla + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}
}
