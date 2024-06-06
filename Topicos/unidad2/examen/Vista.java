/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 2
 * Proyecto: Examen
 * Horario: 9:00 a 10:00
 * Fecha: 19/04/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad2.examen;

import javax.swing.JFrame;

public class Vista extends JFrame {
    
    public Vista() {
        super("Titulo");
        crearInterfaz();
    }

    private void crearInterfaz() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JNumberConverter c = new JNumberConverter();
        add(c);
        
        setVisible(true);

    }

    public static void main(String[] args) {
        new Vista();
    }
}
