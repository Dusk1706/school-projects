package unidad3.programs;

class Tarea1 extends Thread {
	public void run() {
		for (int i = 0; i < 20; i++)
			System.out.println(i + " *** PROCESO UNO TRABAJANDO ");
	}
}

class Tarea2 extends Thread {
	public void run() {
		for (int i = 0; i < 20; i++)
			System.out.println(i + " *** PROCESO DOS TRABAJANDO *");
	}
}

class Tarea3 extends Thread {

	public void run() {
		for (int i = 0; i < 20; i++)
			System.out.println(i + " *** PROCESO TRES TRABAJANDO ***");
	}
}

public class Hilos1 {
	public static void main(String[] a) {
		Tarea1 H1 = new Tarea1();
		Tarea2 H2 = new Tarea2();
		Tarea3 H3 = new Tarea3();

		H1.start();
		H2.start();
		H3.start();
		// checa si uno o mas hilos est�n activos

	}
}
