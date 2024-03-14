package unidad1.examen;

public class Gerente {
    String nombre;
    int idEmpleado;

    public Gerente(String nombre, int id) {
        this.nombre = nombre;
        this.idEmpleado = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }
}
