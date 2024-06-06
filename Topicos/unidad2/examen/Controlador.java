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

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controlador implements ComponentListener, KeyListener {

    private JNumberConverter vista;
    
    public Controlador(JNumberConverter vista) {
        this.vista = vista;
        vista.crearEscuchadores(this);
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
    public void keyPressed(KeyEvent evt) {}

    @Override
    public void keyReleased(KeyEvent evt) {
        if (evt.getSource() == vista.getInputDecimal()) {
            vista.cambiarConversionesDecimal();
        } else if (evt.getSource() == vista.getInputBinario()) {
            vista.cambiarConversionesBinario();
        } else if (evt.getSource() == vista.getInputHexadecimal()) {
            vista.cambiarConversionesHexadecimal();
        }
    }

    @Override
    public void keyTyped(KeyEvent evt) {
        char caracter = evt.getKeyChar();

        if (evt.getSource() == vista.getInputDecimal()) {
            if (!Rutinas.esNumero(caracter)) {
                evt.consume();
            }
            return;
        }
        if (evt.getSource() == vista.getInputBinario()) {
            if (!Rutinas.esBinario(caracter)) {
                evt.consume();
            }
            return;
        }
        
        if (evt.getSource() == vista.getInputHexadecimal()) {
            if (!Rutinas.esHexadecimal(caracter)) {
                evt.consume();
            }
            return;
        }
    }

}
