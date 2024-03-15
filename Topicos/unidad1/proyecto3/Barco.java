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

import unidad1.examen.Rutinas;

public class Barco {
    Bodega bodega;
    TanqueCombustible tanque;

    public Barco() {
        final int capacidadBodegaToneladas = Rutinas.nextInt(60, 90);
        bodega = new Bodega(capacidadBodegaToneladas);
        tanque = new TanqueCombustible(bodega.getCapacidadToneladas());
    }

    public int pescarProductosKg() {
        return Rutinas.nextInt(20_000, 30_000);
    }

    public void guardarProductosBodega(int pesoProductos) {
        if (bodega.isLlena()) {
            return;
        }
        int pesoNuevo = bodega.getPesoActualKg() + pesoProductos;
        pesoNuevo = Math.min(pesoNuevo, bodega.getCapacidadKg());
        bodega.setPesoActualKg(pesoNuevo);
    }

    public boolean isBodegaLlena70Porciento() {
        return bodega.getPesoActualKg() >= bodega.getCapacidadKg() * 0.7;
    }

    public void llenarTanque() {
        int capacidadTanque = tanque.getCapacidadLitros();
        int litrosActuales = tanque.getLitrosActuales();

        int porcentaje = Rutinas.nextInt(90, 95);
        int litros = capacidadTanque * porcentaje / 100;

        int litrosNuevo = Math.max(litrosActuales, litros);

        tanque.setLitrosActuales(litrosNuevo);
    }

    public void gastarCombustible() {
        int litrosActuales = tanque.getLitrosActuales();
        int litrosNuevo = litrosActuales - (int) (litrosActuales * .10);
        tanque.setLitrosActuales(litrosNuevo);
    }

    public void vaciarBodega() {
        bodega.setPesoActualKg(0);
    }

    public Bodega getBodega() {
        return bodega;
    }

    public TanqueCombustible getTanque() {
        return tanque;
    }

    @Override
    public String toString() {
        return "Barco [\n\t" + bodega + "\n\t" + tanque + "\n]";
    }

}
