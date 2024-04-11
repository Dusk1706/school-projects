package unidad2.componentes;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;

public class JDateField extends JTextField implements KeyListener {
    
    private final int POSICION_PRIMER_SLASH;
    private final int POSICION_SEGUNDO_SLASH;
    private final String formato;

    public JDateField() {
        this("dd/mm/yyyy");
    }

    public JDateField(String formato) {
        this.formato = formato;
        POSICION_PRIMER_SLASH = formato.indexOf("/");
        POSICION_SEGUNDO_SLASH = formato.lastIndexOf("/");
        crearInterfaz();
        crearEscuchadores();
    }

    private void crearInterfaz() {
        setText(formato);
        setHighlighter(null);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setHorizontalAlignment(JTextField.CENTER);
    }

    private void crearEscuchadores() {
        addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        int tecla = evt.getKeyCode();
        if (evt.isControlDown()) {
            evt.consume();
        }
        if (validarBorrarCaracter(tecla)) {
            evt.consume();
        }
   
    }

    @Override
    public void keyReleased(KeyEvent evt) {

    }

    @Override
    public void keyTyped(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        int posicionCursor = getCaretPosition();
        int longitud = getText().length();
        evt.consume();

        if (Character.isDigit(caracter) && posicionCursor < longitud) {
            remplazarCaracter(posicionCursor, caracter);
            siguientePosicionCursor(posicionCursor);
        }
        if (posicionCursor == longitud - 1 && !validarFecha()) {
            validarFecha();
        }

    }

    private boolean validarFecha() {
        String fecha = getText();
        SimpleDateFormat dateFormato = new SimpleDateFormat(formato);
	    dateFormato.setLenient(false);
	    
	    try {
	        dateFormato.parse(fecha); 
	        return true;
	    }catch (ParseException e) {
            setText(formato);
            setCaretPosition(0);
	        return false;
	    }
    }

    private boolean validarBorrarCaracter(int tecla) {
        int posicionCursor = getCaretPosition() - 1;

        if (!esTeclaBorrar(tecla) || posicionCursor < 0) {
            return false;
        }
        
        if (esCaracterSlash(posicionCursor)) {
            posicionCursor--;
        }
        
        char nuevoCaracter = formato.charAt(posicionCursor);
        
        remplazarCaracter(posicionCursor, nuevoCaracter);
        setCaretPosition(posicionCursor);
        
        return true;
    }

    private boolean esTeclaBorrar(int tecla) {
        return tecla == KeyEvent.VK_BACK_SPACE;
    }

    private boolean esCaracterSlash(int posicionCursor) {
        return (
            posicionCursor == POSICION_PRIMER_SLASH || 
            posicionCursor == POSICION_SEGUNDO_SLASH
        );
    }

    private void remplazarCaracter(int posicionCursor, char caracter) {
        String texto = getText();
        
        texto = texto.substring(0, posicionCursor) 
            + caracter 
            + texto.substring(posicionCursor + 1);

        setText(texto);
    }

    private void siguientePosicionCursor(int posicionCursor) {
        int siguientePosicion = posicionCursor + 1;
        if (esCaracterSlash(siguientePosicion)) {
            siguientePosicion++;
        }
        setCaretPosition(siguientePosicion);
    }

    public String getFecha() {
        return getText();
    }
}
