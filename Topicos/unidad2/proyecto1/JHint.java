package unidad2.proyecto1;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import java.util.Random;

public class JHint extends JPanel {

    private JLabel lblHint;
    private JTextField inputHint;
    private JRadioButton rbtnFontOriginal, rbtnFontAleatorio;
    private ButtonGroup grupoBotones;
    
    private String leyendaHint, txtInputHint;
    private String [] fontDisponibles;
    private Font fontOriginal;

    public JHint(String leyendaHint) {
        
        this.leyendaHint = leyendaHint;

        crearInterfaz();

        new ControladorJHint(new ModeloJHint(), this);
    }

    private void crearInterfaz() {
        setLayout(null);

        fontDisponibles = GraphicsEnvironment.getLocalGraphicsEnvironment()
                                             .getAvailableFontFamilyNames();
        lblHint = new JLabel();
        lblHint.setForeground(Color.RED);
        lblHint.setHorizontalAlignment(JLabel.LEFT);

        fontOriginal = new Font("Arial", Font.PLAIN, 20);

        inputHint = new JTextField();
        inputHint.setText(leyendaHint);
        inputHint.setFont(fontOriginal);

        txtInputHint = "";

        rbtnFontOriginal = new JRadioButton("Font original");
        rbtnFontOriginal.setForeground(Color.BLUE);

        rbtnFontAleatorio = new JRadioButton("Font aleatorio");
        rbtnFontAleatorio.setForeground(Color.BLUE);
        
        grupoBotones = new ButtonGroup();
        grupoBotones.add(rbtnFontOriginal);
        grupoBotones.add(rbtnFontAleatorio);
        
        add(lblHint);
        add(inputHint);
        add(rbtnFontOriginal);
        add(rbtnFontAleatorio);
    }

    public void crearEscuchadores(ControladorJHint controlador) {
        inputHint.addKeyListener(controlador);
        
        rbtnFontOriginal.addActionListener(controlador);
        rbtnFontAleatorio.addActionListener(controlador);
        
        addComponentListener(controlador);
    }

    public void restaurarHint() {
        lblHint.setText("");
        borrarImagen();

        txtInputHint = "";

        inputHint.setText(leyendaHint);
    }

    public void quitarHint() {
        lblHint.setText(leyendaHint);

        inputHint.setText(txtInputHint);
    }

    public void borrarImagen() {
        lblHint.setIcon(null);
    }

    public void asignarImagen(String urlImagen) {
        int ancho = inputHint.getWidth() / 3;
        int altura = inputHint.getHeight();

        urlImagen = "imagenes/" + urlImagen;
        ImageIcon imagen = Rutinas.AjustarImagen(urlImagen, ancho, altura);
        lblHint.setIcon(imagen);
    }

    public void cambiarAFontOriginal() {
        inputHint.setFont(fontOriginal);
    }

    public void cambiarAFontAleatorio() {
        Random random = new Random();
        
        int indice = random.nextInt(fontDisponibles.length);
        Font fontAleatorio = new Font(fontDisponibles[indice], Font.PLAIN, 20);
        
        inputHint.setFont(fontAleatorio);
    }

    public void resizeComponentes() {
        int anchoPanel = getWidth();
        int alturaPanel = getHeight();

        cambiarTamanioLabelHint(anchoPanel, alturaPanel);
        cambiarTamanioInputHint(anchoPanel, alturaPanel);
        cambiarTamanioRadioBtns(anchoPanel, alturaPanel);
    }

    private void cambiarTamanioLabelHint(int anchoPanel, int alturaPanel) {
        int x = getValorPorcentaje(anchoPanel, 10);
        int y = getValorPorcentaje(alturaPanel, 5);
        
        int ancho = getValorPorcentaje(anchoPanel, 40);
        int alto = getValorPorcentaje(alturaPanel, 10);

        lblHint.setBounds(x, y, ancho, alto);
    }

    private void cambiarTamanioInputHint(int anchoPanel, int alturaPanel) {
        int x = getValorPorcentaje(anchoPanel, 10);
        int y = getValorPorcentaje(alturaPanel, 15);
        
        int ancho = getValorPorcentaje(anchoPanel, 40);
        int alto = getValorPorcentaje(alturaPanel, 10);

        inputHint.setBounds(x, y, ancho, alto);
    }

    private void cambiarTamanioRadioBtns(int anchoPanel, int alturaPanel) {
        int x = getValorPorcentaje(anchoPanel, 50);
        int y = getValorPorcentaje(alturaPanel, 15);
        
        int ancho = getValorPorcentaje(anchoPanel, 40);
        int alto = getValorPorcentaje(alturaPanel, 5);

        rbtnFontOriginal.setBounds(x, y, ancho, alto);
        
        y = getValorPorcentaje(alturaPanel, 20);

        rbtnFontAleatorio.setBounds(x, y, ancho, alto);
    }

    private int getValorPorcentaje(int valor, int porcentaje) {
        return valor * porcentaje / 100;
    }

    public JLabel getLblHint() {
        return lblHint;
    }

    public JTextField getInputHint() {
        return inputHint;
    }

    public JRadioButton getRbtnFontOriginal() {
        return rbtnFontOriginal;
    }

    public JRadioButton getRbtnFontAleatorio() {
        return rbtnFontAleatorio;
    }

    public String getText() {
        return txtInputHint;
    }

    public void setText(String texto) {
        txtInputHint = texto;
        inputHint.setText(texto);
    }

}
