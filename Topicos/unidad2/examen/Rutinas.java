/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 2
 * Proyecto: Examen
 * Horario: 9:00 a 10:00
 * Fecha: 19/04/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad2.examen;

public class Rutinas {

	public static String decimalAHexadecimal(int decimal) {
		String hexadecimal = "";
		String caracteresHexadecimales = "0123456789abcdef";
		while (decimal > 0) {
			int residuo = decimal % 16;
			hexadecimal = caracteresHexadecimales.charAt(residuo) + hexadecimal;
			decimal /= 16;
		}
		return hexadecimal;
	}

	public static String decimalABinario(int decimal) {
		String binario = "";
		while (decimal > 0) {
			if (decimal % 2 == 0) {
				binario = "0" + binario;
			} else {
				binario = "1" + binario;
			}
			decimal /= 2;
		}
		return binario;
	}

	public static String binarioADecimal(String binario) {
		int decimal = 0, potencia = 0;
		for (int i = binario.length() - 1; i >= 0; i--) {
			if (binario.charAt(i) == '1') {
				decimal += Math.pow(2, potencia);
			}
			potencia++;
		}
		return String.valueOf(decimal);
	}

	public static String binarioAHexadecimal(String binario) {
		String decimal = binarioADecimal(binario);
		return decimalAHexadecimal(Integer.parseInt(decimal));
	}

	public static String hexadecimalABinario(String hex) {
		String decimal = hexadecimalADecimal(hex);
		return decimalABinario(Integer.parseInt(decimal));
	}

	public static String hexadecimalADecimal(String hexadecimal) {
		String digitos = "0123456789abcdef";
		int decimal = 0;
		for (int i = 0; i < hexadecimal.length(); i++) {
			char caracter = hexadecimal.charAt(i);
			int valor = digitos.indexOf(caracter);
			decimal = 16 * decimal + valor;
		}
		return String.valueOf(decimal);
	}

	public static int getValorPorcentaje(int valor, int porcentaje) {
		return valor * porcentaje / 100;
	}

	public static boolean esNumero(char caracter) {
		return caracter >= '0' && caracter <= '9';
	}

	public static boolean esHexadecimal(char caracter) {
		return esNumero(caracter) || caracter >= 'a' && caracter <= 'f';
	}

	public static boolean esBinario(char caracter) {
		return caracter == '0' || caracter == '1';
	}
}
