/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 1
 * Proyecto: Lista Logica
 * Horario: 9:00 a 10:00
 * Fecha: 09/02/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad1.proyecto1;

import java.util.*;

public class Rutinas {

	static Random r = new Random();
	static String[] VN = { "Diego", "Daniel", "Yessenia", "Luis", "Anastacia", "Plutarco", "Alicia", "Maria", "Sofia",
			"Antonio", "Nereida", "Carolina",
			"Rebeca", "Javier", "Luis" };
	static String[] VA = { "Garcia", "Lopez", "Perez", "Urias", "Mendoza", "Coppel", "Diaz" };
	static boolean[] Sexo = { true, true, false, true, false, true, false, false, false, true, false, false,
			false, true, true };

	public static String nextNombre(int Numero) {
		String Nom = "", NomTra = "";
		int Pos;
		boolean Genero = true;

		for (int i = 0; i < Numero; i++) {
			Pos = r.nextInt(VN.length);

			NomTra = VN[Pos] + " ";

			if (i == 0) {
				Genero = Sexo[Pos];
			}

			if (Genero != Sexo[Pos] || i > 0 && Nom.indexOf(NomTra) > -1) {
				i--;
				continue;
			}

			Nom += NomTra;

		}
		NomTra = "";
		for (byte i = 0; i < 2; i++) {
			NomTra += VA[r.nextInt(VA.length)] + " ";
		}
		Nom = Nom + NomTra;
		return Nom.trim();
	}

	public static String miTrim(String texto) {
		int sub = 0;
		while (texto.charAt(sub) == ' ')
			sub++;
		texto = texto.substring(sub);
		sub = texto.length() - 1;
		while (texto.charAt(sub) == ' ')
			sub--;
		texto = texto.substring(0, sub + 1);

		String aux = "";
		char car;
		boolean band = true;
		for (int i = 0; i < texto.length(); i++) {
			car = texto.charAt(i);
			if (car == ' ' && band) {
				aux += ' ';
				band = false;
				continue;
			}
			if (car != ' ') {
				aux = aux + car;
				band = true;
			}

		}

		return aux;
	}

	public static String PonComas(long cantidad) {
		String texto = cantidad + "";
		String res = "";
		int l;
		while (texto.length() > 3) {
			l = texto.length();
			res = "," + texto.substring(l - 3) + res;
			texto = texto.substring(0, l - 3);
		}
		if (texto.length() > 0)
			res = texto + res;
		return res;
	}

	public static int nextInt(int li, int ls) {
		return r.nextInt(ls - li + 1) + li;
	}

	public static String PonCeros(long numero, int tamanno) {
		String texto = numero + "";
		while (texto.length() < tamanno)
			texto = "0" + texto;
		return texto;
	}

	public static String PonBlancos(String texto, int tamanno) {
		while (texto.length() < tamanno)
			texto = texto + " ";
		return texto;
	}

	public static String nextColor() {
		String[] colores = { "rojo", "rosa", "violeta", "negro", "amarillo", "blanco", "gris", "azul", "verde", "cafe",
				"naranja", "lila" };
		return colores[Rutinas.nextInt(0, colores.length - 1)];
	}
}
