package unidad3.programs;

class Tarea11 implements Runnable {
	public void run() {
		for (int i = 0; i < 20; i++)
			System.out.println(i + " *** PROCESO UNO TRABAJANDO ");
	}
}

class Tarea12 implements Runnable {
	public void run() {
		for (int i = 0; i < 20; i++)
			System.out.println(i + " *** PROCESO DOS TRABAJANDO *");
	}
}

class Tarea13 implements Runnable {

	public void run() {
		for (int i = 0; i < 20; i++)
			System.out.println(i + " *** PROCESO TRES TRABAJANDO ***");
	}
}

public class Hilo2 {

	public static void main(String[] a) {

		Tarea11 H1 = new Tarea11();
		Tarea12 H2 = new Tarea12();
		Tarea13 H3 = new Tarea13();
		Thread t1 = new Thread(H1);
		Thread t2 = new Thread(H2);
		Thread t3 = new Thread(H3);
		t1.start();
		t2.start();
		t3.start();
		// checa si uno o mas hilos est�n activos

	}
}
