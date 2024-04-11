package unidad2.componentes;

import javax.swing.JTextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JDecimalField extends JTextField implements KeyListener {

    public JDecimalField() {
        crearInterfaz();
        crearEscuchadores();
    }

    private void crearInterfaz() {
        setHighlighter(null);
    }

    private void crearEscuchadores() {
        addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        int tecla = evt.getKeyCode();
        if(!validarTecla(tecla)) {
            evt.consume();
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        
    }

    @Override
    public void keyTyped(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        String texto = getText();

        if (!validarCaracter(caracter)) {
            evt.consume();
            return;
        }

        if (caracter == '-') {
            evt.consume();
            setText('-' + texto);
            return;
        }
    }

    private boolean validarTecla(int tecla) {
        return tecla == KeyEvent.VK_BACK_SPACE;
    }

    private boolean validarCaracter(char caracter) {
        return (
            Character.isDigit(caracter) || 
            validarCaracterMenos(caracter) ||
            validarCaracterPunto(caracter)
        );
    }

    private boolean validarCaracterMenos(char caracter) {
        String texto = getText();
        boolean existeCaracterMenos = texto.indexOf('-') > -1;
        return caracter == '-' && !existeCaracterMenos;
    }

    private boolean validarCaracterPunto(char caracter) {
        String texto = getText();
        boolean existeCaracterPunto = texto.indexOf('.') > -1;
        return caracter == '.' && !existeCaracterPunto;
    }

    public double getValor() {
        String texto = getText();
        return Double.parseDouble(texto);
    }

}
