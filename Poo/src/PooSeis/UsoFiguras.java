package PooSeis;

public class UsoFiguras {

	public static void main(String[] args) {
		int opcion = 0;
		while (opcion != 5) {
			opcion = menu();
		}

	}

	// Menu
	public static int menu() {
		int opcion = 0;
		while (opcion != 5) {
			System.out.println("Selecciona una opcion");
			System.out.println("1. Figura Triangulo");
			System.out.println("2. Figura Cuadrado");
			System.out.println("3. Figura Rectangulo");
			System.out.println("4. Figura Circulo");
			System.out.println("5. Salir");
			opcion = Leer.datoInt();
			switch (opcion) {
			case 1:
				Triangulo triangulito=capturaTriangulo();
				System.out.println(triangulito.toString()+" Area: "+triangulito.calcularArea());
				triangulito.dibujarFigura();
				break;
			case 2:
				Cuadrado cuadradito=capturaCuadrado();
				System.out.println(cuadradito.toString()+" Area: "+cuadradito.calcularArea());
				cuadradito.dibujarFigura();
				break;
			case 3:
				Rectangulo rectangulito=capturaRectangulo();
				System.out.println(rectangulito.toString()+" Area: "+rectangulito.calcularArea());
				rectangulito.dibujarFigura();
				break;
			case 4:
				Circulo circulito=capturaCirculo();
				System.out.println(circulito.toString()+" Area: "+circulito.calcularArea());
				circulito.dibujarFigura();
				break;
			default:
				System.out.println((opcion == 5) ? "Saliste del programa" : "Opcion fuera del rango");
				break;
			}
		}
		return opcion;
	}
	
	// Metodo captura datos figura triangulo
	public static Triangulo capturaTriangulo() {
		System.out.println("Ingresa el color del triangulo: ");
		String color = Leer.dato();
		System.out.println("Ingresa el grosor del triangulo: ");
		int grosor = Leer.datoInt();
		System.out.println("Ingresa la base del triangulo: ");
		int base = Leer.datoInt();
		System.out.println("Ingresa la altura del triangulo: ");
		int altura = Leer.datoInt();
		System.out.println("Ingresa el caracter de relleno del triangulo: ");
		char caracterRelleno = Leer.datocar();
		System.out.println(caracterRelleno);
		Triangulo triangulito = new Triangulo("Triangulo", color, grosor, base, altura, caracterRelleno);
		return triangulito;
	}
	// Metodo captura datos figura cuadrado
	public static Cuadrado capturaCuadrado() {
		System.out.println("Ingresa el color del cuadrado: ");
		String color = Leer.dato();
		System.out.println("Ingresa el grosor del cuadrado: ");
		int grosor = Leer.datoInt();
		System.out.println("Ingresa el lado del cuadrado: ");
		int lado = Leer.datoInt();
		System.out.println("Ingresa el caracter de relleno del cuadrado: ");
		char caracterRelleno = Leer.datocar();
		Cuadrado cuadradito = new Cuadrado("Cuadrado", color, grosor, lado, caracterRelleno);
		return cuadradito;
	}
	// Metodo captura datos figura rectangulo
	public static Rectangulo capturaRectangulo() {
		System.out.println("Ingresa el color del Rectangulo: ");
		String color = Leer.dato();
		System.out.println("Ingresa el grosor del Rectangulo: ");
		int grosor = Leer.datoInt();
		System.out.println("Ingresa el largo del Rectangulo: ");
		int largo = Leer.datoInt();
		System.out.println("Ingresa el ancho del Rectangulo: ");
		int ancho = Leer.datoInt();
		System.out.println("Ingresa el caracter de relleno del cuadrado: ");
		char caracterRelleno = Leer.datocar();
		Rectangulo rectangulito = new Rectangulo("Rectangulo", color, grosor, largo,ancho, caracterRelleno);
		return rectangulito;
	}
	// Metodo captura datos figura circulo
	public static Circulo capturaCirculo() {
		System.out.println("Ingresa el color del Circulo: ");
		String color = Leer.dato();
		System.out.println("Ingresa el grosor del Circulo: ");
		int grosor = Leer.datoInt();
		System.out.println("Ingresa el radio del Circulo: ");
		double radio= Leer.datoInt();
		System.out.println("Ingresa el caracter de relleno del Circulo: ");
		char caracterRelleno = Leer.datocar();
		Circulo circulito = new Circulo("Circulo", color, grosor, radio, caracterRelleno);
		return circulito;
	}

}
