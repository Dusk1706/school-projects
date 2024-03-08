package unidad1.programs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MENU extends JFrame implements ActionListener{
	JMenuBar barra;
	JMenu colores, generos, pastel;
	JMenuItem rojo, azul, verde, pastel1,pastel2,pastel3;
	JMenuItem masculino, femenino;
	JButton btnCristal;
	JPanel glassPane;
	public MENU() {
		super("Man� de opciones");
		HazInterfaz();
		HazEscuchas();
	}
	public void HazInterfaz() {
		setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		UIManager.put("Label.font", new Font("tahoma", Font.BOLD, 20));
		
		btnCristal=new JButton("Cristal");
		barra=new JMenuBar();
		setJMenuBar(barra);
		barra.setBackground(Color.MAGENTA);
		colores=new JMenu("COLORES");
		generos=new JMenu("GENEROS");
		
		pastel=new JMenu("Patel");
		barra.add(colores);
		barra.add(generos);
		barra.add(btnCristal);
		
		
		verde=new JMenuItem("Verde");
		azul=new JMenuItem("Azul");
		rojo=new JMenuItem("Rojo");
		
		colores.add(rojo);
		colores.add(azul);
		colores.add(verde);
		colores.add(pastel);
		
		pastel.add(pastel1=new JMenuItem("Rosa"));
		pastel.add(pastel2=new JMenuItem("Amarillo"));
		pastel.add(pastel3=new JMenuItem("Naranja"));
		
		
		masculino=new JMenuItem("Masc�lino");
		femenino=new JMenuItem("Fememino");
		
		generos.add(masculino);
		generos.add(femenino);
		
		
		setVisible(true);
	}
	public void HazEscuchas() {
		rojo.addActionListener(this);
		azul.addActionListener(this);
		verde.addActionListener(this);
		btnCristal.addActionListener(this);
	}
	public static void main(String[] args) {
		new MENU();

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if( evt.getSource()==btnCristal) {
			glassPane =(JPanel) this.getGlassPane();
			glassPane.setOpaque(false);
			
			glassPane.add(new JLabel("_______________________________"));
			glassPane.add(new JLabel("MENSAJE URGENTE PARA USTEDES"));
			glassPane.add(new JLabel("_______________________________"));
			
			//glassPane.add(btnDesactivalo);
			JButton btnDesactivalo=new JButton("Desactivalo");
		    btnDesactivalo.addActionListener(new ActionListener()
		      {   public void actionPerformed(ActionEvent evt)
		          {  
		    	  	glassPane.removeAll();
		    	  	glassPane.setVisible(false);
		          }
		       });

			
			glassPane.add(btnDesactivalo);
			glassPane.setVisible(true);
			return;
		}
		if( evt.getSource()==rojo) {
			
			this.getContentPane().setBackground(Color.red);
			return;
		}
		if( evt.getSource()==verde) {
			this.getContentPane().setBackground(Color.green);
			
			return;
		}
		if( evt.getSource()==azul) {
			this.getContentPane().setBackground(Color.blue);
			return;
		}

	}

	
}
