
package unidad4.proyecto1;

import java.awt.Image;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import unidad4.proyecto1.modelo.Contacto;

public class Rutinas {
	static Random r = new Random();

	static String[] VN = { "Zoila", "Daniel", "Yessenia", "Luis", "Anastacia", "Plutarco", "Alicia", "Maria", "Sofia",
			"Antonio", "Nereida", "Carolina",
			"Rebeca", "Javier", "Luis" };
	static String[] VA = { "Garcia", "Lopez", "Perez", "Urias", "Mendoza", "Coppel", "Diaz" };
	static boolean[] Sexo = { false, true, false, true, false, true, false, false, false, true, false, false, false,
			true, true };

	public static void mensaje(String texto, int tipo) {
		JOptionPane.showMessageDialog(null, texto, "", tipo);
	}

	public static String nextNombre(int Numero) {
		String Nom = "", NomTra = "";
		int Pos;
		boolean Genero = true;

		for (int i = 0; i < Numero; i++) {
			Pos = nextInt(VN.length);

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
		for (byte i = 0; i < 2; i++) {
			Nom += VA[nextInt(VA.length)] + " ";
		}
		return Nom.trim();
	}

	public static int nextInt(int li, int ls) {
		return r.nextInt(ls - li + 1) + li;

	}

	public static int nextInt(int numero) {
		return r.nextInt(numero);
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

	public static String color() {
		String[] color = { "Azul", "Amarillo", "Negro", "Blanco", "Rojo", "Verde", "Purpura" };
		return color[Rutinas.nextInt(0, color.length - 1)];
	}

	public static String PonBlancos(String texto, int largo) {
		while (texto.length() < largo)
			texto = texto + " ";
		return texto;

	}

	public static ImageIcon AjustarImagen(String ico, int Ancho, int Alto) {
		ImageIcon tmpIconAux = new ImageIcon(ico);
		// Escalar Imagen
		ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(Ancho,
				Alto, Image.SCALE_SMOOTH));// SCALE_DEFAULT
		return tmpIcon;
	}

	public static String generarDireccion() {
        String calle = "Calle " + Rutinas.nextInt(1, 100);
        String colonia = "Colonia " + Rutinas.nextInt(1, 100);
        String ciudad = "Ciudad " + Rutinas.nextInt(1, 100);
        return calle + ", " + colonia + ", " + ciudad;
    }

	public static String generarTelefono() {
		String telefono = Rutinas.nextInt(1, 9) + "";
		for (int i = 0; i < 9; i++) {
			telefono += Rutinas.nextInt(0, 9);
		}
		return telefono;
	}

	public static String generarNombreEmpresa() {
        return "Empresa " + nextNombre(1);
    }

	public static String generarFecha() {
		int dia = Rutinas.nextInt(1, 31);
        int mes = Rutinas.nextInt(1, 12);
        int anio = Rutinas.nextInt(2000, 2024);
		String fecha = dia + "/" + mes + "/" + anio;
        return convertirFecha(fecha);
	}

	public static String convertirFecha(String fechaOriginal) {
        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("es", "MX"));
        try {
            Date fecha = new SimpleDateFormat("dd/MM/yyyy", dfs).parse(fechaOriginal);
            return new SimpleDateFormat("dd-MM-yyyy").format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error fecha " + fechaOriginal);
            return null;
        }
    }

	public static Contacto[] generarContactos(int numeroContactos, int numeroTabs) {
        int numTabs = numeroTabs + 1 + (numeroContactos == 1 ? 0 : 1);
        Contacto [] contactos = new Contacto[numeroContactos];
        for (int i = 0; i < numeroContactos; i++) {
            contactos[i] = new Contacto(numTabs);
        }
		return contactos;
    }

	public static String generarEtiquetaFecha(String fecha, int numeroTabs) {
		String tabs = generarTabs(numeroTabs);
		String tabs1 = generarTabs(numeroTabs + 1);
		return  tabs + "<fecha>\n" +
			   	tabs1 + "<dia>" + fecha.substring(0, 2) + "</dia>\n" +
			   	tabs1 + "<mes>" + fecha.substring(3, 5) + "</mes>\n" +
			   	tabs1 + "<año>" + fecha.substring(6) + "</año>\n" +
				tabs + "</fecha>\n";
	}

	public static String generarCorreo(String inicial) {
		String correo = inicial + "@gmail.com";
		return correo;
	}

	public static String generarTabs(int numeroTabs) {
		String tabs = "";
		for (int i = 0; i < numeroTabs; i++) {
			tabs += "    ";
		}
		return tabs;
	}
}
