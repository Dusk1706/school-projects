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
    private final String[] puertos;

    public Modelo() {
        puertos = crearPuertos();
        
        NUM_PUERTOS = puertos.length;
        NUM_BARCOS = Rutinas.nextInt(5, 10);

        barcos = new Barco[NUM_BARCOS];
        crearBarcos();
    }

    public String[] crearPuertos() {
        return new String[]{ 
            "Ensenada", "La Paz", "Guaymas", "Topolobampo", 
            "Mazatlan", "Vallarta", "Manzanillo", "Lazaro"  
        };
    }

    public void crearBarcos() {
        for (int i = 0; i < NUM_BARCOS; i++) {
            barcos[i] = new Barco();
        }
    }

    public int generarIndiceDestino() {
        int indiceAleatorio = Rutinas.nextInt(1, puertos.length - 1);
        return indiceAleatorio;
    }

    public Barco getBarco(int i) {
        return barcos[i];
    }

    public Barco[] getBarcos() {
        return barcos;
    }

    public String[] getPuertos() {
        return puertos;
    }

    public int getNUM_BARCOS() {
        return NUM_BARCOS;
    }

    public int getNUM_PUERTOS() {
        return NUM_PUERTOS;
    }

}
