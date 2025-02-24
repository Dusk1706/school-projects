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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Controlador implements ActionListener {
    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        crearEscuchadores();
    }

    private void crearEscuchadores() {
        vista.getBtnRegistrar().addActionListener(this);
        vista.getBtnBuscar().addActionListener(this);

        vista.getDialogoBuscar().getBtnBuscar().addActionListener(this);

        vista.getDialogoRegistro().getBtnGuardarComposicion().addActionListener(this);
        vista.getDialogoRegistro().getBtnGuardarAgregacion().addActionListener(this);
        vista.getDialogoRegistro().getBtnAgregarMemoria().addActionListener(this);

        vista.getDialogoRegistro().getRbMotorExistente().addActionListener(this);
        vista.getDialogoRegistro().getRbNuevoMotor().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegistrar()) {
            vista.mostrarDialogoRegistro();
            return;
        }

        if (e.getSource() == vista.getBtnBuscar()) {
            vista.mostrarDialogoBuscar();
            return;
        }

        if (e.getSource() == vista.getDialogoRegistro().getBtnGuardarComposicion()) {
            registrarCarroComposicion();
            return;
        }

        if (e.getSource() == vista.getDialogoRegistro().getBtnGuardarAgregacion()) {
            registrarCarroAgregacion();
            return;
        }

        if (e.getSource() == vista.getDialogoRegistro().getBtnAgregarMemoria()) {
            vista.getDialogoRegistro().agregarMotorAMemoria();
            return;
        }

        if (e.getSource() == vista.getDialogoBuscar().getBtnBuscar()) {
            buscarCarro();
            return;
        }

        if (e.getSource() == vista.getDialogoRegistro().getRbMotorExistente()) {
            vista.getDialogoRegistro().getBtnGuardarComposicion().setEnabled(false);
            return;
        }

        if (e.getSource() == vista.getDialogoRegistro().getRbNuevoMotor()) {
            vista.getDialogoRegistro().getBtnGuardarComposicion().setEnabled(true);
            return;
        }
    }

    private void registrarCarroComposicion() {
        if (vista.getDialogoRegistro().hayCamposVacios()) {
            Vista.mostrarMensaje("Todos los campos son requeridos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Carro carro = new Carro(
            vista.getDialogoRegistro().getMarca(),
            vista.getDialogoRegistro().getModelo(),
            vista.getDialogoRegistro().getColor(),
            obtenerAnio(),
            vista.getDialogoRegistro().getPlaca(),
            vista.getDialogoRegistro().getTipoMotor(),
            vista.getDialogoRegistro().getCilindrada(),
            vista.getDialogoRegistro().getPotencia(),
            vista.getDialogoRegistro().getCombustible(),
            vista.getDialogoRegistro().getSerial()
        );

        registrarCarro(carro);
    }

    private void registrarCarroAgregacion() {
        if (!validarCamposCarro())
            return;

        String marca = vista.getDialogoRegistro().getMarca();
        String modelo = vista.getDialogoRegistro().getModelo();
        String color = vista.getDialogoRegistro().getColor();
        int anio = obtenerAnio();
        if (anio == -1)
            return;

        String placa = vista.getDialogoRegistro().getPlaca();
        Motor motor = obtenerMotor();
        if (motor == null)
            return;

        Carro carro = new Carro(marca, modelo, color, anio, placa, motor);
        registrarCarro(carro);
    }

    private boolean validarCamposCarro() {
        if (vista.getDialogoRegistro().hayCamposVaciosCarro()) {
            Vista.mostrarMensaje("Todos los campos de carro son requeridos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private int obtenerAnio() {
        try {
            return Integer.parseInt(vista.getDialogoRegistro().getAnio());
        } catch (NumberFormatException e) {
            Vista.mostrarMensaje("El año debe ser un número valido", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private Motor obtenerMotor() {
        if (vista.getDialogoRegistro().esOpcionMotorExistente()) {
            return obtenerMotorExistente();
        } else {
            return crearNuevoMotor();
        }
    }

    private Motor obtenerMotorExistente() {
        if (!vista.getDialogoRegistro().hayMotoresEnMemoria()) {
            Vista.mostrarMensaje("No hay motores en memoria", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return vista.getDialogoRegistro().getMotorSeleccionado();
    }

    private Motor crearNuevoMotor() {
        if (vista.getDialogoRegistro().hayCamposVaciosMotor()) {
            Vista.mostrarMensaje("Todos los campos del motor son requeridos", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Motor nuevoMotor = new Motor(
                vista.getDialogoRegistro().getTipoMotor(),
                vista.getDialogoRegistro().getCilindrada(),
                vista.getDialogoRegistro().getPotencia(),
                vista.getDialogoRegistro().getCombustible(),
                vista.getDialogoRegistro().getSerial());

        vista.getDialogoRegistro().agregarMotorAMemoria();
        return nuevoMotor;
    }

    private void registrarCarro(Carro carro) {
        if (modelo.registrarCarro(carro)) {
            Vista.mostrarMensaje("Carro registrado exitosamente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Vista.mostrarMensaje("Error al registrar carro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarCarro() {
        vista.getDialogoBuscar().getTaResultados().setText("");

        String placa = vista.getDialogoBuscar().getPlaca();
        Carro[] carros = modelo.buscarCarros(placa);

        if (carros == null) {
            Vista.mostrarMensaje("Error al buscar carro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (carros.length == 0) {
            Vista.mostrarMensaje("No se encontraron carros con la placa " + placa, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Carro carro : carros) {
            sb.append(carro).append("\n").append(carro.getMotor()).append("\n\n");
        }

        vista.getDialogoBuscar().getTaResultados().setText(sb.toString());
    }

}
