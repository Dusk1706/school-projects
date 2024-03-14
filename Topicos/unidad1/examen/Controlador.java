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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Controlador implements ActionListener {
    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        vista.crearInterfaz(modelo.getNUM_EMPLEADOS(), modelo.getNombreGerentes());
        vista.asignarImagen();
        crearEscuchadores();
    }

    private void crearEscuchadores() {
        vista.getBtnEntrar().addActionListener(this);
        vista.getBtnSalir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       JButton aux = (JButton) e.getSource();
       if (aux == vista.getBtnEntrar()) {
            entrarTorniquete();
       }

       if (aux == vista.getBtnSalir()) {
            salirTorniquete();
       }
       vista.limpiarSeleccion();
    }

    private boolean errorSeleccion(JRadioButton idEmpleado, JRadioButton gerente) {
        if(idEmpleado != null && gerente != null) {
            vista.mostrarMensaje("No se puede ingresar", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        if (idEmpleado == null && gerente == null) {
            vista.mostrarMensaje("Selecciona una forma de entrar", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private void entrarTorniquete() {
        JRadioButton idEmpleado = vista.getRbtnEmpleadoSeleccionado();
        JRadioButton gerente = vista.getRbtnGerenteSeleccionado();
        
        if(errorSeleccion(idEmpleado, gerente)) {
            return;
        }
        int tipoError = JOptionPane.ERROR_MESSAGE, tipoOk = JOptionPane.INFORMATION_MESSAGE;

        if(idEmpleado != null) {
            int id = Integer.parseInt(idEmpleado.getText());
            if(modelo.pasarPorTorniquete(id)) {
                modelo.decrementarAcceso(id);
                vista.mostrarMensaje("Bienvenido", tipoOk);
            } else {
                vista.mostrarMensaje("Ya paso por el torniquete", tipoError);
            }
        }

        if(gerente != null) {
            String nombreGerente = gerente.getText();
            int idGerente = modelo.getIdGerente(nombreGerente);
            if(modelo.pasarPorTorniquete(idGerente)) {
                vista.mostrarMensaje("Bienvenido", tipoOk);
            } else {
                vista.mostrarMensaje("Ya paso por el torniquete", tipoError);
            }
        }


    }

    private void salirTorniquete() {
        JRadioButton empleado = vista.getRbtnEmpleadoSeleccionado();
        JRadioButton gerente = vista.getRbtnGerenteSeleccionado();
        
        if(errorSeleccion(empleado, gerente)) {
            return;
        }
        int tipoError = JOptionPane.ERROR_MESSAGE, tipoOk = JOptionPane.INFORMATION_MESSAGE;

        if(empleado != null) {
            int idEmpleado = Integer.parseInt(empleado.getText());
            if(modelo.salirPorTorniquete(idEmpleado)) {
                Rutinas.mensaje("Bienvenido", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Rutinas.mensaje("No ha pasado por el torniquete", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(gerente != null) {
            String nombreGerente = gerente.getText();
            int idGerente = modelo.getIdGerente(nombreGerente);
            if(modelo.salirPorTorniquete(idGerente)) {
                vista.mostrarMensaje("Hasta luego", tipoOk);
            } else {
                vista.mostrarMensaje("No ha pasado por el torniquete", tipoError);
            }
        }
    }
    
}
