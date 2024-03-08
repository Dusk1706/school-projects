package unidad1.programs;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class Captura extends JFrame implements ActionListener, FocusListener{
	JTextField txtNombre, txtEdad, txtEstatura;
	JButton btnLimpiar, btnGrabar;
	Color colorBackgroud;
	Font  fontTexto;
	public Captura() {
		super("Captura y validaci�n de datos");
		HazInterfaz();
		HazEscuchas();
	}
	private void HazInterfaz() {
		setSize(400,300);
		setLayout(new GridLayout(0,2,10,10));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		txtNombre= new JTextField();
		txtEdad=new JTextField();
		txtEstatura=new JTextField();
		colorBackgroud=txtNombre.getBackground();
		fontTexto=txtNombre.getFont();
		btnLimpiar=new JButton("Limpiar");
		btnGrabar=new JButton("Grabar");
		
		 UIManager.put("Label.font", new Font("tahoma", Font.BOLD, 18));
			
		
		
		add(new JLabel("Nombre",JLabel.RIGHT));
		add(txtNombre);
		add(new JLabel("Edad",JLabel.RIGHT));
		add(txtEdad);
		add(new JLabel("Estatura",JLabel.RIGHT));
		add(txtEstatura);
		add(btnLimpiar);
		add(btnGrabar);
		
		
		setVisible(true);
	}
	private void HazEscuchas() {
		btnLimpiar.addActionListener(this);
		
		txtNombre.addActionListener(this);
		txtEdad.addActionListener(this);
		txtEstatura.addActionListener(this);
		
		txtNombre.addFocusListener(this);
		txtEdad.addFocusListener(this);
		txtEstatura.addFocusListener(this);
		
		btnLimpiar.addFocusListener(this);
		
		btnGrabar.addActionListener(this);
		
	}
	public static void main(String [] a) {
		new Captura();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==btnLimpiar) {
			txtNombre.setText("");
			txtEdad.setText("");
			txtEstatura.setText("");
			txtNombre.requestFocus();
			return;
		}
		if( evt.getSource()==txtNombre) {
			String texto=txtNombre.getText();
			if(  texto.length()==0) {
				Rutinas.Mensaje("Debe escribir el nombre");
				return;
			}
			txtEdad.requestFocus();
			return;
		}
		if( evt.getSource()==txtEdad) {
			if( !Validar(txtEdad.getText())) {
				Rutinas.Mensaje("La edad escrita es incorrecta");
				return;
			}
			long edad=Long.parseLong(txtEdad.getText());
			if(edad<18 || edad>60) {
				Rutinas.Mensaje("La edad no est� en e� rango permitido");
				return;
			}
			txtEstatura.requestFocus();
			return;
		}
		if( evt.getSource()==txtEstatura) {
			btnGrabar.requestFocus();
			return;
		}
		if( evt.getSource()==btnGrabar ) {
			// validad el valor de las cajas de texto
			String texto=txtNombre.getText();
			if(  texto.length()==0) {
				Rutinas.Mensaje("Debe escribir el nombre");
				return;
			}
			if( !Validar(txtEdad.getText())) {
				Rutinas.Mensaje("La edad escrita es incorrecta");
				txtNombre.requestFocus();
				return;
			}
			long edad=Long.parseLong(txtEdad.getText());
			if(edad<18 || edad>60) {
				Rutinas.Mensaje("La edad no est� en e� rango permitido");
				txtEdad.requestFocus();
				return;
			}
			long estatura=Long.parseLong(txtEstatura.getText());
			if(estatura<18 || estatura>60) {
				Rutinas.Mensaje("La estatura no est� en el rango permitido");
				txtEstatura.requestFocus();
				return;
			}
			return;
		}
		
	}
	public boolean Validar(String texto) {
		/*
		try {
			long dato=Long.parseLong(texto);

		} catch (Exception e) {
			return false;
		}
		return true;
		*/
		for(int i=0 ; i<texto.length() ; i++) {
			if(texto.charAt(i)<'0' || texto.charAt(i)>'9')
				return false;
		}
		return true;
			
			
	}
	@Override
	public void focusGained(FocusEvent evt) {
		if ( evt.getSource() instanceof JTextField) {
			JTextField aux = (JTextField) evt.getSource();
			aux.setBackground(Color.LIGHT_GRAY);
			aux.setFont(new Font("Tahoma",Font.ITALIC,22));
			aux.selectAll();
			aux.setBorder(new LineBorder(Color.BLUE));
			aux.setFocusTraversalKeysEnabled(false);
		}
		
	}
	@Override
	public void focusLost(FocusEvent evt) {
		if ( evt.getSource() instanceof JTextField) {
			JTextField aux = (JTextField) evt.getSource();
			aux.setBackground(colorBackgroud);
			aux.setFont(fontTexto);
			aux.setBorder(new LineBorder(Color.LIGHT_GRAY));

		}	
		
	}
	
}
