package PooSeis;

public class Figura {

	private String nombre, color;
	private int grosor;
	private char caracterRelleno;

	public Figura(String nombre, String color, int grosor, char caracterRelleno) {
		this.nombre = nombre;
		this.color = color;
		this.grosor = grosor;
		this.caracterRelleno=caracterRelleno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getGrosor() {
		return grosor;
	}

	public void setGrosor(int grosor) {
		this.grosor = grosor;
	}

	public char getCaracterRelleno() {
		return caracterRelleno;
	}

	public void setCaracterRelleno(char caracterRelleno) {
		this.caracterRelleno = caracterRelleno;
	}

	@Override
	public String toString() {
		return "Figura: " + nombre + "\nColor: " + color + ", Grosor: " + grosor+", Caracter de relleno "+caracterRelleno;
	}

}
