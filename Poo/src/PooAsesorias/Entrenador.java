package PooAsesorias;

public class Entrenador extends Persona {
	private String annos, escuela;

	public Entrenador(String nombre, String apellido, int edad, int num, String annos, String escuela) {
		super(nombre, apellido, edad, num);
		this.annos = annos;
		this.escuela = escuela;
	}

	public String getAnnos() {
		return annos;
	}

	public void setAnnos(String annos) {
		this.annos = annos;
	}

	public String getEscuela() {
		return escuela;
	}

	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}

}
