/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 1
 * Proyecto: Barcos
 * Horario: 9:00 a 10:00
 * Fecha: 8/03/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */
package unidad1.proyecto3;

public class Modelo {
    private final int NUM_BARCOS, NUM_PUERTOS;
    private final Barco[] barcos;
    private final Puerto[] puertos;

    public Modelo() {
        NUM_PUERTOS = getCantidadPuertos();
        NUM_BARCOS = getCantidadBarcosAleatorio();

        puertos = crearPuertos();
        barcos = crearBarcos();
    }

    public int generarIndiceDestinoBarco() {
        int indiceAleatorio = Rutinas.nextInt(1, puertos.length - 1);
        return indiceAleatorio;
    }

    private String[] getNombrePuertos() {
        return new String[] {
                "Ensenada", "La Paz", "Guaymas", "Topolobampo",
                "Mazatlan", "Vallarta", "Manzanillo", "Lazaro"
        };
    }

    private int getCantidadBarcosAleatorio() {
        return Rutinas.nextInt(5, 10);
    }

    private int getCantidadPuertos() {
        return getNombrePuertos().length;
    }

    private Puerto[] crearPuertos() {
        String [] nombrePuertos = getNombrePuertos();
        Puerto[] puertos = new Puerto[NUM_PUERTOS];
        
        for (int i = 0; i < NUM_PUERTOS; i++) {
            puertos[i] = new Puerto(nombrePuertos[i]);
        }
        return puertos;
    }

    private Barco[] crearBarcos() {
        Barco[] barcos = new Barco[NUM_BARCOS];
        for (int i = 0; i < NUM_BARCOS; i++) {
            barcos[i] = new Barco();
        }
        return barcos;
    }

    public Barco getBarco(int i) {
        return barcos[i];
    }

    public Barco[] getBarcos() {
        return barcos;
    }

    public Puerto getPuerto(int posicionPuerto) {
        return puertos[posicionPuerto];
    }

    public Puerto[] getPuertos() {
        return puertos;
    }

    public int getNUM_BARCOS() {
        return NUM_BARCOS;
    }

    public int getNUM_PUERTOS() {
        return NUM_PUERTOS;
    }

}
