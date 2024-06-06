package unidad3.programs;

public class incremetar {

	public static void main(String[] args) {
		Incremento o1, o2, o3;
		o1 = new Incremento();
		o2 = new Incremento();
		o3 = new Incremento();

		o1.start();
		o2.start();
		o3.start();
		while (o1.isAlive() | o2.isAlive() | o3.isAlive())
			;
		System.out.println(o1);

		Incremento[] hilos = new Incremento[100_000];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Incremento();
		}
		for (int i = 0; i < hilos.length; i++)
			hilos[i].start();

		while (HayaVivos(hilos))
			;
		System.out.println(hilos[0]);
	}

	private static boolean HayaVivos(Incremento[] arreglo) {
		for (int i = 0; i < arreglo.length; i++) {
			if (arreglo[i].isAlive())
				return true;
		}
		return false;
	}
}
