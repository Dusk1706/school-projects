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

import java.util.HashMap;

public class Modelo {
    private final int NUM_CUENTAS;
    private HashMap<String, Tarjeta> cuentas;

    public Modelo() {
        NUM_CUENTAS = Rutinas.nextInt(50, 100);
        cuentas = new HashMap<String, Tarjeta>();
        inicializarCuentas();
    }

    public boolean verificarNip(String noTarjeta, int nip) {
        return cuentas.get(noTarjeta).getNip() == nip; 
    }

    public boolean retirarDinero(String noTarjeta, int cantidad) {
        return cuentas.get(noTarjeta).retirarDinero(cantidad);
    }

    private void inicializarCuentas() {
        for (int i = 0; i < NUM_CUENTAS; i++) {
            cuentas.put(generarTarjetaUnica(), new Tarjeta());
        }
    }

    private String generarTarjetaUnica() {
        String noTarjeta;
        do {
            noTarjeta = generarNumeroTarjeta();
        } while (cuentas.containsKey(noTarjeta));
        return noTarjeta;
    }

    private String generarNumeroTarjeta() {
        String noTarjeta = String.valueOf(Rutinas.nextInt(1, 9));
        for (int i = 2; i <= 16; i++) {
            noTarjeta += Rutinas.nextInt(0, 9);
            if (i % 4 == 0 && i != 16) {
                noTarjeta += " ";
            }
        }
        return noTarjeta;
    }

    public HashMap<String, Tarjeta> getCuentas() {
        return cuentas;
    }

}
