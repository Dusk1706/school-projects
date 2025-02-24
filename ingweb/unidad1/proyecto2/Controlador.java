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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Controlador implements ActionListener {
    
    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        vista.crearEscuchadores(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == vista.getBtnLimpiar()) {
            vista.limpiar();
            return;
        }

        int numero = obtenerNumero();
        if (numero < 0) {
            vista.mostrarMensaje("Favor de ingresar un numero valido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int resultado = -1;
        if (evt.getSource() == vista.getBtnFactorial()) {
            resultado = calcularFactorial(numero);
        } else if (evt.getSource() == vista.getBtnFibonacci()) {
            resultado = calcularFibonacci(numero);
        } else if (evt.getSource() == vista.getBtnAckerman()) {
            resultado = calcularAckerman(numero);
        }

        modelo.getRegistro().leerObjetos();

        vista.setResultadoValor(resultado);
    }

    private int calcularFactorial(int numero) {
        Operacion operacion = modelo.getRegistro().buscarOperacion("Factorial", numero);
        if (operacion == null) {
            try {
                operacion = new Operacion(
                    "Factorial", numero, modelo.factorial(numero)
                );
                
                modelo.getRegistro().registrarObjeto(operacion);
            } catch (StackOverflowError e) {
                vista.mostrarMensaje("Error el numero es muy grande", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
        }
        
        return operacion.getResultado();
    }

    private int calcularFibonacci(int numero) {
        Operacion operacion = modelo.getRegistro().buscarOperacion("Fibonacci", numero);
        if (operacion == null) {
            try {
                operacion = new Operacion(
                    "Fibonacci", numero, modelo.fibonacci(numero)
                );
                modelo.getRegistro().registrarObjeto(operacion);
            } catch (StackOverflowError e) {
                vista.mostrarMensaje("Error el numero es muy grande", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
        }
        
        return operacion.getResultado();
    }

    private int calcularAckerman(int numero) {
        Operacion operacion = modelo.getRegistro().buscarOperacion("Ackerman", numero);
        if (operacion == null) {
            try {
                operacion = new Operacion(
                    "Ackerman", numero, modelo.ackerman(numero, numero)
                );
                modelo.getRegistro().registrarObjeto(operacion);
            } catch (StackOverflowError e) {
                vista.mostrarMensaje("Error el numero es muy grande", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
        }
        
        return operacion.getResultado();
    }

    private int obtenerNumero() {
        try {
            return Integer.parseInt(vista.getTfNumero().getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
