package unidad1.programs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class MyLienzo extends Canvas {
	private String myImagen;
	public MyLienzo(String myImagen) {
		this.myImagen=myImagen;
	}
	public void paint( Graphics g) 	{
		super.paint(g);
		g.drawImage(Rutinas.AjustarImagen(myImagen,getWidth(),getHeight()).getImage(), 0, 0, getWidth(), getHeight(), this);
		 
	}
}
public class LienzoCanvas extends JFrame {

	MyLienzo lienzo1,lienzo2,lienzo3,lienzo4;
	public LienzoCanvas() {
		super("Manipoulando varios lienzos gr�ficos");
		HazInterfaz();
		HazEscuchas();
	}
	private void HazInterfaz() {
		setSize(500,500);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(0,2));
		lienzo1=new MyLienzo("Caballo.jpg");
		lienzo2=new MyLienzo("ojo.jpg");
		lienzo3=new MyLienzo("Caballo.jpg");
		lienzo4=new MyLienzo("avionregreso.jpg");
		add(lienzo1);
		add(lienzo2);
		add(lienzo3);
		add(lienzo4);
		setVisible(true);
		
	}
	private void HazEscuchas() {
		
	}
	public static void main(String[] args) {
		new LienzoCanvas();

	}

}
