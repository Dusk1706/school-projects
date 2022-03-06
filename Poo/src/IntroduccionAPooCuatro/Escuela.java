package IntroduccionAPooCuatro;

public class Escuela {
	/*
	 * <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 
	 * <3 Jessica Abigail Luque Ochoa   <3 
	 * <3 Dylan Michel Garcia Figueroa  <3 
	 * <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3
	 */
	public static void main(String[] args) {
		int n,pos=0;
		do {
			System.out.println("Ingresa la cantidad de alumnos a ingresar");
			n = Leer.datoInt();
			if (n < 1)
				System.out.println("La cantidad de alumnos debe ser mayor a 0");
		} while (n < 1);
		Alumno estudiantes[] = new Alumno[n];
		int opcion;
		do {
			opcion = menu(estudiantes,pos);
		} while (opcion != 6);

	}

	// Menu
	public static int menu(Alumno estudiantes[],int pos) {
		int opcion;
		char carrera;
		do {
			System.out.println(
					"-----------------------------------------------------------------\n|\t\t\t\tMenu\t\t\t\t|");
			System.out.println("|\t1-Capturar datos de alumnos\t\t\t\t|");
			System.out.println("|\t2-Imprimir alumnos de una carrera especifica\t\t|");
			System.out.println("|\t3-Calcular promedio e imprimir nombre y promedio\t|");
			System.out.println("|\t4-Consulta de un alumno por numero de control\t\t|");
			System.out.println("|\t5-Alumno con el mejor promedio\t\t\t\t|");
			System.out.println(
					"|\t6-Salir\t\t\t\t\t\t\t|\n-----------------------------------------------------------------");
			opcion = Leer.datoInt();
			switch(opcion) {
			case 1: capturaDatos(estudiantes,pos); break;
			case 2: carrera = validacionCarrera();
			imprimeCarrera(estudiantes, carrera,pos); break;
			case 3: calcularPromedio(estudiantes,pos); break;
			case 4: 
				if (estudiantes[0] == null) {
				System.out.println("No se ha dado de alta a ningun alumno");
			} else {
				String num = validacionNumControl(estudiantes, 0);
				consultaControl(estudiantes, num);
			} break;
			case 5: mejorPromedio(estudiantes,pos); break;
			default: System.out.println((opcion == 6) ? "Gracias por usar el programa" : "Ingresa una opcion dentro del rango");
			break;
			}
		} while (opcion < 1 || opcion > 6);
		return opcion;
	}

	// Metodo 1- Para capturar datos
	public static void capturaDatos(Alumno estudiantes[],int pos) {
		int[] calif = new int[4];
		for (int i = pos; i < estudiantes.length; i++) {
			System.out.println("------------------------------Alumno " + (i + 1) + "---------------------------");
			String numControl = validacionNumControl(estudiantes, i);
			if(numControl.equals("0")) {
				break;
			}
			pos++;
			System.out.print("Ingresa el nombre del alumno: ");
			String nombre = Leer.dato();
			char carrera = validacionCarrera();
			validacionCalificacion(calif);
			estudiantes[i] = new Alumno(numControl, nombre, carrera, calif[0], calif[1], calif[2], calif[3], 0);
		}
	}

	// Metodo 2- Para imprimir una carrera especifica
	public static void imprimeCarrera(Alumno[] estudiantes, char carrera,int pos) {
		boolean flag = false;
		if (estudiantes[0] == null) {
			System.out.println("No se ha dado de alta a ningun alumno");
		} else {
			for (int i = 0; i < pos+1; i++) {
				if (estudiantes[i].getCarrera() == carrera) {
					flag = true;
					System.out.println(estudiantes[i].toString());
				}
			}
			if (flag == false)
				System.out.println("No se ha dado de alta a ningun alumno en esta carrera");
		}
	}

	// Metodo 3- Para calcular el promedio
	public static void calcularPromedio(Alumno estudiantes[],int pos) {
		double promedio;
		if (estudiantes[0] == null) {
			System.out.println("No hay estudiantes dados de alta");
		} else {
			System.out.println("Promedios de cada estudiante");
			for (int i = 0; i < pos+1; i++) {
				promedio = estudiantes[i].getCal1() + estudiantes[i].getCal2() + estudiantes[i].getCal3()
						+ estudiantes[i].getCal4();
				estudiantes[i].setPromedio(promedio / 4);
				System.out.println("------------------------------Alumno " + (i + 1) + "---------------------------");
				System.out.println(
						"Nombre: " + estudiantes[i].getNombre() + "\nPromedio: " + estudiantes[i].getPromedio());
			}
		}
	}

	// Metodo 4- Consultar alumno por numero de control
	public static void consultaControl(Alumno estudiantes[], String num) {
		boolean flag = false;
		if (estudiantes[0] == null) {
			System.out.println("No se ha dado de alta a ningun alumno");
		} else {
			for (int i = 0; i < estudiantes.length; i++) {
				if (estudiantes[i]!=null &&num.equals(estudiantes[i].getNumControl())) {
					flag = true;
					System.out.println(estudiantes[i].toString());
				}
			}
			if (flag == false) {
				System.out.println("Numero de control no asociado a ningun alumno");
			}
		}
	}

	// Metodo 5- Encontrar el alumno con mejor promedio
	public static void mejorPromedio(Alumno estudiantes[],int posi) {
		int pos = 0;
		double promedio = 0;
		if (estudiantes[0] == null) {
			System.out.println("No se ha dado de alta a ningun alumno");
		} else if (estudiantes[0].getPromedio() == 0) {
			System.out.println("No se ha calculado los promedios");
		} else {
			for (int i = 0; i < posi+1; i++) {
				if (estudiantes[i].getPromedio() > promedio) {
					promedio = estudiantes[i].getPromedio();
					pos = i;
				}
			}
			String carrera2 = (estudiantes[pos].getCarrera() == 'S') ? "Sistemas" : "Tics";
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Alumno con mejor promedio:");
			System.out.println("Nombre: " + estudiantes[pos].getNombre() + "\nCarrera: " + carrera2 + "\nPromedio: "
					+ estudiantes[pos].getPromedio());
		}
	}

	// Validacion de numero de control
	public static String validacionNumControl(Alumno estudiantes[], int k) {
		String num;
		boolean flag = false;
		do {
			System.out.print("Ingresa el numero de control: (0-Salir)");
			num = Leer.dato();
			flag = false;
			if(num.equals("0")) {
			for (int i = 0; i < k; i++) {
				if (num.equals(estudiantes[i].getNumControl())) {
					System.out.println("Numero de control ya registrado");
					flag = true;
				}
			}
			}
		} while (flag == true);
		return num;
	}

	// Validacion de carrera
	public static char validacionCarrera() {
		char carrera;
		do {
			System.out.print("Ingresa una carrera (S=Sistemas, T=Tics): ");
			carrera = Character.toUpperCase(Leer.datocar());
			if (carrera != 'S' && carrera != 'T') {
				System.out.println("Ingresa una carrera valida");
			}
		} while (carrera != 'S' && carrera != 'T');
		return carrera;
	}

	// Validacion de calificacion
	public static void validacionCalificacion(int calificaciones[]) {
		for (int j = 0; j < 4; j++) {
			do {
				System.out.print("Ingresa la calificacion " + (j + 1) + ": ");
				calificaciones[j] = Leer.datoInt();
				if (calificaciones[j] < 0 || calificaciones[j] > 100)
					System.out.println("Ingresa una calificacion en el rango(0-100)");
			} while (calificaciones[j] < 0 || calificaciones[j] > 100);
		}
	}

}
