/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 1
 * Proyecto: Examen
 * Horario: 9:00 a 10:00
 * Fecha: 8/03/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad1.examen;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Vista extends JFrame {

    private JPanel panelIds, panelBotones, panelGerentes;
    private ButtonGroup grupoIds, grupoGerentes;
    private JRadioButton [] rbtnIds;
    private JButton btnImagen, btnEntrar, btnSalir;
    private JRadioButton [] rbtnGerentes;

    public Vista() {
        super("Titulo");
    }

    public void crearInterfaz(int cantidadPersonas, String[] gerentes) {
        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3));

        crearPanelIds(cantidadPersonas);
        crearPanelBotones();
        crearPanelGerentes(gerentes);

        setVisible(true);
    }

    private void crearPanelIds(int cantidadPersonas) {
        panelIds = new JPanel();
        panelIds.setLayout(new GridLayout(0, 1));

        grupoIds = new ButtonGroup();
        
        rbtnIds = new JRadioButton[cantidadPersonas];

        for (int i = 0; i < cantidadPersonas; i++) {
            rbtnIds[i] = new JRadioButton(String.valueOf(i + 1));
            grupoIds.add(rbtnIds[i]);
            panelIds.add(rbtnIds[i]);
        }


        add(panelIds);
    }

    public JRadioButton[] getRbtnsIds() {
        return rbtnIds;
    }

    private void crearPanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(0, 1));

        btnImagen = new JButton();
        btnImagen.setEnabled(false);
        btnEntrar = new JButton("Entrar");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnImagen);
        panelBotones.add(btnEntrar);
        panelBotones.add(btnSalir);

        add(panelBotones);
    }

    public void asignarImagen() {
        ImageIcon icono = Rutinas.AjustarImagen("imagenes/torniquete.png", btnImagen.getWidth(), btnImagen.getHeight());
        btnImagen.setIcon(icono);
    }

    public void crearPanelGerentes(String [] gerentes) {
        panelGerentes = new JPanel();
        panelGerentes.setLayout(new GridLayout(0, 1));

        grupoGerentes = new ButtonGroup();

        int cantidadGerentes = gerentes.length;
        rbtnGerentes = new JRadioButton[cantidadGerentes];

        for (int i = 0; i < cantidadGerentes; i++) {
            rbtnGerentes[i] = new JRadioButton(gerentes[i]);
            grupoGerentes.add(rbtnGerentes[i]);
            panelGerentes.add(rbtnGerentes[i]);
        }

        add(panelGerentes);
    }

    public void limpiarSeleccion() {
        grupoIds.clearSelection();
        grupoGerentes.clearSelection();
    }

    public void mostrarMensaje(String mensaje, int tipo) {
        Rutinas.mensaje(mensaje, tipo);
    }

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JRadioButton getRbtnEmpleadoSeleccionado() {
        for (JRadioButton rbtn : rbtnIds) {
            if (rbtn.isSelected()) {
                return rbtn;
            }
        }
        return null;
    }

    public JRadioButton getRbtnGerenteSeleccionado() {
        for (JRadioButton rbtn : rbtnGerentes) {
            if (rbtn.isSelected()) {
                return rbtn;
            }
        }
        return null;
    }

}
