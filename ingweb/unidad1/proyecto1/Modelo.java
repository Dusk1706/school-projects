/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Ingenieria Web
 * Unidad: 1
 * Proyecto: Persistencia de objetos
 * Horario: 10:00 a 11:00
 * Fecha: 10/02/2025
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package proyecto1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

public class Modelo {
    
    public boolean registrarCarro(Carro carro) {
        System.out.println(carro);

        File file = new File("carros.dat");
        boolean append = file.exists();

        try (
            FileOutputStream fout = new FileOutputStream(file, append);
            AppendableObjectOutputStream oout = new AppendableObjectOutputStream(fout, append);
        ) {
            oout.writeObject(carro);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Carro[] buscarCarros(String placa) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("carros.dat"))) {
            
            Carro[] temp = new Carro[10];
            int size = 0;
            
            while (true) {
                try {
                    Carro carro = (Carro) ois.readObject();
                    
                    if (carro.getPlaca().equals(placa)) {
                        if (size == temp.length) {
                            Carro[] nuevoArray = new Carro[temp.length * 2];
                            System.arraycopy(temp, 0, nuevoArray, 0, size);
                            temp = nuevoArray;
                        }
                        temp[size++] = carro;
                    }
                } catch (EOFException e) {
                    break; 
                }
            }
            
            Carro[] resultado = new Carro[size];
            System.arraycopy(temp, 0, resultado, 0, size);
            
            return resultado.length > 0 ? resultado : null;
    
        } catch (Exception e) {
            System.out.println("Error al buscar carros: " + e.getMessage());
            return null;
        }
    }
    
}
