package unidad1.examen;

public class IdEmpleado {
    private int cantidadAccesos;
    private boolean pasoPorTorniquete;

    public IdEmpleado() {
        cantidadAccesos = generarCantidadAccesos();
        pasoPorTorniquete = false;
    }

    private int generarCantidadAccesos() {
        return Rutinas.nextInt(1000, 1500);
    }

    public void setCantidadAccesos(int cantidadAccesos) {
        this.cantidadAccesos = cantidadAccesos;
    }

    public void setPasoPorTorniquete(boolean pasoPorTorniquete) {
        this.pasoPorTorniquete = pasoPorTorniquete;
    }

    public int getCantidadAccesos() {
        return cantidadAccesos;
    }

    public boolean pasoPorTorniquete() {
        return pasoPorTorniquete;
    }


}
