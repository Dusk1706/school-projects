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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Controlador implements ActionListener {
    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        crearInterfaz();
        crearEscuchadores();
    }

    private void crearInterfaz() {
        vista.crearInterfaz(modelo.getNUM_PUERTOS());
        vista.crearPuertos(getNombrePuertos());
        vista.crearBarcos(modelo.getBarcos(), modelo.getNUM_PUERTOS());
        vista.revalidate();
        vista.crearImagenesBarcos();
    }

    private String[] getNombrePuertos() {
        Puerto[] puertos = modelo.getPuertos();
        int cantidadPuertos = puertos.length;
        String[] nombrePuertos = new String[cantidadPuertos];
        for(int i = 0; i < cantidadPuertos; i++) {
            nombrePuertos[i] = puertos[i].getNombre();
        }
        return nombrePuertos;
    }

    private void crearEscuchadores() {
        JButton[] btnBarcos = vista.getBtnBarcos();
        for (int i = 0; i < btnBarcos.length; i++) {
            btnBarcos[i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JButton btn = (JButton) evt.getSource();
        btn.setEnabled(false);
        
        Barco barco = modelo.getBarco(obtenerIndiceBtnBarco(btn));
        int puertoDestino = modelo.generarIndiceDestinoBarco();

        new Thread(() -> {
            irAPuertoDestino(barco, puertoDestino);
            regresarPuertoInicial(barco, puertoDestino);
            btn.setEnabled(true);
        }).start();
    }

    private void irAPuertoDestino(Barco barco, int puertoDestino) {
        int indiceBarco = obtenerIndiceBarco(barco);
        Icon imagen = vista.getBtnBarco(indiceBarco).getIcon();
        JLabel lblPuerto = vista.getLabelPuerto(indiceBarco, 0);
        lblPuerto.setIcon(imagen);

        barco.llenarTanque();
        lblPuerto.setIcon(null);

        for (int puerto = 1; puerto <= puertoDestino; puerto++) {
            lblPuerto = vista.getLabelPuerto(indiceBarco, puerto);
            lblPuerto.setIcon(imagen);
            procesoBarco(barco, puerto);
            lblPuerto.setIcon(null);
        }
    }

    private void regresarPuertoInicial(Barco barco, int puertoDestino) {
        int indiceBarco = obtenerIndiceBarco(barco);
        Icon imagen = vista.getBtnBarco(indiceBarco).getIcon();
        JLabel lblPuerto = vista.getLabelPuerto(indiceBarco, 0);

        barco.llenarTanque();
        for (int puerto = puertoDestino - 1; puerto >= 0; puerto--) {
            lblPuerto.setIcon(null);
            lblPuerto = vista.getLabelPuerto(indiceBarco, puerto);
            lblPuerto.setIcon(imagen);
            procesoBarco(barco, puerto);
        }
        lblPuerto.setIcon(null);
    }

    private void procesoBarco(Barco barco, int puerto) {
        barco.gastarCombustible();
        pescarProductos(barco);
        pescarProductos(barco);
        entregarToneladasPuerto(barco, puerto);
        pausarVista();
    }

    private void pausarVista() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pescarProductos(Barco barco) {
        if (barco.isBodegaLlena70Porciento()) {
            return;
        }
        int pesoProductos = barco.pescarProductosKg();
        barco.guardarProductosBodega(pesoProductos);
    }

    private void entregarToneladasPuerto(Barco barco, int indicePuerto) {
        int indiceBarco = obtenerIndiceBarco(barco);
        
        Puerto puerto = modelo.getPuerto(indicePuerto);
        
        int pesoActualBodegaBarco = barco.getBodega().getPesoActualKg();
        puerto.guardarProductosPuerto(pesoActualBodegaBarco);
        barco.vaciarBodega();        

        JLabel lblPuerto = vista.getLabelPuerto(indiceBarco, indicePuerto);

        int toneladasPuerto = puerto.getBodega().getPesoActualToneladas();
        lblPuerto.setText(String.valueOf(toneladasPuerto));

    }

    private int obtenerIndiceBtnBarco(JButton btn) {
        JButton[] btnBarcos = vista.getBtnBarcos();
        for (int i = 0; i < btnBarcos.length; i++) {
            if (btnBarcos[i] == btn) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerIndiceBarco(Barco barco) {
        Barco[] barcos = modelo.getBarcos();
        for (int i = 0; i < barcos.length; i++) {
            if (barcos[i] == barco) {
                return i;
            }
        }
        return -1;
    }

}
