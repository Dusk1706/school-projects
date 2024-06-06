package unidad2.programs;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
public class JReal extends JEntero implements KeyListener{

	public JReal() {
		this(10);
	}
	public JReal(int tamano) {
		this(tamano,1,2147000000);
	}
	public JReal(int tamano, int limInf, int limSup) {
		super(tamano,limInf,limSup);
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		char car=evt.getKeyChar();
		if(car != '.') {
			super.keyTyped(evt);
			return;
		}
		if( getText().length() == this.getTamano()  || getText().indexOf(car)>=0) {
			evt.consume();
			return;
		}
		if( getText().length()==0) {
			setText("0.");
			evt.consume();
			return;
		}
	}

}
