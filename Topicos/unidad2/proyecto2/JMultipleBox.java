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

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class JMultipleBox extends JPanel {
    private JRadioButton rbtnCorreos, rbtnRfc, rbtnTelefonos;
    private ButtonGroup grupoBotones;
    private JButton btnNuevaCaja;
    
    private JPanel panelCaja;
    private JScrollPane scroll;
    private JTextField [] cajas;
    private JButton [] btnsBorrar;

    public JMultipleBox() {
        crearInterfaz();

        new ControladorJMultipleBox(this);
    }

    private void crearInterfaz() {
        setLayout(null);

        setBorder(BorderFactory.createLineBorder(Color.BLUE));

        rbtnCorreos = new JRadioButton("Correos");
        rbtnCorreos.setHorizontalAlignment(JRadioButton.LEFT);
        
        rbtnRfc = new JRadioButton("RFC");
        rbtnRfc.setHorizontalAlignment(JRadioButton.CENTER);

        rbtnTelefonos = new JRadioButton("Teléfonos");
        rbtnTelefonos.setHorizontalAlignment(JRadioButton.CENTER);

        grupoBotones = new ButtonGroup();
        grupoBotones.add(rbtnCorreos);
        grupoBotones.add(rbtnRfc);
        grupoBotones.add(rbtnTelefonos);

        btnNuevaCaja = new JButton("Nueva Caja");
        btnNuevaCaja.setHorizontalAlignment(JButton.CENTER);

        panelCaja = new JPanel();
        panelCaja.setLayout(null);
        panelCaja.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        scroll = new JScrollPane(panelCaja);
        
        add(rbtnCorreos);
        add(rbtnRfc);
        add(rbtnTelefonos);
        add(btnNuevaCaja);
        add(panelCaja);
        add(scroll);
    }

    public void crearEscuchadores(ControladorJMultipleBox controlador) {
        btnNuevaCaja.addActionListener(controlador);

        addComponentListener(controlador);
    }

    public void validarTextoCaja(JTextField caja) {
        String texto = caja.getText();
        boolean esValido = false;

        if (esCorreos()) {
            esValido = Rutinas.validarCorreo(texto);
        }
        if (esRfc()) {
            esValido = Rutinas.validarRFC(texto);
        }

        if (esTelefonos()) {
            esValido = Rutinas.validarTelefono(texto);
        }

        cambiarColorCaja(caja, esValido);
    }

    private void cambiarColorCaja(JTextField caja, boolean esValido) {
        if (esValido) {
            caja.setBackground(Color.GREEN);
            return;
        }

        caja.setBackground(Color.RED);
    } 

    
    public void crearCaja(ControladorJMultipleBox controlador) {
        if (!esCriterioSeleccionado()) {
            Rutinas.mensaje("Debe seleccionar un criterio", 0);
            return;
        }

        if (esCajasMaximas()) {
            Rutinas.mensaje("No se pueden crear más cajas", 0);
            return;
        }

        agrandarArregloCajas();

        crearCajaEnPanel(controlador);
    }

    private void agrandarArregloCajas() {
        if (cajas == null) {
            desactivarRadioButtons();
            
            cajas = new JTextField[1];
            btnsBorrar = new JButton[1];

            return;
        }
        
        JTextField [] cajasAux = new JTextField[cajas.length + 1];
        JButton [] btnsBorrarAux = new JButton[cajas.length + 1];
        
        for (int i = 0; i < cajas.length; i++) {
            cajasAux[i] = cajas[i];
            btnsBorrarAux[i] = btnsBorrar[i];
        }

        cajas = cajasAux;
        btnsBorrar = btnsBorrarAux;
    }

    private void crearCajaEnPanel(ControladorJMultipleBox controlador) {
        int indice = cajas.length - 1;

        cajas[indice] = new JTextField();
        btnsBorrar[indice] = new JButton("X");

        cajas[indice].addKeyListener(controlador);
        btnsBorrar[indice].addActionListener(controlador);

        panelCaja.add(cajas[indice]);
        panelCaja.add(btnsBorrar[indice]);
    }

    public void borrarCaja(int indice) {
        panelCaja.remove(cajas[indice]);
        panelCaja.remove(btnsBorrar[indice]);

        if (cajas.length == 1) {
            activarRadioButtons();

            cajas = null;
            btnsBorrar = null;
            return;
        }

        JTextField [] cajasAux = new JTextField[cajas.length - 1];
        JButton [] btnsBorrarAux = new JButton[cajas.length - 1];

        int j = 0;
        for (int i = 0; i < cajas.length; i++) {
            if (i == indice) {
                continue;
            }

            cajasAux[j] = cajas[i];
            btnsBorrarAux[j] = btnsBorrar[i];
            j++;
        }

        cajas = cajasAux;
        btnsBorrar = btnsBorrarAux;

    }

    public void resizeComponentes() {
        int anchoPanel = getWidth();
        int alturaPanel = getHeight();

        cambiarTamanioParteArriba(anchoPanel, alturaPanel);
        cambiarTamanioPanelCaja(anchoPanel, alturaPanel);
        
        revalidate();
        repaint();
    }

    private void cambiarTamanioParteArriba(int anchoPanel, int alturaPanel) {
        int x = getValorPorcentaje(anchoPanel, 5);
        int y = getValorPorcentaje(alturaPanel, 15);

        int ancho = getValorPorcentaje(anchoPanel, 20);
        int alto = getValorPorcentaje(alturaPanel, 7);
        
        btnNuevaCaja.setBounds(x, y, ancho, alto);
        int anchoBtn = btnNuevaCaja.getWidth();
        int fontTamano = getValorPorcentaje(anchoBtn, 11); 
        btnNuevaCaja.setFont(new Font("Arial", Font.BOLD, fontTamano));

        ancho = getValorPorcentaje(anchoPanel, 17);
        y = getValorPorcentaje(alturaPanel, 5);
        rbtnCorreos.setBounds(x, y, ancho, alto);

        x = getValorPorcentaje(anchoPanel, 22);
        rbtnRfc.setBounds(x, y, ancho, alto);

        x = getValorPorcentaje(anchoPanel, 42);
        rbtnTelefonos.setBounds(x, y, ancho, alto);
    }   

    private void cambiarTamanioPanelCaja(int anchoPanel, int alturaPanel) {
        if (cajas == null) {
            return;
        }
        int x = getValorPorcentaje(anchoPanel, 5);
        int y = getValorPorcentaje(alturaPanel, 30);

        int ancho = getValorPorcentaje(anchoPanel, 85);
        int alto = getValorPorcentaje(alturaPanel, 70);

        panelCaja.setBounds(x, y, ancho, alto);
        

        int alturaPanelCaja = alto;
        
        for (int i = 0; i < cajas.length; i++) {
            x = 0;
            y = getValorPorcentaje(alturaPanelCaja, 10) * i;
            ancho = getValorPorcentaje(anchoPanel, 40);
            alto = getValorPorcentaje(alturaPanelCaja, 10);
            
            cajas[i].setBounds(x, y, ancho, alto);
            
            ancho = getValorPorcentaje(anchoPanel, 20);
            x = getValorPorcentaje(ancho, 70);
            btnsBorrar[i].setBounds(x, y, ancho, alto);
        }
    }

    private int getValorPorcentaje(int valor, int porcentaje) {
        return valor * porcentaje / 100;
    }

    public void activarRadioButtons() {
        rbtnCorreos.setEnabled(true);
        rbtnRfc.setEnabled(true);
        rbtnTelefonos.setEnabled(true);
    }

    public void desactivarRadioButtons() {
        rbtnCorreos.setEnabled(false);
        rbtnRfc.setEnabled(false);
        rbtnTelefonos.setEnabled(false);
    }

    public boolean esCajasMaximas() {
        return esCajasCreadas() && cajas.length == 10;
    }

    public boolean esCajasCreadas() {
        return cajas != null;
    }

    public boolean esCriterioSeleccionado() {
        return rbtnCorreos.isSelected() || rbtnRfc.isSelected() || rbtnTelefonos.isSelected();
    }

    public boolean esCorreos() {
        return rbtnCorreos.isSelected();
    }

    public boolean esRfc() {
        return rbtnRfc.isSelected();
    }

    public boolean esTelefonos() {
        return rbtnTelefonos.isSelected();
    }

    public JButton getBtnNuevaCaja() {
        return btnNuevaCaja;
    }

    public JButton [] getBtnsBorrar() {
        return btnsBorrar;
    }

    public String [] getTextCajas() {
        if (cajas == null) {
            return null;
        }

        String [] textos = new String[cajas.length];

        for (int i = 0; i < cajas.length; i++) {
            textos[i] = cajas[i].getText();
        }

        return textos;
    }

    public String getTextCaja(int indice) {
        if (cajas == null) {
            return null;
        }

        return cajas[indice].getText();
    }

}
