package IntroduccionAPoo;

import java.util.*;

public class Personal {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Persona personal[] = new Persona[5];
		pideDatos(personal);
		imprimeDatos(personal);
	}
	
	// Hacer un ciclo para guardar 5 objetos en el arreglo
	public static void pideDatos(Persona personal[]) {
		for (int i = 0; i < personal.length; i++) {
			Persona p1 = new Persona();
			System.out.println("Ingresa el nombre de la persona " + (i + 1));
			p1.setNombre(sc.next());
			System.out.println("Ingresa el genero de la persona " + (i + 1));
			p1.setGenero(sc.next().toUpperCase().charAt(0));
			System.out.println("Ingresa si la persona " + (i + 1) + " es casada");
			p1.setCasado(sc.nextBoolean());
			System.out.println("Ingresa la edad de la persona " + (i + 1));
			p1.setEdad(sc.nextInt());
			personal[i] = p1;
		}
	}
	
	// Hacer un metodo para imprimir los 5 objetos
	public static void imprimeDatos(Persona personal[]) {
		for (int i = 0; i < personal.length; i++) {
			String genero = (personal[i].getGenero() == 'M') ? "Hombre" : "Mujer";
			String estado = (personal[i].isCasado() == true) ? "Casado" : "Soltero";
			System.out.println(
					personal[i].getNombre() + " Tiene " + personal[i].getEdad() + " años, es " + genero + " " + estado);
		}
	}

}
