package unidad1.proyecto3;

import unidad1.examen.Rutinas;

public class Puerto {
    private String nombre;
    private Bodega bodega;

    public Puerto(String nombre) {
        this.nombre = nombre;
        final int capacidadBodegaToneladas = Rutinas.nextInt(1000, 5000);
        bodega = new Bodega(capacidadBodegaToneladas);
    }

    public void guardarProductosPuerto(int pesoProductos) {
        if (bodega.isLlena()) {
            return;
        }
        int pesoNuevo = bodega.getPesoActualKg() + pesoProductos;
        pesoNuevo = Math.min(pesoNuevo, bodega.getCapacidadKg());
        bodega.setPesoActualKg(pesoNuevo);
    }

    public String getNombre() {
        return nombre;
    }

    public Bodega getBodega() {
        return bodega;
    }

}
