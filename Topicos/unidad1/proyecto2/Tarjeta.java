/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 1
 * Proyecto: Teclado de autorizacion
 * Horario: 9:00 a 10:00
 * Fecha: 23/02/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad1.proyecto2;

public class Tarjeta {
    private int saldo, nip;

    public Tarjeta() {
        saldo = Rutinas.nextInt(1000, 50000);
        nip = 1234;
    }

    public boolean retirarDinero(int cantidad) {
        if (cantidad > saldo) {
            return false;
        }
        saldo -= cantidad;
        return true;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getNip() {
        return nip;
    }

    @Override
    public String toString() {
        return "Saldo: " + saldo + " NIP: " + nip;
    }

}
