/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Ingenieria Web
 * Unidad: 1
 * Proyecto: MVC
 * Horario: 10:00 a 11:00
 * Fecha: 24/02/2025
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package proyecto2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class RegistradorObjetos {
    private File file;
    private FileOutputStream fout;
    private AppendableObjectOutputStream oout;

    public RegistradorObjetos(String nombreArchivo) {
        file = new File(nombreArchivo);
        abrirArchivo();
    }

    public <T> boolean registrarObjeto(T objeto) {
        try {
            oout.writeObject(objeto);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Operacion buscarOperacion(String operacion, int valor) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            while (true) {
                try {
                    Operacion op = (Operacion) ois.readObject();

                    if (op.getOperacion().equals(operacion) && op.getValor() == valor) {
                        return op;
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            return null;

        } catch (Exception e) {
            System.out.println("Error al buscar operacion: " + e.getMessage());
            return null;
        }
    }

    public void leerObjetos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("------ Objetos registrados ------");
            while (true) {
                try {
                    Object obj = ois.readObject();
                    System.out.println(obj);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer objetos: " + e.getMessage());
        }
    }

    private void abrirArchivo() {
        try {
            boolean append = file.exists();
            fout = new FileOutputStream(file, append);
            oout = new AppendableObjectOutputStream(fout, append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrarArchivo() {
        try {
            if (oout != null) {
                oout.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
