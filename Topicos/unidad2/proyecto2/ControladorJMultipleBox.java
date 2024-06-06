/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 2
 * Proyecto: Componentes
 * Horario: 9:00 a 10:00
 * Fecha: 11/04/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad2.proyecto2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class ControladorJMultipleBox implements ActionListener, ComponentListener, KeyListener {

    private JMultipleBox vista;
    
    public ControladorJMultipleBox(JMultipleBox vista) {
        this.vista = vista;
        vista.crearEscuchadores(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnNuevaCaja()) {
            vista.crearCaja(this);
            vista.resizeComponentes();
            return;
        }

        for (int i = 0; i < vista.getBtnsBorrar().length; i++) {
            if (e.getSource() == vista.getBtnsBorrar()[i]) {
                vista.borrarCaja(i);
                vista.resizeComponentes();
                return;
            }
        }
    }

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {
        vista.resizeComponentes();
    }

    @Override
    public void componentShown(ComponentEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        vista.validarTextoCaja((JTextField) e.getSource());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
