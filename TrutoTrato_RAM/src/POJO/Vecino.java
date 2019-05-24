package POJO;

public class Vecino {
	private String nombreVecino; // PrimaryKey de la tabla
	private int piso;
	private int puerta;
	
	// Constructor con todos los campos
	public Vecino(String nombreVecino, int piso, int puerta) {
		super();
		this.nombreVecino = nombreVecino;
		this.piso = piso;
		this.puerta = puerta;
	}

	// Constructor sin la PrimaryKey
	public Vecino(int piso, int puerta) {
		super();
		this.piso = piso;
		this.puerta = puerta;
	}
	
	// Constructor sin parametros
	public Vecino() {
		super();
	}

	public String getNombreVecino() {
		return nombreVecino;
	}

	public void setNombreVecino(String nombreVecino) {
		this.nombreVecino = nombreVecino;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public int getPuerta() {
		return puerta;
	}

	public void setPuerta(int puerta) {
		this.puerta = puerta;
	}

	@Override
	public String toString() {
		return "Vecino [nombreVecino=" + nombreVecino + ", piso=" + piso + ", puerta=" + puerta + "]";
	}
}
