package unidad3.programs;

class Par extends Thread {
	private int[][] m1, m2, m3;

	public Par(int[][] m1, int[][] m2, int[][] m3) {
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
	}

	public void run() {
		for (int i = 0; i < m1.length; i += 2) {
			for (int j = 0; j < m1.length; j++) {
				for (int k = 0; k < m1.length; k++)
					m3[i][j] += m1[i][k] * m2[k][j];
			}

		}
	}

}

class Impar extends Thread {
	private int[][] m1, m2, m3;

	public Impar(int[][] m1, int[][] m2, int[][] m3) {
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
	}

	public void run() {
		for (int i = 1; i < m1.length; i += 2) {
			for (int j = 0; j < m1.length; j++) {
				for (int k = 0; k < m1.length; k++)
					m3[i][j] += m1[i][k] * m2[k][j];
			}

		}
	}
}

public class MutiplicarMatrices {

	public static void main(String[] args) {
		int[][] m1 = new int[10][10];
		Inicializar(m1);

		int[][] m2 = new int[10][10];
		Inicializar(m2);
		int[][] m3 = new int[10][10];
		Par hiloPar = new Par(m1, m2, m3);
		Impar hiloImpar = new Impar(m1, m2, m3);

		hiloPar.start();
		hiloImpar.start();

		while (hiloPar.isAlive() || hiloImpar.isAlive())
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
