package unidad1.programs;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class TamanioBoton extends JFrame {
	 private JButton boton, boton1;
	 public TamanioBoton() 	 {
		 super("Manejo de botones");
		 HazInterfaz();
		 HazEscuchas();
	   
	 }
	 public void HazInterfaz() {
		 add(new JLabel("Esta boton aumenta 50 pixeles por dimensi�n"));
		    setLayout(new FlowLayout());
		    boton = new JButton("Boton");
		    boton1= new JButton("Boton1");
	        //boton.setSize(150,100);
		    boton. setPreferredSize(new Dimension(150,100));
		    
		    this.setResizable(false);
		    add(boton);
		    add(boton1);
		    setSize(350, 500);
		    this.setLocationRelativeTo(null);
		    setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		    setVisible(true);
	 }
	 public void HazEscuchas() {
	      boton.addActionListener(new ActionListener()
	      {   public void actionPerformed(ActionEvent evt)
	          { 
	    	    boton.setBackground(Color.BLUE);
	    	  	boton.setSize(boton.getWidth() + 50, boton.getHeight() + 50);
	    	  	//repaint();
	          }
	       });

	      boton1.addActionListener(new ActionListener()
	      {   public void actionPerformed(ActionEvent evt)
	          { 
	    	    boton1.setBackground(Color.BLUE);
	    	  	boton1.setSize(boton1.getWidth() + 50, boton1.getHeight() + 50);
	    	  	//repaint();
	          }
	       });	 
	 }
	 public static void main(String[] args)
	 {
	     new TamanioBoton();
	 }
}
