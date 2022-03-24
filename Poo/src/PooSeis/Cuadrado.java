package PooSeis;

public class Cuadrado extends Figura {

	private int lado;

	public Cuadrado(String nombre, String color, int grosor, int lado,char caracterRelleno) {
		super(nombre, color, grosor, caracterRelleno);
		this.lado = lado;
	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	public double calcularArea() {
		return lado*lado;
	}
	
	public void dibujarFigura() {
		System.out.println("Dibujo\n----------------------");
		for(int i=0;i<lado;i++) {
			System.out.println(Character.toString(getCaracterRelleno())+new String(" "+Character.toString(getCaracterRelleno())).repeat(lado-1));
		}
	}

	@Override
	public String toString() {
		return super.toString()+"\nLado: " + lado;
	}

}
