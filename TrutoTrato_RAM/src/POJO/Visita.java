package POJO;

public class Visita {
	private String chuches; 
	private String nombreVecino; // PrimaryKey de la tabla, viene de Vecino
	private String nombreNene; // PrimaryKey de la tabla, viene de Nene
	
	// Constructor con todos los campos
	public Visita(String nombreVecino, String nombreNene, String chuches) {
		super();
		this.chuches = chuches;
		this.nombreVecino = nombreVecino;
		this.nombreNene = nombreNene;
	}

	// Constructor sin parametros
	public Visita() {
		super();
	}

	public String getChuches() {
		return chuches;
	}

	public void setChuches(String chuches) {
		this.chuches = chuches;
	}

	public String getNombreVecino() {
		return nombreVecino;
	}

	public void setNombreVecino(String nombreVecino) {
		this.nombreVecino = nombreVecino;
	}

	public String getNombreNene() {
		return nombreNene;
	}

	public void setNombreNene(String nombreNene) {
		this.nombreNene = nombreNene;
	}

	@Override
	public String toString() {
		return "Visita [chuches=" + chuches + ", nombreVecino=" + nombreVecino + ", nombreNene=" + nombreNene + "]";
	}
}
