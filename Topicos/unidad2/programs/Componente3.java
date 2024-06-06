package unidad2.programs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Componente3 extends JPanel implements ComponentListener,ActionListener{
	private JTextField texto;
	private JButton rbOcultar, rbMostrar;
	private JPanel panel;
	String auxiliar;
	public Componente3() {
		HazInterfaz();
		HazEscuchas();
	}
	private void HazInterfaz() {
		setLayout(null);
		texto=new JTextField();
		rbOcultar=new JButton("Ocultar");
		rbMostrar=new JButton("Mostrar");
		
		rbOcultar.setFont(new Font("Tahoma",Font.BOLD,8));
		rbMostrar.setFont(new Font("Tahoma",Font.BOLD,8));
		rbOcultar.setForeground(Color.RED);
		rbMostrar.setForeground(Color.RED);
		

		
	}
	private void HazEscuchas() {
		rbMostrar.addActionListener(this);
		rbOcultar.addActionListener(this);
		this.addComponentListener(this);
	}
	public String getTexto() {
		return auxiliar;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		if( evt.getSource() == rbMostrar ) {
			texto.setText(auxiliar);
			return;
		}
		if( evt.getSource()== rbOcultar) {
			auxiliar=texto.getText();
			String cad="";
			for(int i=0 ; i<auxiliar.length();i++)
				cad+="*";
			texto.setText(cad);
			return;
		}
		
	}
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentResized(ComponentEvent arg0) {
		int w=this.getWidth();
		int h=this.getHeight();
		int x = (int)(w*0.10);
		int y = (int)(h*0.10);
		int ancho=(int)(w*0.50);
		int alto=(int)(h*0.20);
		texto.setBounds(x,y,ancho,alto);
		x = (int)(w*0.65);
		y = (int)(h*0.10);
		ancho=(int)(w*0.20);
		alto=(int)(h*0.10);
		rbOcultar.setBounds(x,y,ancho,alto);
		x = (int)(w*0.65);
		y = (int)(h*0.20);
		ancho=(int)(w*0.20);
		alto=(int)(h*0.10);
		
		rbMostrar.setBounds(x,y,ancho,alto);
		add(texto);
		add(rbOcultar);
		add(rbMostrar);
//		add(btnOrdenado);
//		add(btnOriginal);	
//		btnOrdenado.setBounds((int)(w*0.10)+(int)(w*0.50)+10,(int)(h*0.10),(int)(combo.getWidth()*0.60),(int)(h*0.075));
//		btnOriginal.setBounds((int)(w*0.10)+(int)(w*0.50)+10,(int)(h*0.10)+(int)(h*0.075),(int)(combo.getWidth()*0.60),(int)(h*0.075));

	}
	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
