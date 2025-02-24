/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Ingenieria Web
 * Unidad: 1
 * Proyecto: MVC
 * Horario: 10:00 a 11:00
 * Fecha: 24/02/2025
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package proyecto2;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Vista extends JFrame {

    private JTextField tfNumero;
    private JLabel lblResultadoValor;
    private JButton btnFactorial, btnFibonacci, btnAckerman, btnLimpiar;

    public Vista() {
        super("Titulo");
        crearInterfaz();
    }

    private void crearInterfaz() {
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));

        JLabel lblNumero = new JLabel("Numero");
        lblNumero.setFont(new Font("Arial", Font.BOLD, 25));
        tfNumero = new JTextField();
        tfNumero.setFont(new Font("Arial", Font.PLAIN, 25));

        JLabel lblResultadoTexto = new JLabel("Resultado");
        lblResultadoTexto.setFont(new Font("Arial", Font.BOLD, 25));
        lblResultadoValor = new JLabel("");
        lblResultadoValor.setFont(new Font("Arial", Font.BOLD, 25));

        btnFactorial = crearBoton("Factorial");
        btnFibonacci = crearBoton("Fibonacci");
        btnAckerman = crearBoton("Ackerman");
        btnLimpiar = crearBoton("Limpiar");

        panel.add(lblNumero);
        panel.add(tfNumero);

        panel.add(lblResultadoTexto);
        panel.add(lblResultadoValor);

        panel.add(btnFactorial);
        panel.add(btnFibonacci);
        panel.add(btnAckerman);
        panel.add(btnLimpiar);

        add(panel);

        setVisible(true);
    }

    public void crearEscuchadores(Controlador controlador) {
        btnFactorial.addActionListener(controlador);
        btnFibonacci.addActionListener(controlador);
        btnAckerman.addActionListener(controlador);
        btnLimpiar.addActionListener(controlador);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(21, 95, 130));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 18));
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return boton;
    }

    public void limpiar() {
        tfNumero.setText("");
        lblResultadoValor.setText("");
    }

    public void setResultadoValor(int valor) {
        lblResultadoValor.setText(String.valueOf(valor));
    }

    public void mostrarMensaje(String texto, int tipo) {
        JOptionPane.showMessageDialog(null, texto, "", tipo);
    }

    public JTextField getTfNumero() {
        return tfNumero;
    }

    public JButton getBtnFactorial() {
        return btnFactorial;
    }

    public JButton getBtnFibonacci() {
        return btnFibonacci;
    }

    public JButton getBtnAckerman() {
        return btnAckerman;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

}
