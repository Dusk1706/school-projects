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
import java.awt.*;

public class JDialogBuscar extends JDialog {
    private JTextField tfPlaca;
    private JButton btnBuscar;
    private JTextArea taResultados;

    public JDialogBuscar(JFrame parent) {
        super(parent, "Buscar Carro", true);
        setSize(700, 450);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(parent);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setFont(new Font("Arial", Font.PLAIN, 18));

        tfPlaca = new JTextField(15);
        tfPlaca.setFont(new Font("Arial", Font.PLAIN, 18));
        
        btnBuscar = new JButton("Buscar");
        
        panelBusqueda.add(lblPlaca);
        panelBusqueda.add(tfPlaca);
        panelBusqueda.add(btnBuscar);
        
        taResultados = new JTextArea(15, 50);
        taResultados.setFont(new Font("Arial", Font.PLAIN, 18));
        taResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taResultados);
        
        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        add(panelPrincipal);
    }

    public String getPlaca() {
        return tfPlaca.getText();
    }

    public JTextArea getTaResultados() {
        return taResultados;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }
    
}
