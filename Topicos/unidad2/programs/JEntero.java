package unidad2.programs;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
public class JEntero extends JTextField implements KeyListener, FocusListener{
	private int tamano;
	private long limInf, limSup;
	private Color colorBackgroud;
	private Font  fontTexto;
	public JEntero() {
		this(3);
	}
	public JEntero(int tamano) {
		this(tamano, 1, 2147000000);
	}
	public JEntero(int tamano, int limInf, int limSup) {
		this.tamano=tamano;
		HazEscuchas();
		colorBackgroud=getBackground();
		fontTexto=getFont();
		this.limInf=limInf;
		this.limSup=limSup;
	}
	private void HazEscuchas() {
		this.addKeyListener(this);
		this.addFocusListener(this);
	}
	public long getCantidad() {
		return Long.parseLong(getText().replace(",", ""));
	}
	@Override
	public void keyPressed(KeyEvent evt) {
		if(evt.isControlDown()) {
			evt.consume();
			return;
		}
		//consume la tecla Inicio,flecha izq o flecha der
		
		if(evt.getKeyCode()==36 ||evt.getKeyCode()==37 || evt.getKeyCode()==39){
			evt.consume();
			return;
		}		
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		if( getText().length()==0 || getText().charAt(0)=='-' && getText().length()==1  ) {
			this.setBorder(new LineBorder(Color.GRAY));
			return; 
		}
		long cantidad=getCantidad();
		if( cantidad < limInf || cantidad>limSup)
			this.setBorder(new LineBorder(Color.red));
		else
			this.setBorder(new LineBorder(Color.green));
		setText(Rutinas.PonComas( getCantidad()) );
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		if( getText().length() == tamano) {
			evt.consume();
			Toolkit.getDefaultToolkit().beep();
			return;
		}
		char car = evt.getKeyChar();
//		if ( !Character.isDigit(car) && car != '-') {
//			evt.consume();
//			return;
//		}
		if ( "0123456789-".indexOf(car)==-1) {
			evt.consume();
			return;
		}
		if ( car =='-'&& getText().indexOf(car)>=0) {
			evt.consume();
			return;
		}
		if ( car == '-') {
			setText(car+getText());
			evt.consume();
		}
	}
	@Override
	public void focusGained(FocusEvent evt) {
		JTextField aux = (JTextField) evt.getSource();
		aux.setBackground(Color.LIGHT_GRAY);
		aux.setFont(new Font("Tahoma",Font.ITALIC,22));
		aux.selectAll();
	//	aux.setBorder(new LineBorder(Color.BLUE));
		aux.setFocusTraversalKeysEnabled(false);	
		
	}
	@Override
	public void focusLost(FocusEvent evt) {
		JTextField aux = (JTextField) evt.getSource();
		aux.setBackground(colorBackgroud);
		aux.setFont(fontTexto);
		//aux.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		
	}
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}
	public long getLimInf() {
		return limInf;
	}
	public void setLimInf(long limInf) {
		this.limInf = limInf;
	}
	public long getLimSup() {
		return limSup;
	}
	public void setLimSup(long limSup) {
		this.limSup = limSup;
	}
	public Color getColorBackgroud() {
		return colorBackgroud;
	}
	public void setColorBackgroud(Color colorBackgroud) {
		this.colorBackgroud = colorBackgroud;
	}
	public Font getFontTexto() {
		return fontTexto;
	}
	public void setFontTexto(Font fontTexto) {
		this.fontTexto = fontTexto;
	}

}
