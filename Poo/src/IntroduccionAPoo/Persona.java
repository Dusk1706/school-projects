package IntroduccionAPoo;

public class Persona {

	private String nombre;
	private char genero;
	private boolean casado;
	private int edad;

	public Persona() {

	}

	public Persona(String nombre, char genero, boolean casado, int edad) {
		this.nombre = nombre;
		this.genero = genero;
		this.casado = casado;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public boolean isCasado() {
		return casado;
	}

	public void setCasado(boolean casado) {
		this.casado = casado;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

}
