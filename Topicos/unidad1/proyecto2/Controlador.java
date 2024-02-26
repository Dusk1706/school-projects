/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 1
 * Proyecto: Teclado de autorizacion
 * Horario: 9:00 a 10:00
 * Fecha: 23/02/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad1.proyecto2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.Set;

public class Controlador implements ActionListener, ItemListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Modelo modelo, Vista vista) {
        this.vista = vista;
        this.modelo = modelo;
        crearEscuchadores();
        llenarComboBox();
        imprimirCuentas();
    }

    private void crearEscuchadores() {
        vista.getInputImporte().addActionListener(this);
        vista.getInputNip().addActionListener(this);
        vista.getBtnAutorizarPago().addActionListener(this);

        for (int i = 0; i < 10; i++) {
            vista.getTecladoNumerico()[i].addActionListener(this);
        }

        vista.getCmbOpcionesNoTarjeta().addItemListener(this);
    }

    private void llenarComboBox() {
        Set<String> tarjetas = modelo.getCuentas().keySet();
        for (String tarjeta : tarjetas) {
            vista.getCmbOpcionesNoTarjeta().addItem(tarjeta);
        }
    }

    private void imprimirCuentas() {
        Set<String> tarjetas = modelo.getCuentas().keySet();
        for (String tarjeta : tarjetas) {
            System.out.println(tarjeta + " " + modelo.getCuentas().get(tarjeta));
        }
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            vista.getPanelCentro().setVisible(false);
            vista.getInputNip().setText("");
            vista.getInputImporte().setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof JTextField) {
            if (validarInputUsuario()) {
                randomizarTecladoNumerico();
                vista.getPanelCentro().setVisible(true);
            }
            return;
        }

        JButton aux = (JButton) evt.getSource();
        if (aux == vista.getBtnAutorizarPago()) {
            validarAutorizacionPago();
        } else {
            procesarTeclaNumerica(aux);
        }
    }

    private boolean validarInputUsuario() {
        String mensajeError = "Ingrese valores validos";
        try {
            int cantidad = obtenerCantidadRetiro();
            String noTarjeta = obtenerTarjetaSeleccionada();
            if (cantidad < 1 || noTarjeta.equals("Seleccione")) {
                vista.ocultarPanelCentro(mensajeError, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            vista.ocultarPanelCentro(mensajeError, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void randomizarTecladoNumerico() {
        JButton[] teclado = vista.getTecladoNumerico();

        for (int i = teclado.length - 1; i > 0; i--) {
            int indiceAleatorio = Rutinas.nextInt(0, i);
            String aux = teclado[i].getText();

            teclado[i].setText(teclado[indiceAleatorio].getText());
            teclado[indiceAleatorio].setText(aux);
        }
    }

    private void procesarTeclaNumerica(JButton boton) {
        String nip = vista.getInputNip().getText() + boton.getText();
        if (nip.length() > 4) {
            return;
        }
        vista.getInputNip().setText(nip);
    }

    private void validarAutorizacionPago() {
        try {
            String noTarjeta = obtenerTarjetaSeleccionada();
            int nip = obtenerNipIngresado();

            if (modelo.verificarNip(noTarjeta, nip)) {
                realizarRetiro(noTarjeta);
            } else {
                vista.ocultarPanelCentro("NIP incorrecto", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            vista.ocultarPanelCentro("Ingrese un valor valido", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarRetiro(String noTarjeta) {
        int cantidad = obtenerCantidadRetiro();
        String mensaje;
        if (modelo.retirarDinero(noTarjeta, cantidad)) {
            mensaje = "Retiro exitoso\n" + obtenerSaldo(noTarjeta);
            vista.ocultarPanelCentro(mensaje, JOptionPane.INFORMATION_MESSAGE);
        } else {
            mensaje = "No tiene saldo suficiente\n" + obtenerSaldo(noTarjeta);
            vista.ocultarPanelCentro(mensaje, JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerSaldo(String noTarjeta) {
        return "Saldo restante: " + modelo.getCuentas().get(noTarjeta).getSaldo();
    }

    private String obtenerTarjetaSeleccionada() {
        return (String) vista.getCmbOpcionesNoTarjeta().getSelectedItem();
    }

    private int obtenerCantidadRetiro() throws NumberFormatException {
        return Integer.parseInt(vista.getInputImporte().getText());
    }

    private int obtenerNipIngresado() throws NumberFormatException {
        return Integer.parseInt(vista.getInputNip().getText());
    }

}
