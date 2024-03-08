package unidad1.programs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GraphicsScreenOff extends JFrame implements ActionListener{
	JButton btnAvanzar;
	int inc, valor=0;
	Timer temporizador ;
	Graphics g;
	Image myImagen = null;	
	public GraphicsScreenOff() {
		super("Manejo de gr�ficos");
		inc=0;
		
		HazInterfaz();
		HazEscuchas();
		myImagen = createImage(getWidth(), getHeight());
		g =myImagen.getGraphics();
		
		temporizador=new Timer(100,this);
		temporizador.start();
	}
	private void HazInterfaz() {
		setSize(600,500);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		btnAvanzar = new JButton("Avanzar");
		add(btnAvanzar,BorderLayout.SOUTH);
		setVisible(true);
	}
	private void HazEscuchas() {
		btnAvanzar.addActionListener(this);
	}
	public static void main(String [] a) {
		new GraphicsScreenOff();
	}
	private void Dibujar() {
		while( g==null);
//		if( g==null)
//			return;
		super.paint(g);
		g.drawImage( Rutinas.AjustarImagen(  "CABALLO.JPG", this.getWidth(), this.getHeight()).getImage() ,0 ,0,null);  
	       g.drawString("INSTITUTO TECNOL�GICO DE CULIAC�N",50,40);
	        g.drawLine(20,45,300,45);
	        g.drawString("INGENIER�A EN SISTEMAS COMPUTACIONALES",50,60);
	        g.setColor(Color.BLUE);
	        g.fillRect(50+inc,70,100,50);
	        g.fillRoundRect(250, 70,100, 50, 20, 20);
	        Color CAFE=new Color(192,128,64);
	        g.setColor(CAFE);
	        g.fillRect(50+inc,150,200,100);
	        g.setColor(Color.RED);
	        g.fillOval(60+inc,220,60,60);
	        g.fillOval(190+inc,220,50,60);
	        g.setColor(Color.WHITE);
	        g.fillRect(200+inc,160,50,30);
	        btnAvanzar.grabFocus();
	}
	public void paint( Graphics g) 	{
		Dibujar();
		g.drawImage(myImagen, 0, 0, getWidth(), getHeight(), this);
		 btnAvanzar.grabFocus();
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if( evt.getSource()==btnAvanzar || evt.getSource()==temporizador) {
			inc+=valor;
			repaint();
			//repaint(50,150,this.getWidth(),150);
			
			if( inc == 340)
				valor=-10;
			if ( inc <5)
				valor=10;
			
		}
		
	}
}


