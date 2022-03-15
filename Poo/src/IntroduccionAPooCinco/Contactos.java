package IntroduccionAPooCinco;

import IntroduccionAPooCuatro.Leer;

public class Contactos {

	public static void main(String[] args) {

		Agenda contactos[] = new Agenda[10];
		int opcion = 0;
		while (opcion != 8) {
			opcion = menu(contactos);
		}

	}

	// Menu
	public static int menu(Agenda contactos[]) {
		int opcion = 0;
		while (opcion != 8) {
			System.out.println("---------------------------------------------------------\n|\t\t\tMenu\t\t\t\t|");
			System.out.println("|\t1-Añadir contacto\t\t\t\t|");
			System.out.println("|\t2-Consulta si existe el contacto\t\t|");
			System.out.println("|\t3-Lista de la agenda\t\t\t\t|");
			System.out.println("|\t4-Consulta de un contacto por nombre\t\t|");
			System.out.println("|\t5-Eliminar contacto\t\t\t\t|");
			System.out.println("|\t6-Consulta si la agenda esta llena\t\t|");
			System.out.println("|\t7-Indica cuantos contactos mas puedes meter\t|");
			System.out.println("|\t8-Salir\t\t\t\t\t\t|\n---------------------------------------------------------");
			opcion = Leer.datoInt();
			String nombre;
			switch (opcion) {
			case 1:
				capturaContacto(contactos);
				break;
			case 2:
				System.out.println("Ingresa el nombre a consultar si existe");
				nombre = Leer.dato();
				existeNombre(contactos, nombre);
				imprimeExiste(contactos, nombre);
				break;
			case 3:
				listaAgenda(contactos);
				break;
			case 4:
				System.out.println("Ingresa el nombre a consultar telefono");
				nombre = Leer.dato();
				consultaTelefono(contactos, nombre);
				break;
			case 5:
				System.out.println("Ingresa el contacto a eliminar");
				nombre = Leer.dato();
				eliminaContacto(contactos, nombre);
				break;
			case 6:
				System.out.println((agendaLlena(contactos)) ? "Agenda llena" : "Agenda con huecos libres");
				break;
			case 7:
				huecosLibres(contactos);
				break;
			default:
				System.out.println((opcion == 8) ? "Saliste del programa" : "Opcion fuera del rango");
				break;
			}
		}
		return opcion;
	}

	// Metodo 1 - Añadir un contacto
	public static void capturaContacto(Agenda contactos[]) {
		boolean flag;
		if (!agendaLlena(contactos)) {
			for (int i = 0; i < contactos.length; i++) {
				if (contactos[i] == null) {
					String nombre, telefono;
					do {
						System.out.println("Ingresa el nombre del contacto");
						nombre = Leer.dato();
						flag = existeNombre(contactos, nombre);
						if (flag)
							System.out.println("Contacto ya existente");
					} while (flag);
					System.out.println("Ingresa el telefono del contacto");
					telefono = Leer.dato();
					contactos[i] = new Agenda(nombre, telefono);
					break;
				}
			}
		} else {
			System.out.println("Agenda llena");
		}
		
	}

	// Metodo 2 - Indica si el nombre del contacto existe
	public static boolean existeNombre(Agenda contactos[], String nombre) {
		boolean flag = false;
		for (int i = 0; i < contactos.length; i++) {
			if (contactos[i] != null && contactos[i].getNombre().equalsIgnoreCase(nombre)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	// Imprime si existe o no
	public static void imprimeExiste(Agenda contactos[], String nombre) {
		if (existeNombre(contactos, nombre)) {
			System.out.println("Nombre ya existente");
		} else {
			System.out.println("Nombre no registrado");
		}
	}

	// Metodo 3 - Lista de toda la agenda
	public static void listaAgenda(Agenda contactos[]) {
		boolean flag = false;
		for (int i = 0; i < contactos.length; i++) {
			if (contactos[i] != null) {
				flag = true;
				System.out.println(contactos[i].toString());
			}
		}
		if (!flag) {
			System.out.println("Agenda vacia");
		}

	}

	// Metodo 4 - Busca un contacto por su nombre y muestra su telefono
	public static void consultaTelefono(Agenda contactos[], String nombre) {
		if (existeNombre(contactos, nombre)) {
			for (int i = 0; i < contactos.length; i++) {
				if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
					System.out.println("El numero de " + nombre + " es: " + contactos[i].getTelefono());
					break;
				}
			}
		} else {
			System.out.println("Nombre no registrado");
		}
	}

	// Metodo 5 - Elimina un contacto
	public static void eliminaContacto(Agenda contactos[], String nombre) {
		boolean flag = false;
		for (int i = 0; i < contactos.length; i++) {
			if (contactos[i] != null && contactos[i].getNombre().equalsIgnoreCase(nombre)) {
				contactos[i] = null;
				flag = true;
				break;
			}
		}
		System.out.println((flag) ? "Se ha eliminado el contacto" : "No se ha encontrado el contacto a eliminar");
	}

	// Metodo 6 - Indica si la agenda esta llena
	public static boolean agendaLlena(Agenda contactos[]) {
		boolean flag = true;
		for (int i = 0; i < contactos.length; i++) {
			if (contactos[i] == null) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	// Metodo 7 - Indica cuantos contactos hay disponibles a meter
	public static void huecosLibres(Agenda contactos[]) {
		int pos = 0;
		for (int i = 0; i < contactos.length; i++) {
			if (contactos[i] == null)
				pos++;
		}
		System.out.println((pos == 0) ? "No quedan huecos libres" : "Quedan " + pos + " huecos libres");
	}

}
