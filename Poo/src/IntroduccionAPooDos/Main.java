package IntroduccionAPooDos;

import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Ingresa la cantidad de amigos a ingresar");
		int n = sc.nextInt();
		Amigos personas[] = new Amigos[n];
		guardaAmigos(personas);
		char continuar;
		do {
			menu(personas);
			continuar=sc.next().toUpperCase().charAt(0);
		}while(continuar=='S');
	}
	
	//Menu
	public static void menu(Amigos personas[]) {
		System.out.println("Elige una opcion a realizar");
		System.out.println("1-Imprimir listado de amigos\n2-Buscar un contacto por nombre");
		int opcion;
		do {
			opcion=sc.nextInt();
			if(opcion<1 || opcion>2) {
				System.out.println("Ingresa una opcion entre 1 y 2");
			}
		}while(opcion<1 || opcion>2);
		if(opcion==1) {
			imprimeAgenda(personas);
		}else {
			consultaPorNombre(personas);
		}
		System.out.println("Desea seguir en el menu (S/N)");
	}

	// Metodo para guardar objetos en el arreglo
	public static void guardaAmigos(Amigos personas[]) {
		for (int i = 0; i < personas.length; i++) {
			System.out.println("Ingresa el nombre del contacto " + (i + 1));
			sc.nextLine();
			String nombre = sc.nextLine();
			System.out.println("Ingresa el telefono del contacto " + (i + 1));
			String telefono = sc.next();
			System.out.println("Ingresa el correo del contacto");
			String correo = sc.next();
			System.out.println("Ingresa el cumpleaños del contacto " + (i + 1) + " en formato (MM/DD)");
			String fechaCumple = sc.next();
			personas[i] = new Amigos(nombre, telefono, correo, fechaCumple);
		}
	}
	
	//Metodo para imprimir listado de amigos
	public static void imprimeAgenda(Amigos personas[]) {
		System.out.println("------------Agenda------------");
		for (int i = 0; i < personas.length; i++) {
			System.out.println("Datos del contacto " + (i + 1));
			System.out.println("Nombre: " + personas[i].getNombre());
			System.out.println("Telefono: " + personas[i].getTelefono());
			System.out.println("Correo: " + personas[i].getCorreo());
			System.out.println("Fecha de cumpleaños (MM/DD): " + personas[i].getFechaCumple());
			System.out.println("------------------------");
		}
	}
	
	//Metodo para hacer consultas por nombre
	public static void consultaPorNombre(Amigos personas[]) {
		System.out.println("Ingresa el nombre del contacto a buscar");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (int i = 0; i < personas.length; i++) {
			if (personas[i].getNombre().equalsIgnoreCase(nombre)) {
				System.out.println("Datos del contacto " + nombre);
				System.out.println("Nombre: " + personas[i].getNombre());
				System.out.println("Telefono: " + personas[i].getTelefono());
				System.out.println("Correo: " + personas[i].getCorreo());
				System.out.println("Fecha de cumpleaños (MM/DD): " + personas[i].getFechaCumple());
				System.out.println("------------------------");
			}
		}

	}
}
