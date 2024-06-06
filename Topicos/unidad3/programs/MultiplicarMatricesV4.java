package unidad3.programs;

class HiloRenglonAleatorio extends Thread {
	private int[][] m1, m2, m3;
	static private Semaforo [][] s;
	static private boolean [][] band;

	public HiloRenglonAleatorio(int[][] m1, int[][] m2, int[][] m3) {
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		if (band == null) {
			int renglones = m1.length;
			band = new boolean[renglones][renglones];
			s = new Semaforo[renglones][renglones];
			for (int i = 0; i < renglones; i++) {
				for (int j = 0; j < renglones; j++) {
					s[i][j] = new Semaforo(1);
				}
			}
		}
	}

	public void run() {
		int renglon, columna;
		int renglones = m1.length;
		while (HayaPendientes()) {
			renglon = Rutinas.nextInt(0, renglones - 1);
			columna = Rutinas.nextInt(0, renglones - 1);
			s[renglon][columna].espera();
			if (band[renglon][columna]) {
				s[renglon][columna].libera();
				continue;
			}
			band[renglon][columna] = true;
			s[renglon][columna].libera();
			for (int k = 0; k < m1.length; k++) {
				m3[renglon][columna] += m1[renglon][k] * m2[k][columna];
			}
		}

	}

	private boolean HayaPendientes() {
		for (int i = 0; i < m1.length; i++)
			for (int j = 0; j < m1.length; j++) {
				if(!band[i][j]){
					return true;
				}
			}
		return false;
	}

}

public class MultiplicarMatricesV4 {
	public static void main(String[] args) {
		final int tam = 10;
		int[][] m1 = new int[tam][tam];
		Inicializar(m1);

		int[][] m2 = new int[tam][tam];
		Inicializar(m2);
		int[][] m3 = new int[tam][tam];

		HiloRenglonAleatorio hilo1 = new HiloRenglonAleatorio(m1, m2, m3);
		HiloRenglonAleatorio hilo2 = new HiloRenglonAleatorio(m1, m2, m3);
		HiloRenglonAleatorio hilo3 = new HiloRenglonAleatorio(m1, m2, m3);

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
