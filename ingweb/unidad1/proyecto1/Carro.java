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

import java.io.Serializable;

public class Carro implements Serializable {
    private String marca;
    private String modelo;
    private String color;
    private String placa;
    private int anio;

    private Motor motor;

    public Carro(String marca, String modelo, String color, int anio, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.placa = placa;
    }

    public Carro(String marca, String modelo, String color, int anio, String placa, String tipo, String cilindrada, String potencia, String combustible, String serial) {
        this(marca, modelo, color, anio, placa);
        this.motor = new Motor(tipo, cilindrada, potencia, combustible, serial);
    }

    public Carro(String marca, String modelo, String color, int anio, String placa, Motor motor) {
        this(marca, modelo, color, anio, placa);
        this.motor = motor;
    }

    public Motor getMotor() {
        return motor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public int getAnio() {
        return anio;
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", anio=" + anio +
                ", placa=" + placa +
                '}';
    }
    
}
