package unidad1.programs;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TamanoBotonV2 extends JFrame implements ActionListener{
	 private JButton boton, boton1;
	 private boolean band;
	 public TamanoBotonV2() 	 {
		 super("Manejo de botones");
		 HazInterfaz();
		 HazEscuchas();
		 band=true;
	   
	 }
	 public void HazInterfaz() {
		 add(new JLabel("Esta boton aumenta 50 pixeles por dimensi�n"));
		    setLayout(new FlowLayout());
		    boton = new JButton("Boton");
		    boton1= new JButton("Boton1");
	        //boton.setSize(150,100);
		    boton. setPreferredSize(new Dimension(150,100));
		    
		   // this.setResizable(false);
		    add(boton);
		    add(boton1);
		    setSize(350, 500);
		    this.setLocationRelativeTo(null);
		    setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		    setVisible(true);
	 }
	 public void HazEscuchas() {
	      boton.addActionListener(this);
	      boton1.addActionListener(this); 
	 }
	 public static void main(String[] args)
	 {
	     new TamanoBotonV2();
	 }
	@Override
	public void actionPerformed(ActionEvent evt) {
/*		
		if( evt.getSource() == boton ) {
			boton.setSize(boton.getWidth() + 50, boton.getHeight() + 50);
			return;
		}
		if ( evt.getSource()==boton1) {
			boton1.setSize(boton1.getWidth() + 50, boton1.getHeight() + 50);
	    	 return; 
		}
*/
		JButton aux = (JButton) evt.getSource();
		aux.setSize(aux.getWidth() + 50, aux.getHeight() + 50);
		if( evt.getSource()==boton) {
			if( band ) {
				aux.setBackground(Color.cyan);
				band=!band;
			} else {
				aux.setBackground(Color.red);
				band=!band;
			}
			return;
		}
		if ( evt.getSource()==boton1) {
			JDialog pantalla2=new JDialog();
			pantalla2.setTitle("Captura INE");
			
			pantalla2.setSize(500,500);
			pantalla2.setLocationRelativeTo(null);
			pantalla2.setModal(true);
			pantalla2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			pantalla2.setVisible(true);
		}
	}
}
