package PooSeis;

public class Triangulo extends Figura {

	private int base, altura;

	public Triangulo(String nombre, String color, int grosor, int base, int altura,char caracterRelleno) {
		super(nombre, color, grosor,caracterRelleno);
		this.base = base;
		this.altura = altura;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	public double calcularArea() {
		return base*altura/2;
	}
	
	public void dibujarFigura() {
		for(int i=1;i<=base;i++) {
			if(i!=base) {
				System.out.print(new String(" ").repeat(base-i-1));
				System.out.println(new String(" "+Character.toString(getCaracterRelleno())).repeat(i));
			}else {
				System.out.println(Character.toString(getCaracterRelleno())+new String(" "+Character.toString(getCaracterRelleno())).repeat(i-1));
			}
		}
	}

	@Override
	public String toString() {
		return super.toString()+"\nBase: " + base + " Altura: " + altura;
	}
	
	

}
