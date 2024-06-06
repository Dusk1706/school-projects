/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 2
 * Proyecto: Componentes
 * Horario: 9:00 a 10:00
 * Fecha: 11/04/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package proyecto2;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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

	public static String tipoTienda() {
		String[] tipoTienda = { "Electronica", "Hogar", "Oficina", "Industrial", "Entretenimiento", "Deportes", "Salud",
				"Belleza", "Moda", "Calzado", "Accesorios", "Joyeria", "Relojes", "Bolsas", "Mochilas", "Maletas",
				"Equipaje", "Viaje", "Herramientas", "Jardin", "Mascotas", "Niños", "Bebes", "Juguetes", "Libros",
				"Musica", "Peliculas", "Series", "Videojuegos", "Consolas" };
		return tipoTienda[Rutinas.nextInt(0, tipoTienda.length - 1)];
	
	}

	public static String horarioAtencion() {
		String[] horarioAtencion = { "8:00 - 20:00", "9:00 - 21:00", "10:00 - 22:00", "11:00 - 23:00", "12:00 - 24:00",
				"13:00 - 1:00", "14:00 - 2:00", "15:00 - 3:00", "16:00 - 4:00", "17:00 - 5:00", "18:00 - 6:00",
				"19:00 - 7:00", "20:00 - 8:00", "21:00 - 9:00", "22:00 - 10:00", "23:00 - 11:00" };
		return horarioAtencion[Rutinas.nextInt(0, horarioAtencion.length - 1)];
	}

	public static String producto() {
		String[] producto = { "Laptop", "Tablet", "Smartphone", "Smartwatch", "Audifonos", "Teclado", "Mouse",
				"Monitor", "Impresora", "Proyector", "Bocina", "Cargador", "Cable", "Memoria USB", "Disco Duro",
				"Tarjeta de Video", "Tarjeta Madre", "Procesador", "Memoria RAM", "Fuente de Poder", "Gabinete",
				"Ventilador", "Tarjeta de Red", "Switch", "Router", "Modem", "Cable de Red", "Cable Coaxial",
				"Antena", "Telefono", "Camara", "Microfono", "Bateria", "Cargador", "Estuche", "Funda", "Mica",
				"Protector", "Soporte", "Base", "Tripie", "Mochila", "Maletin", "Bolso", "Portafolio", "Estuche",
				"Fundas", "Micas", "Protectores", "Soportes", "Bases", "Tripiés", "Mochilas", "Maletines", "Bolsos",
				"Portafolios" };
		return producto[Rutinas.nextInt(0, producto.length - 1)];
	
	}

	public static String nombreTienda() {
		String[] tienda = { "BestBuy", "Walmart", "Soriana", "Chedraui", "Coppel", "Elektra", "Liverpool", "Palacio de Hierro",
				"Office Depot", "Staples", "RadioShack", "GamePlanet", "GameStop", "Blockbuster", "Cinemex", "Cinepolis",
				"Palacio de Hierro", "Sears", "Sanborns", "Suburbia", "Zara", "H&M", "Forever 21", "Pull & Bear",
				"Stradivarius", "Bershka", "Oysho", "Massimo Dutti", "Zara Home", "Uterque", "Mango"};
		return tienda[Rutinas.nextInt(0, tienda.length - 1)];
	}

	public static String ciudad() {
		String[] ciudad = { "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas",
				"Chihuahua", "Coahuila", "Colima", "Durango", "Estado de Mexico", "Guanajuato", "Guerrero", "Hidalgo",
				"Jalisco", "Michoacan", "Morelos", "Nayarit", "Nuevo Leon", "Oaxaca", "Puebla", "Queretaro", "Quintana Roo",
				"San Luis Potosi", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatan",
				"Zacatecas", "CDMX" };
		return ciudad[Rutinas.nextInt(0, ciudad.length - 1)];
	}

	public static String calidad() {
		String[] calidad = { "Alta", "Excelente" , "Media", "Baja" };
		return calidad[Rutinas.nextInt(0, calidad.length - 1)];
	}

	public static String tamaños() {
		String[] inventario = { "Grande", "Mediano", "Chico" };
		return inventario[Rutinas.nextInt(0, inventario.length - 1)];
	}

	public static String origen() {
		String[] origen = { "Nacional", "Importado" };
		return origen[Rutinas.nextInt(0, origen.length - 1)];
	}

	public static String margenBeneficio() {
		String[] margenBeneficio = { "10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%" };
		return margenBeneficio[Rutinas.nextInt(0, margenBeneficio.length - 1)];
	
	}

	public static String dimensiones() {
		String[] dimensiones = { "10x10x10", "20x20x20", "30x30x30", "40x40x40", "50x50x50", "60x60x60", "70x70x70",
				"80x80x80", "90x90x90", "100x100x100" };
		return dimensiones[Rutinas.nextInt(0, dimensiones.length - 1)];
	}


	public static String categoria() {
		String[] categoria = { "Computo", "Telefonia", "Audio", "Video", "Redes", "Accesorios", "Almacenamiento",
				"Componentes", "Perifericos", "Gaming", "Software", "Servicios", "Refacciones", "Consumibles",
				"Electronica", "Hogar", "Oficina", "Industrial", "Entretenimiento", "Deportes", "Salud", "Belleza",
				"Moda", "Calzado", "Accesorios", "Joyeria", "Relojes", "Bolsas", "Mochilas", "Maletas", "Equipaje",
				"Viaje", "Herramientas", "Jardin", "Mascotas", "Niños", "Bebes", "Juguetes", "Libros", "Musica",
				"Peliculas", "Series", "Videojuegos", "Consolas", "Computadoras", "Laptops", "Tablets", "Smartphones",
				"Smartwatches", "Audifonos", "Teclados", "Mouses", "Monitores", "Impresoras", "Proyectores", "Bocinas",
				"Cargadores", "Cables", "Memorias USB", "Discos Duros", "Tarjetas de Video", "Tarjetas Madre",
				"Procesadores", "Memorias RAM", "Fuentes de Poder", "Gabinetes", "Ventiladores", "Tarjetas de Red",
				"Switches", "Routers", "Modems", "Cables de Red", "Cables Coaxiales", "Antenas", "Telefonos", "Camaras",
				"Microfonos", "Baterias", "Cargadores", "Estuches", "Fundas", "Micas", "Protectores", "Soportes",
				"Bases", "Tripiés", "Mochilas", "Maletines", "Bolsos", "Portafolios", "Estuches", "Fundas", "Micas",
				"Protectores", "Soportes", "Bases", "Tripiés", "Mochilas", "Maletines", "Bolsos", "Portafolios"};
		return categoria[Rutinas.nextInt(0, categoria.length - 1)];
	}

	public static String marca() {
		String[] marca = { "Apple", "Samsung", "Huawei", "Xiaomi", "Sony", "LG", "Motorola", "Nokia", "HTC", "Lenovo",
				"HP", "Dell", "Acer", "Asus", "Toshiba", "Alienware", "MSI", "Razer", "Corsair", "Logitech", "SteelSeries",
				"HyperX", "Kingston", "Crucial", "Western Digital", "Seagate", "Toshiba", "Samsung", "Kingston", "Crucial",
				"Western Digital", "Seagate", "Toshiba", "Samsung", "Kingston", "Crucial", "Western Digital", "Seagate",
				"Toshiba", "Samsung", "Kingston", "Crucial", "Western Digital", "Seagate", "Toshiba", "Samsung", "Kingston",
				"Crucial", "Western Digital", "Seagate", "Toshiba", "Samsung", "Kingston", "Crucial", "Western Digital",
				"Seagate", "Toshiba", "Samsung", "Kingston", "Crucial", "Western Digital", "Seagate", "Toshiba", "Samsung",
				"Kingston", "Crucial", "Western Digital", "Seagate", "Toshiba", "Samsung", "Kingston", "Crucial",
				"Western Digital", "Seagate", "Toshiba", "Samsung", "Kingston", "Crucial", "Western Digital", "Seagate",
				"Toshiba", "Samsung", "Kingston", "Crucial", "Western Digital", "Seagate", "Toshiba", "Samsung", "Kingston",
				"Crucial", "Western Digital", "Seagate", "Toshiba", "Samsung", "Kingston", "Crucial", "Western Digital",
				"Seagate", "Toshiba", "Samsung", "Kingston", "Crucial", "Western Digital", "Seagate", "Toshiba", "Samsung",
				"Kingston", "Crucial", "Western Digital", "Seagate", "Toshiba", "Samsung", "Kingston", "Crucial"};
		return marca[Rutinas.nextInt(0, marca.length - 1)];
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
}
