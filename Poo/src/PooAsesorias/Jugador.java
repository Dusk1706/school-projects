package PooAsesorias;

public class Jugador extends Persona {

	int numDorsal;
	String posicion;

	public Jugador(String nombre, String apellido, int edad, int num, int numDorsal, String posicion) {
		super(nombre, apellido, edad, num);
		this.numDorsal = numDorsal;
		this.posicion = posicion;
	}

	public int getNumDorsal() {
		return numDorsal;
	}

	public void setNumDorsal(int numDorsal) {
		this.numDorsal = numDorsal;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

}
