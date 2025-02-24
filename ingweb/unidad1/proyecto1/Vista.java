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

public class Vista extends JFrame {
    private JPanel panelMenu;
    private JButton btnRegistrar, btnBuscar;

    private JDialogRegistro dialogoRegistro;
    private JDialogBuscar dialogoBuscar;

    public Vista() {
        super("Gestión de Carros");
        crearInterfaz();
        crearDialogos();
    }

    private void crearInterfaz() {
        setSize(600, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelMenu = new JPanel(new GridLayout(3, 1, 0, 15));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 100, 50, 100));

        JLabel lblTitulo = new JLabel("Seleccione una opcion");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 35));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        btnRegistrar = new JButton("Registrar Carro");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 25));

        btnBuscar = new JButton("Buscar Carro");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 25));

        panelMenu.add(lblTitulo);
        panelMenu.add(btnRegistrar);
        panelMenu.add(btnBuscar);

        add(panelMenu, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void mostrarMensaje(String texto, int tipo) {
		JOptionPane.showMessageDialog(null, texto, "", tipo);
	}

    private void crearDialogos() {
        dialogoRegistro = new JDialogRegistro(this);
        dialogoBuscar = new JDialogBuscar(this);
    }

    public void mostrarDialogoRegistro() {
        dialogoRegistro.setVisible(true);
    }

    public void mostrarDialogoBuscar() {
        dialogoBuscar.setVisible(true);
    }

    public JDialogRegistro getDialogoRegistro() {
        return dialogoRegistro;
    }

    public JDialogBuscar getDialogoBuscar() {
        return dialogoBuscar;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

}
