package unidad2;

import java.awt.GridLayout;

import javax.swing.JFrame;

import unidad2.proyecto1.JHint;
import unidad2.proyecto2.JMultipleBox;


public class Vista extends JFrame {
    
    public Vista() {
        super("Titulo");
        crearInterfaz();
    }

    private void crearInterfaz() {
        setSize(1100, 500);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10));    
        // add(new JHint("Estacion"));
        add(new JMultipleBox());
        setVisible(true);

    }

    public static void main(String[] args) {
        new Vista();
    }
}
