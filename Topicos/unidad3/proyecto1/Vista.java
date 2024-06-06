/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 3
 * Proyecto: Planta Ensambladora
 * Horario: 9:00 a 10:00
 * Fecha: 17/05/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad3.proyecto1;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import unidad3.proyecto1.auxiliar.Rutinas;

public class Vista extends JFrame {
    private static JLabel lineas[][];
    private int numeroLineas;

    public Vista(int numeroLineas){
        super("Planta Ensambladora");
        this.numeroLineas = numeroLineas;
        crearInterfaz();
    }

    public static void actualizarInterfaz(int linea, int posicion, int autoActual, String urlImgProceso ,String urlImgRobot) {
        JLabel lblProceso = lineas[linea][posicion];
        ImageIcon imgProceso = Rutinas.AjustarImagen(
            urlImgProceso, lblProceso.getWidth(), lblProceso.getHeight() / 2
        );
        lblProceso.setForeground(Color.RED);
        lblProceso.setHorizontalTextPosition(JLabel.CENTER);
        lblProceso.setVerticalTextPosition(JLabel.BOTTOM);
        lblProceso.setIcon(imgProceso);
        lblProceso.setText("Auto #" + autoActual);
            
        JLabel lblRobot = lineas[linea][posicion + 1];
        ImageIcon imgRobot = Rutinas.AjustarImagen(
            urlImgRobot, lblRobot.getWidth(), lblRobot.getHeight()
        );
        lblRobot.setIcon(imgRobot);
    }

    public static void quitarInterfaz(int linea, int posicion) {
        JLabel lblProceso = lineas[linea][posicion];
        lblProceso.setIcon(null);
        lblProceso.setText("");
        
        JLabel lblRobot = lineas[linea][posicion + 1];
        lblRobot.setIcon(null);
        lblRobot.setText("");

    }

    private void crearInterfaz() {
        setSize(900, 700);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,15));
        
        crearTituloEstaciones();
        crearLineasProduccion();

        setVisible(true);
    }

    private void crearTituloEstaciones() {
        add(new JLabel());
        for (int i = 1; i <= 6; i++) {
            if(i == 2) {
                add(new JLabel());
                add(new JLabel("Estacion "+ i));
                add(new JLabel());
            }else {
                add(new JLabel("Estacion "+ i));
            }
            add(new JLabel());
        }
    }

    private void crearLineasProduccion() {
        lineas = new JLabel[numeroLineas][15];
        for (int i = 0; i < numeroLineas; i++) {
            agregarLabelLinea(i, 0, "Linea #"+ (i + 1));
            for (int j = 0; j <= 6; j++) {
                int pos = j * 2;
                agregarLabelLinea(i, pos + 1, "");
                agregarLabelLinea(i, pos + 2, "");
            }
        }
    }

    private void agregarLabelLinea(int linea, int pos, String texto) {
        lineas[linea][pos] = new JLabel(texto);
        add(lineas[linea][pos]);
    }

}
