package unidad1.programs;

import javax.swing.*;
import java.awt.*;
public class HolaMundo {

	public static void main(String [] a) {
		
		JFrame pantalla = new JFrame("ITC");
		
		
		
		JButton boton = new JButton("GRABAR");
		JButton botonN = new JButton("NORTE");
		JButton botonS = new JButton("SUR");
		JButton botonE = new JButton("ESTE");
		JButton botonO = new JButton("OESTE");
		pantalla.setSize(400, 300);
		//pantalla.setLocation(600, 100);
		pantalla.setLocationRelativeTo(null);
boton.setBounds(100, 100, 100, 100);		
		pantalla.add(boton);
		pantalla.add(botonN,BorderLayout.NORTH);
		pantalla.add(botonS,BorderLayout.SOUTH);
		pantalla.add(botonE,BorderLayout.EAST);
		pantalla.add(botonO,BorderLayout.WEST);
		pantalla.setVisible(true);
		
		
	}
}
