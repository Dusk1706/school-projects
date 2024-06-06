package unidad3.programs;

class HiloRenglon extends Thread {
	private int[][] m1, m2, m3;
	private int inicia, incremento;

	public HiloRenglon(int[][] m1, int[][] m2, int[][] m3, int inicia, int incremento) {
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.inicia = inicia;
		this.incremento = incremento;
	}

	public void run() {
		for (int i = inicia; i < m1.length; i += incremento) {
			for (int j = 0; j < m1.length; j++) {
				for (int k = 0; k < m1.length; k++)
					m3[i][j] += m1[i][k] * m2[k][j];
			}

		}
	}

}

public class MultiplicarMatricesV2 {
	public static void main(String[] args) {
		int[][] m1 = new int[10][10];
		Inicializar(m1);

		int[][] m2 = new int[10][10];
		Inicializar(m2);
		int[][] m3 = new int[10][10];

		HiloRenglon hilo1 = new HiloRenglon(m1, m2, m3, 0, 3);
		HiloRenglon hilo2 = new HiloRenglon(m1, m2, m3, 1, 3);
		HiloRenglon hilo3 = new HiloRenglon(m1, m2, m3, 2, 3);

		hilo1.start();
		hilo2.start();
		hilo3.start();

		while (hilo1.isAlive() || hilo2.isAlive() || hilo3.isAlive())
			;
		System.out.println("Resultado:");
		System.out.println("Matriz 1");
		Imprimir(m1);
		System.out.println("Matriz 2");
		Imprimir(m2);
		System.out.println("Matriz 3");
		Imprimir(m3);
	}

	static private void Inicializar(int[][] m) {
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++)
				m[i][j] = i + 1;// Rutinas.nextInt(1,1);
	}

	static private void Imprimir(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.printf("%4d", m[i][j]);
			}
			System.out.println();
		}
	}

}
