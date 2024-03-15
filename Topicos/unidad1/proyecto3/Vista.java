/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 1
 * Proyecto: Barcos
 * Horario: 9:00 a 10:00
 * Fecha: 8/03/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad1.proyecto3;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import unidad1.examen.Rutinas;

public class Vista extends JFrame {
    private JLabel[] lblPuertos;
    private JButton[] btnBarcos;
    private JLabel[][] lblToneladasEntregadas;

    public Vista() {
        super("Barcos");
    }

    public void crearInterfaz(int numPuertos) {
        setSize(900, 700);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, numPuertos + 1));
        setVisible(true);
    }

    public void crearPuertos(String[] puertos) {
        lblPuertos = new JLabel[puertos.length];
        add(new JLabel());
        for (int i = 0; i < puertos.length; i++) {
            lblPuertos[i] = new JLabel(puertos[i], JLabel.CENTER);
            add(lblPuertos[i]);
        }
    }

    public void crearBarcos(Barco[] barcos, int numPuertos) {
        int numBarcos = barcos.length;
        btnBarcos = new JButton[numBarcos];
        lblToneladasEntregadas = new JLabel[numBarcos][numPuertos];

        for (int barco = 0; barco < barcos.length; barco++) {
            crearBotonBarco(barco);
            crearToneladasEntregadas(barco, numPuertos);
        }
    }

    private void crearBotonBarco(int barco) {
        btnBarcos[barco] = new JButton();
        add(btnBarcos[barco]);
    }

    private void crearToneladasEntregadas(int barco, int numPuertos) {
        for (int puerto = 0; puerto < numPuertos; puerto++) {
            lblToneladasEntregadas[barco][puerto] = new JLabel("0", JLabel.CENTER);
            add(lblToneladasEntregadas[barco][puerto]);
        }
    }

    public void crearImagenesBarcos() {
        for (JButton barco : btnBarcos) {
            ImageIcon imagen = Rutinas.AjustarImagen(
                crearRutaImagen(), barco.getWidth(), barco.getHeight()
            );
            barco.setIcon(imagen);
        }
    }

    private String crearRutaImagen() {
        int imagenAleatoria = Rutinas.nextInt(1, 5);
        return "imagenes/barco" + imagenAleatoria + ".png";
    }

    public JButton getBtnBarco(int indice) {
        return btnBarcos[indice];
    }

    public JButton[] getBtnBarcos() {
        return btnBarcos;
    }

    public JLabel getLabelPuerto(int barco, int puerto) {
        return lblToneladasEntregadas[barco][puerto];
    }

}
