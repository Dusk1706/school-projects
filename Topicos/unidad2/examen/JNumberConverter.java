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

import javax.swing.JPanel;
import javax.swing.JTextField;

public class JNumberConverter extends JPanel {
    private JTextField inputDecimal, inputBinario, inputHexadecimal;

    public JNumberConverter() {
        crearInterfaz();

        new Controlador(this);
    }

    private void crearInterfaz() {
        setLayout(null);

        inputDecimal = new JTextField();
        inputBinario = new JTextField();
        inputHexadecimal = new JTextField();

        add(inputDecimal);
        add(inputBinario);
        add(inputHexadecimal);
    }

    public void crearEscuchadores(Controlador controlador) {
        inputDecimal.addKeyListener(controlador);
        inputBinario.addKeyListener(controlador);
        inputHexadecimal.addKeyListener(controlador);

        addComponentListener(controlador);
    }

    public void cambiarConversionesDecimal() {
        String decimalTxt = getDecimal();

        if (decimalTxt.isEmpty()) {
            inputBinario.setText("");
            inputHexadecimal.setText("");
            return;
        }
        int decimal = Integer.parseInt(decimalTxt);

        String binario = Rutinas.decimalABinario(decimal);
        String hexadecimal = Rutinas.decimalAHexadecimal(decimal);

        inputBinario.setText(binario);
        inputHexadecimal.setText(hexadecimal);
    }

    public void cambiarConversionesBinario() {
        String binarioTxt = getBinario();

        if (binarioTxt.isEmpty()) {
            inputDecimal.setText("");
            inputHexadecimal.setText("");
            return;
        }

        String decimal = Rutinas.binarioADecimal(binarioTxt);
        String hexadecimal = Rutinas.binarioAHexadecimal(binarioTxt);

        inputDecimal.setText(decimal);
        inputHexadecimal.setText(hexadecimal);
    }

    public void cambiarConversionesHexadecimal() {
        String hexadecimalTxt = getHexadecimal();

        if (hexadecimalTxt.isEmpty()) {
            inputDecimal.setText("");
            inputBinario.setText("");
            return;
        }

        String decimal = Rutinas.hexadecimalADecimal(hexadecimalTxt);
        String binario = Rutinas.hexadecimalABinario(hexadecimalTxt);

        inputDecimal.setText(decimal);
        inputBinario.setText(binario);
    }

    public void resizeComponentes() {
        int ancho = getWidth();
        int alto = getHeight();

        int x = Rutinas.getValorPorcentaje(ancho, 5);
        
        int anchoComponente = Rutinas.getValorPorcentaje(ancho, 50);
        int altoComponente = Rutinas.getValorPorcentaje(alto, 10);
        
        int y = Rutinas.getValorPorcentaje(alto, 10);
        inputDecimal.setBounds(x, y, anchoComponente, altoComponente);
        
        y = Rutinas.getValorPorcentaje(alto, 22);
        inputBinario.setBounds(x, y, anchoComponente, altoComponente);
        
        y = Rutinas.getValorPorcentaje(alto, 34);
        inputHexadecimal.setBounds(x, y, anchoComponente, altoComponente);
    }

    public JTextField getInputDecimal() {
        return inputDecimal;
    }

    public JTextField getInputBinario() {
        return inputBinario;
    }

    public JTextField getInputHexadecimal() {
        return inputHexadecimal;
    }

    public String getDecimal() {
        return inputDecimal.getText();
    }

    public String getBinario() {
        return inputBinario.getText();
    }

    public String getHexadecimal() {
        return inputHexadecimal.getText();
    }
}
