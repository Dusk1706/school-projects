/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 1
 * Proyecto: 
 * Horario: 9:00 a 10:00
 * Fecha: 8/03/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package plantilla;

import javax.swing.JFrame;

public class Vista extends JFrame {
    
    public Vista() {
        super("Titulo");
        crearInterfaz();
    }

    private void crearInterfaz() {
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
}
