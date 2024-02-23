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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class Vista extends JFrame {
    private JPanel panelArriba, panelCentro, panelNumerico;
    private JLabel txtNoTarjeta, txtImporte;
    private JComboBox<String> opcionesNoTarjeta;
    private JTextField inputImporte;
    private JPasswordField inputNip;
    private JButton[] tecladoNumerico;
    private JButton autorizarPago;

    public Vista() {
        super("Autorizacion de compra");
        crearVista();
    }

    public void crearVista() {
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        crearPanelArriba();
        crearPanelCentro();

    }

    private void crearPanelArriba() {
        panelArriba = new JPanel();
        panelArriba.setPreferredSize(new Dimension(700, 50));

        txtNoTarjeta = new JLabel("No. Tarjeta");
        txtNoTarjeta.setFont(new Font("Arial", Font.BOLD, 20));

        opcionesNoTarjeta = new JComboBox<String>();
        opcionesNoTarjeta.setPreferredSize(new Dimension(250, 30));
        opcionesNoTarjeta.addItem("Seleccione");

        txtImporte = new JLabel("Importe");
        txtImporte.setFont(new Font("Arial", Font.BOLD, 20));

        inputImporte = new JTextField(10);
        inputImporte.setPreferredSize(new Dimension(100, 30));

        panelArriba.add(txtNoTarjeta);
        panelArriba.add(opcionesNoTarjeta);
        panelArriba.add(txtImporte);
        panelArriba.add(inputImporte);

        add(panelArriba, BorderLayout.NORTH);
    }

    private void crearPanelCentro() {
        panelCentro = new JPanel(new BorderLayout());
        panelCentro.setVisible(false);

        inputNip = new JPasswordField(4);
        inputNip.setEchoChar('*');
        inputNip.setFont(new Font("Arial", Font.PLAIN, 30));
        inputNip.setHorizontalAlignment(JTextField.CENTER);
        inputNip.setPreferredSize(new Dimension(300, 50));
        inputNip.setEditable(false);

        crearPanelNumerico();
        
        autorizarPago = new JButton("Autorizar");
        autorizarPago.setPreferredSize(new Dimension(300, 50));
        
        panelCentro.add(inputNip, BorderLayout.NORTH);
        panelCentro.add(panelNumerico, BorderLayout.CENTER);
        panelCentro.add(autorizarPago, BorderLayout.SOUTH);

        add(panelCentro, BorderLayout.CENTER);
    }

    public void crearPanelNumerico() {
        panelNumerico = new JPanel(new GridLayout(4, 3));

        tecladoNumerico = new JButton[10];
        for (int i = 0; i < 10; i++) {
            tecladoNumerico[i] = new JButton(String.valueOf(i));
            if (i == 9) {
                panelNumerico.add(new JLabel());
            }
            panelNumerico.add(tecladoNumerico[i]);
        }
    }

    public void ocultarPanelCentro(String mensaje, int tipo) {
        Rutinas.mensaje(mensaje, tipo);
        opcionesNoTarjeta.setSelectedIndex(0);
        inputImporte.setText("");
        inputNip.setText("");
        panelCentro.setVisible(false);
    }

    public JPanel getPanelCentro() {
        return panelCentro;
    }

    public JButton getAutorizarPago() {
        return autorizarPago;
    }

    public JComboBox<String> getOpcionesNoTarjeta() {
        return opcionesNoTarjeta;
    }

    public JTextField getInputImporte() {
        return inputImporte;
    }

    public JTextField getInputNip() {
        return inputNip;
    }

    public JButton[] getTecladoNumerico() {
        return tecladoNumerico;
    }

}
