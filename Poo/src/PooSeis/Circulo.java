package PooSeis;

public class Circulo extends Figura {

	private double radio;

	public Circulo(String nombre, String color, int grosor, double radio, char caracterRelleno) {
		super(nombre, color, grosor, caracterRelleno);
		this.radio = radio;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public double calcularArea() {
		return Math.PI * (radio * radio);
	}

	public void dibujarFigura() {
		System.out.println(" "+Character.toString(getCaracterRelleno())+new String(" " + Character.toString(getCaracterRelleno())).repeat((int)(radio*2)-2));
		System.out.println(Character.toString(getCaracterRelleno())+new String(" " + Character.toString(getCaracterRelleno())).repeat((int)(radio*2)-1));
		System.out.println(Character.toString(getCaracterRelleno())+new String(" " + Character.toString(getCaracterRelleno())).repeat((int)(radio*2)-1));
		System.out.println(" "+Character.toString(getCaracterRelleno())+new String(" " + Character.toString(getCaracterRelleno())).repeat((int)(radio*2)-2));
	}

	@Override
	public String toString() {
		return super.toString() + "\nRadio: " + radio;
	}

}
