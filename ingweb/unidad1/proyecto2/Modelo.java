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

public class Modelo {

    private RegistradorObjetos registro;

    public Modelo() {
        this.registro = new RegistradorObjetos("operaciones.dat");
    }

    public int factorial(int n) {
        if (n == 0) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    public int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public int ackerman(int m, int n) {
        if (m == 0) {
            return n + 1;
        }
        if (n == 0) {
            return ackerman(m - 1, 1);
        }
        return ackerman(m - 1, ackerman(m, n - 1));
    }

    public RegistradorObjetos getRegistro() {
        return registro;
    }

}
