package unidad1.programs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Graphics2 extends JFrame implements MouseListener{
	private JButton btnCirculo,btnTriangulo, btnCuadrado;
	private boolean banCirculo, banTriangulo, banCuadrado;
	public Graphics2(){
		super("Manejo del mouse en graphics");
		banCirculo=banTriangulo=banCuadrado=false;
		HazInterfaz();
		HazEscuchas();
	}
	private void HazInterfaz() {
		setSize(400,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		btnCirculo=new JButton("Circulo");
		btnTriangulo=new JButton("Triangulo");
		btnCuadrado=new JButton("Cuadrado");
		JPanel panelV=new JPanel();
		panelV.setLayout(new GridLayout(0,1));
		panelV.add(btnCirculo);
		panelV.add(btnTriangulo);
		panelV.add(btnCuadrado);
		add(panelV,BorderLayout.EAST);
		setVisible(true);
	}
	private void HazEscuchas() {
		btnCirculo.addMouseListener(this);
		btnTriangulo.addMouseListener(this);
		btnCuadrado.addMouseListener(this);
	}
	public static void main(String[] args) {
		new Graphics2();
	}
	public void paint( Graphics g) 	{
		super.paint(g);
		if( banCirculo) {
			g.setColor(Color.RED);
			g.fillOval(50,100,(int)(this.getWidth()*0.60),(int)(this.getHeight()*0.60));
			g.setColor(Color.LIGHT_GRAY);
			return;
		}
		if( banCuadrado) {
			g.setColor(Color.RED);
			g.fillRect(50,100,(int)(this.getWidth()*0.60),(int)(this.getHeight()*0.60));
			g.setColor(Color.LIGHT_GRAY);
			return;
		}
		if( banTriangulo) {
			int [] x= {100,10,190};
			int [] y= {100,150,150};
			g.fillPolygon(x, y, x.length);
			
			
			
			
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
		if(evt.getSource()==btnCuadrado) {
			banCuadrado=true;
			repaint();
			return;
		}
		if(evt.getSource()==btnCirculo) {
			banCirculo=true;
			repaint();
			return;
		}
		if( evt.getSource()==btnTriangulo) {
			banTriangulo=true;
			repaint();
			return;
		}
	}

	@Override
	public void mouseExited(MouseEvent evt) {
		if( evt.getSource()== btnCuadrado) {
			banCuadrado=false;
			repaint();
		}
		if(evt.getSource()==btnCirculo) {
			banCirculo=false;
			repaint();
			return;
		}
		if( evt.getSource()==btnTriangulo) {
			banTriangulo=!banTriangulo;
			repaint();
			return;
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
