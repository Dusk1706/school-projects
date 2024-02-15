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

import java.io.RandomAccessFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Archivos {
    private final static String PERSONAS_PATH = "Personas.dat";
    private final static String INICIO_PATH = "inicio.txt";
    private final static int TAMANNO_REGISTRO = 60;
    private static int cantidadPersonas;
    private static RandomAccessFile raf;

    public static void crearArchivo() throws IOException {
        raf = new RandomAccessFile(PERSONAS_PATH, "rw");
        cantidadPersonas = Rutinas.nextInt(500, 600);
        for (int i = 0; i < cantidadPersonas; i++) {
            Persona p = new Persona();
            raf.writeUTF(p.getNombre());
            raf.writeInt(p.getEdad());
            raf.writeInt(p.getSig());
        }
        raf.close();
    }

    private static int encontrarPersonaMenor(int ultimaPersonaEncontrada) throws IOException {
        String nombreMenor = "";
        int personaMenor = -1;

        raf.seek(0);
        for (int j = 0; j < cantidadPersonas; j++) {
            String nombre = raf.readUTF();
            raf.readInt();
            int sig = raf.readInt();
            if (sig == 0 && ultimaPersonaEncontrada != j) {
                if (nombre.compareTo(nombreMenor) < 0 || personaMenor == -1) {
                    nombreMenor = nombre;
                    personaMenor = j;
                }
            }
        }

        return personaMenor;
    }

    public static void actualizarArchivo() throws IOException {
        raf = new RandomAccessFile(PERSONAS_PATH, "rw");
        int ultimaPersonaEncontrada = -1;

        for (int i = 0; i < cantidadPersonas; i++) {
            int personaMenorActual = encontrarPersonaMenor(ultimaPersonaEncontrada);
            if (i == 0) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(INICIO_PATH));
                bw.write(String.valueOf(personaMenorActual + 1));
                bw.close();
            } else {
                long posicion = (TAMANNO_REGISTRO * ultimaPersonaEncontrada)
                        + TAMANNO_REGISTRO - 4;
                raf.seek(posicion);
                raf.writeInt(personaMenorActual + 1);
            }
            ultimaPersonaEncontrada = personaMenorActual;
        }
        raf.close();
    }

    public static int personaMenorInicio() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(INICIO_PATH));
        int personaMenor = Integer.parseInt(br.readLine());
        br.close();
        return personaMenor;
    }

    public static void leerArchivos() throws IOException {
        String guiones = "-".repeat(100);
        System.out.println(guiones);
        int personaMenor = personaMenorInicio();
        System.out.printf("Persona menor: %03d\n", personaMenor);
        System.out.println(guiones);

        raf = new RandomAccessFile(PERSONAS_PATH, "r");
        for (int i = 1; i <= cantidadPersonas; i++) {
            String nombre = raf.readUTF();
            int edad = raf.readInt();
            int sig = raf.readInt();
            System.out.printf("Persona %03d: %s\t%d\t%03d\n", i, nombre, edad, sig);
        }
        System.out.println(guiones);
        raf.close();
    }

}
