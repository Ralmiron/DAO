package POJO;

public class Pieza {
	private int codigoPieza; // PrimaryKey de la tabla
	private String color;
	private String nombre;
	private String descripcion;
	private int codigoDisfraz; // ForeignKey con la tabla Disfraz
	
	// Constructor con todos los campos
	public Pieza(int codigoPieza, String color, String nombre, String descripcion, int codigoDisfraz) {
		super();
		this.codigoPieza = codigoPieza;
		this.color = color;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigoDisfraz = codigoDisfraz;
	}

	// Constructor sin la PrimaryKey
	public Pieza(String color, String nombre, String descripcion, int codigoDisfraz) {
		super();
		this.color = color;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigoDisfraz = codigoDisfraz;
	}

	// Constructor sin parametros
	public Pieza() {
		super();
	}

	public int getCodigoPieza() {
		return codigoPieza;
	}

	public void setCodigoPieza(int codigoPieza) {
		this.codigoPieza = codigoPieza;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public int getCodigoDisfraz() {
		return codigoDisfraz;
	}

	public void setCodigoDisfraz(int codigoDisfraz) {
		this.codigoDisfraz = codigoDisfraz;
	}

	@Override
	public String toString() {
		return "Pieza [codigoPieza=" + codigoPieza + ", color=" + color + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", codigoDisfraz=" + codigoDisfraz + "]";
	}
}
