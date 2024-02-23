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

import javax.swing.JButton;
import javax.swing.JTextField;

import java.util.Set;

public class Controlador implements ActionListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Modelo modelo, Vista vista) {
        this.vista = vista;
        this.modelo = modelo;
        crearEscuchadores();
        llenarComboBox();
    }

    private void crearEscuchadores() {
        vista.getInputImporte().addActionListener(this);
        vista.getInputNip().addActionListener(this);
        vista.getAutorizarPago().addActionListener(this);
        for (int i = 0; i < 10; i++) {
            vista.getTecladoNumerico()[i].addActionListener(this);
        }
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

    private void llenarComboBox() {
        Set<String> tarjetas = modelo.getCuentas().keySet();
        for (String tarjeta : tarjetas) {
            vista.getOpcionesNoTarjeta().addItem(tarjeta);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof JTextField) {
            if (evt.getSource() == vista.getInputImporte() && validarInputUsuario()) {
                randomizarTecladoNumerico();
                vista.getPanelCentro().setVisible(true);
            }
            return;
        }

        JButton aux = (JButton) evt.getSource();
        if (aux == vista.getAutorizarPago()) {
            validarAutorizacionPago();
        } else {
            String nip = vista.getInputNip().getText() + aux.getText();
            if (nip.length() > 4) {
                return;
            }
            vista.getInputNip().setText(nip);
        }
    }

    private boolean validarInputUsuario() {
        try {
            int cantidad = obtenerCantidadRetiro();
            String noTarjeta = obtenerTarjetaSeleccionada();

            if (cantidad < 1 || noTarjeta.equals("Seleccione")) {
                vista.ocultarPanelCentro("Ingrese valores validos", 0);
                return false;
            }
        } catch (NumberFormatException e) {
            vista.ocultarPanelCentro("Ingrese valores validos", 0);
            return false;
        }
        return true;
    }

    private void validarAutorizacionPago() {
        try {
            String noTarjeta = obtenerTarjetaSeleccionada();
            int nip = obtenerNipIngresado();
            int cantidad = obtenerCantidadRetiro();

            if (modelo.verificarNip(noTarjeta, nip)) {
                if (modelo.retirarDinero(noTarjeta, cantidad)) {
                    String mensaje = "Retiro exitoso\n" + obtenerSaldo(noTarjeta);
                    vista.ocultarPanelCentro(mensaje, 1);
                } else {
                    vista.ocultarPanelCentro("No tiene saldo suficiente", 0);
                }
            } else {
                vista.ocultarPanelCentro("NIP incorrecto", 0);
            }
        } catch (NumberFormatException e) {
            vista.ocultarPanelCentro("Ingrese un valor valido", 0);
        }
    }

    private String obtenerSaldo(String noTarjeta) {
        return "Saldo restante: " + modelo.getCuentas().get(noTarjeta).getSaldo();
    }
    
    private String obtenerTarjetaSeleccionada() {
        return (String) vista.getOpcionesNoTarjeta().getSelectedItem();
    }
    
    private int obtenerCantidadRetiro() throws NumberFormatException {
        return Integer.parseInt(vista.getInputImporte().getText());
    }

    private int obtenerNipIngresado() throws NumberFormatException {
        return Integer.parseInt(vista.getInputNip().getText());
    }

}
