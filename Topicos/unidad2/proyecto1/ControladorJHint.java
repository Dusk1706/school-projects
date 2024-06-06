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

package unidad2.proyecto1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControladorJHint implements ActionListener, ComponentListener, KeyListener {

    private ModeloJHint modelo;
    private JHint vista;

    public ControladorJHint(ModeloJHint modelo, JHint vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.crearEscuchadores(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == vista.getRbtnFontOriginal()) {
            vista.cambiarAFontOriginal();
        } else if (evt.getSource() == vista.getRbtnFontAleatorio()) {
            vista.cambiarAFontAleatorio();
        }
    }

    @Override
    public void componentResized(ComponentEvent evt) {
        vista.resizeComponentes();
    }

    @Override
    public void componentMoved(ComponentEvent evt) {}

    @Override
    public void componentShown(ComponentEvent evt) {}

    @Override
    public void componentHidden(ComponentEvent evt) {}

    @Override
    public void keyPressed(KeyEvent evt) {
        int tecla = evt.getKeyCode();

        evt.consume();

        if (tecla != KeyEvent.VK_BACK_SPACE) {
            return;
        }

        String texto = vista.getText();
        System.out.println("Valor "+ vista.getText());
        if (texto.length() > 1) {
            texto = texto.substring(0, texto.length() - 1);
            vista.setText(texto);
        }else {
            vista.restaurarHint();
        }

    }

    @Override
    public void keyReleased(KeyEvent evt) {
        String texto = vista.getText();

        if (texto.length() == 0) {
            return;
        }

        String hint = modelo.buscarHint(texto);
        if (hint != null) {
            vista.asignarImagen(hint);
        }else {
            vista.borrarImagen();
        }
    }

    @Override
    public void keyTyped(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        
        evt.consume();

        if (!validarCaracter(caracter)) {
            return;
        }
        System.out.println("Valor keytyped "+ vista.getText());

        String texto = vista.getText();

        if (texto.length() == 0) {
            vista.quitarHint();
        }

        vista.setText(texto + caracter);

    }

    private boolean validarCaracter(char caracter) {
        return Character.isLetter(caracter) || Character.isDigit(caracter) 
        || caracter == ' ' || caracter == '.';
    }


}