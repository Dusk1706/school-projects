/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Ingenieria Web
 * Unidad: 1
 * Proyecto: Persistencia de objetos
 * Horario: 10:00 a 11:00
 * Fecha: 10/02/2025
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package proyecto1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class JDialogRegistro extends JDialog {
    private JButton btnAgregarMemoria;

    private JTextField tfMarca, tfModelo, tfAnio, tfColor, tfPlaca;
    private JTextField tfTipo, tfCilindrada, tfPotencia, tfCombustible, tfSerial;

    private JButton btnGuardarComposicion, btnGuardarAgregacion;
    private JRadioButton rbNuevoMotor, rbMotorExistente;
    private JComboBox<Motor> comboMotores;

    public JDialogRegistro(JFrame parent) {
        super(parent, "Registro de Vehículo", true);
        setSize(700, 500);
        setLayout(new BorderLayout(10, 10));

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelDatos = new JPanel(new GridLayout(1, 2, 20, 0));
        panelDatos.add(crearPanelCarro());
        panelDatos.add(crearPanelMotor());

        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        panelPrincipal.add(crearPanelBotones(), BorderLayout.SOUTH);

        add(panelPrincipal);
        setLocationRelativeTo(parent);
    }

    private JPanel crearPanelCarro() {
        JPanel panelCarro = crearPanelSeccion("Datos del Carro", Color.BLUE);
        panelCarro.setLayout(new GridLayout(5, 2, 5, 8));

        tfMarca = crearTextField();
        tfModelo = crearTextField();
        tfAnio = crearTextField();
        tfColor = crearTextField();
        tfPlaca = crearTextField();

        agregarCampo(panelCarro, "Marca:", tfMarca);
        agregarCampo(panelCarro, "Modelo:", tfModelo);
        agregarCampo(panelCarro, "Año:", tfAnio);
        agregarCampo(panelCarro, "Color:", tfColor);
        agregarCampo(panelCarro, "Placa:", tfPlaca);

        return panelCarro;
    }

    private JPanel crearPanelMotor() {
        JPanel panelMotor = crearPanelSeccion("Datos del Motor", new Color(0, 128, 0));
        panelMotor.setLayout(new BorderLayout());

        JPanel panelOpciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbNuevoMotor = new JRadioButton("Nuevo Motor");
        rbMotorExistente = new JRadioButton("Motor Existente");
        ButtonGroup grupoOpciones = new ButtonGroup();
        grupoOpciones.add(rbNuevoMotor);
        grupoOpciones.add(rbMotorExistente);
        rbNuevoMotor.setSelected(true);

        panelOpciones.add(rbNuevoMotor);
        panelOpciones.add(rbMotorExistente);

        JPanel panelContenedor = new JPanel(new CardLayout());
        panelContenedor.add(crearPanelNuevoMotor(), "NUEVO");
        panelContenedor.add(crearPanelMotorExistente(), "EXISTENTE");

        rbNuevoMotor.addActionListener(e -> {
            ((CardLayout) panelContenedor.getLayout()).show(panelContenedor, "NUEVO");
        });
        rbMotorExistente.addActionListener(e -> {
            ((CardLayout) panelContenedor.getLayout()).show(panelContenedor, "EXISTENTE");
        });

        panelMotor.add(panelOpciones, BorderLayout.NORTH);
        panelMotor.add(panelContenedor, BorderLayout.CENTER);

        return panelMotor;
    }

    private JPanel crearPanelNuevoMotor() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 8));

        tfTipo = crearTextField();
        tfCilindrada = crearTextField();
        tfPotencia = crearTextField();
        tfCombustible = crearTextField();
        tfSerial = crearTextField();

        agregarCampo(panel, "Tipo:", tfTipo);
        agregarCampo(panel, "Cilindrada (cc):", tfCilindrada);
        agregarCampo(panel, "Potencia (HP):", tfPotencia);
        agregarCampo(panel, "Combustible:", tfCombustible);
        agregarCampo(panel, "N° Serial:", tfSerial);

        panel.add(new JLabel());

        btnAgregarMemoria = crearBoton("Agregar a memoria", new Color(0, 128, 0));
        panel.add(btnAgregarMemoria);

        return panel;
    }

    private JTextField crearTextField() {
        JTextField campo = new JTextField(15);
        campo.setFont(new Font("Arial", Font.PLAIN, 15));
        return campo;
    }

    public void agregarMotorAMemoria() {
        String tipo = tfTipo.getText();
        String cilindrada = tfCilindrada.getText();
        String potencia = tfPotencia.getText();
        String combustible = tfCombustible.getText();
        String serial = tfSerial.getText();
    
        if (hayCamposVaciosMotor()) {
            Vista.mostrarMensaje("Llene todos los campos del motor.", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        Motor nuevoMotor = new Motor(tipo, cilindrada, potencia, combustible, serial);
        comboMotores.addItem(nuevoMotor);
    }

    private JPanel crearPanelMotorExistente() {
        JPanel panel = new JPanel(new BorderLayout());
        comboMotores = new JComboBox<Motor>();
        panel.add(comboMotores, BorderLayout.NORTH);
        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        btnGuardarComposicion = crearBoton("Guardar Composicion", new Color(0, 128, 0));
        btnGuardarAgregacion = crearBoton("Guardar Agregacion", new Color(139, 34, 34));

        panelBotones.add(btnGuardarComposicion);
        panelBotones.add(btnGuardarAgregacion);

        return panelBotones;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        return boton;
    }

    private JPanel crearPanelSeccion(String titulo, Color color) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(color, 1),
                titulo,
                TitledBorder.LEFT,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 15),
                color));
        return panel;
    }

    private void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(label);
        panel.add(campo);
    }

    public void limpiarCampos() {
        tfMarca.setText("");
        tfModelo.setText("");
        tfAnio.setText("");
        tfColor.setText("");
        tfPlaca.setText("");
        tfTipo.setText("");
        tfCilindrada.setText("");
        tfPotencia.setText("");
        tfCombustible.setText("");
        tfSerial.setText("");
    }

    public boolean hayCamposVacios() {
        return hayCamposVaciosCarro() || hayCamposVaciosMotor();
    }

    public boolean hayMotoresEnMemoria() {
        return comboMotores.getItemCount() > 0;
    }

    public boolean hayCamposVaciosCarro() {
        return tfMarca.getText().isEmpty() || tfModelo.getText().isEmpty() || tfAnio.getText().isEmpty() ||
                tfColor.getText().isEmpty() || tfPlaca.getText().isEmpty();
    }

    public boolean hayCamposVaciosMotor() {
        return tfTipo.getText().isEmpty() || tfCilindrada.getText().isEmpty() || tfPotencia.getText().isEmpty() ||
                tfCombustible.getText().isEmpty() || tfSerial.getText().isEmpty();
    }

    public boolean esOpcionNuevoMotor() {
        return rbNuevoMotor.isSelected();
    }

    public boolean esOpcionMotorExistente() {
        return rbMotorExistente.isSelected();
    }

    public Motor getMotorSeleccionado() {
        return (Motor) comboMotores.getSelectedItem();
    }

    public JRadioButton getRbNuevoMotor() {
        return rbNuevoMotor;
    }

    public JRadioButton getRbMotorExistente() {
        return rbMotorExistente;
    }

    public JButton getBtnAgregarMemoria() {
        return btnAgregarMemoria;
    }

    public JButton getBtnGuardarComposicion() {
        return btnGuardarComposicion;
    }

    public JButton getBtnGuardarAgregacion() {
        return btnGuardarAgregacion;
    }

    public JComboBox<Motor> getComboMotores() {
        return comboMotores;
    }

    public String getMarca() {
        return tfMarca.getText();
    }

    public String getModelo() {
        return tfModelo.getText();
    }

    public String getAnio() {
        return tfAnio.getText();
    }

    public String getColor() {
        return tfColor.getText();
    }

    public String getPlaca() {
        return tfPlaca.getText();
    }

    public String getTipoMotor() {
        return tfTipo.getText();
    }

    public String getCilindrada() {
        return tfCilindrada.getText();
    }

    public String getPotencia() {
        return tfPotencia.getText();
    }

    public String getCombustible() {
        return tfCombustible.getText();
    }

    public String getSerial() {
        return tfSerial.getText();
    }

}