package POJO;

public class Nene {
	private String nombreNene; // PrimaryKey de la tabla
	private int edad;
	private String genero;
	private int codigoDisfraz; // ForeignKey con la tabla Disfraz.
	
	// Constructor con todos los campos
	public Nene(String nombreNene, int edad, String genero, int codigoDisfraz) {
		super();
		this.nombreNene = nombreNene;
		this.edad = edad;
		this.genero = genero;
		this.codigoDisfraz = codigoDisfraz;
	}

	// Constructor sin la PrimaryKey
	public Nene(int edad, String genero, int codigoDisfraz) {
		super();
		this.edad = edad;
		this.genero = genero;
		this.codigoDisfraz = codigoDisfraz;
	}

	// Constructor sin parametros
	public Nene() {
		super();
	}

	public String getNombreNene() {
		return nombreNene;
	}

	public void setNombreNene(String nombreNene) {
		this.nombreNene = nombreNene;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getCodigoDisfraz() {
		return codigoDisfraz;
	}

	public void setCodigoDisfraz(int codigoDisfraz) {
		this.codigoDisfraz = codigoDisfraz;
	}

	@Override
	public String toString() {
		return "Nene [nombreNene=" + nombreNene + ", edad=" + edad + ", genero=" + genero + ", codigoDisfraz="
				+ codigoDisfraz + "]";
	}
}
