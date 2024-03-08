package unidad1.programs;
import javax.swing.*;
public class HolaMundo1 extends JFrame{
	JButton btn;
	public HolaMundo1() {
		super("Pantalla con herencia");
		HazInterfaz();
		HazEscuchas();
	}
	private void HazInterfaz() {
		setSize(600,400);
		this.setLocationRelativeTo(null);
		btn=new JButton("Grabar");
		add(btn);

		setVisible(true);
		remove(btn);
		System.out.println("termini el constructor");
		setIconImage (new ImageIcon("caballo.jpg").getImage());

	}
	private void HazEscuchas() {
		
	}
	public static void main(String[] a) {
		new HolaMundo1();
		
		//System.out.println("se acabo el main");

	}

}
