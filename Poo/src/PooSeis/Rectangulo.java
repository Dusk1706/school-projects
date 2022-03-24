package PooSeis;

public class Rectangulo extends Figura {

	private int largo, ancho;

	public Rectangulo(String nombre, String color, int grosor, int largo, int ancho, char caracterRelleno) {
		super(nombre, color, grosor, caracterRelleno);
		this.largo = largo;
		this.ancho = ancho;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getAncho() {
		return ancho;
	}

	public double calcularArea() {
		return largo * ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	public void dibujarFigura() {
		System.out.println("Dibujo\n----------------------");
		for(int i=0;i<ancho;i++) {
			System.out.println(Character.toString(getCaracterRelleno())+new String(" "+Character.toString(getCaracterRelleno())).repeat(largo-1));
		}
	}

	@Override
	public String toString() {
		return super.toString() + "\nLargo: " + largo + ", Ancho: " + ancho;
	}

}
