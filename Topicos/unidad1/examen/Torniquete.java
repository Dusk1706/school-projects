package unidad1.examen;

public class Torniquete {
    private IdEmpleado [] idsEmpleados;
    
    public Torniquete(int cantidadEmpleados) {
        idsEmpleados = new IdEmpleado[cantidadEmpleados];
        generarIds(cantidadEmpleados);
    }

    private void generarIds(int cantidadEmpleados) {
        for (int id = 0; id < cantidadEmpleados; id++) {
            idsEmpleados[id] = new IdEmpleado();
        }
    }

    public IdEmpleado getIdEmpleado(int id) {
        return idsEmpleados[id - 1];
    }

    public IdEmpleado[] getEmpleados() {
        return idsEmpleados;
    }


}
