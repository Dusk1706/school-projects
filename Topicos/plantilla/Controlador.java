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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        crearEscuchadores();
    }

    private void crearEscuchadores() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        
    }
    
}
