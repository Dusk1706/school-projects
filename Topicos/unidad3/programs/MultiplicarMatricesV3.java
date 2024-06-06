package unidad3.programs;

import java.util.Arrays;

class HiloRenglonAleatorio extends Thread {
	private int[][] m1, m2, m3;
	private int inicia, incremento;
	static private Semaforo s;
	static private boolean[] band;

	public HiloRenglonAleatorio(int[][] m1, int[][] m2, int[][] m3, int inicia, int incremento) {
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.inicia = inicia;
		this.incremento = incremento;
		if (band == null) {
			band = new boolean[m1.length];
			Arrays.fill(band, false);
			s = new Semaforo(1);
		}
	}

	public void run() {
		int renglon;
		while (HayaPendientes()) {
			renglon = Rutinas.nextInt(0, m1.length - 1);
			s.espera();
			if (band[renglon]) {
				s.libera();
				continue;
			}
			band[renglon] = true;
			s.libera();
			for (int j = 0; j < m1.length; j++) {
				for (int k = 0; k < m1.length; k++)
					m3[renglon][j] += m1[renglon][k] * m2[k][j];
			}
		}

	}

	private boolean HayaPendientes() {
		for (int i = 0; i < m1.length; i++)
			if (!band[i])
				return true;
		return false;

	}

}

public class MultiplicarMatricesV3 {
	public static void main(String[] args) {
		final int tam = 10;
		int[][] m1 = new int[tam][tam];
		Inicializar(m1);

		int[][] m2 = new int[tam][tam];
		Inicializar(m2);
		int[][] m3 = new int[tam][tam];

		HiloRenglonAleatorio hilo1 = new HiloRenglonAleatorio(m1, m2, m3, 0, 3);
		HiloRenglonAleatorio hilo2 = new HiloRenglonAleatorio(m1, m2, m3, 1, 3);
		HiloRenglonAleatorio hilo3 = new HiloRenglonAleatorio(m1, m2, m3, 2, 3);

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
