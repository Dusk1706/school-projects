package unidad2.programs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplComponentes extends JFrame {
	private Componente3 componente;

	public AplComponentes() {
		super("Probando componentes");
		HazInterfaz();
	}

	private void HazInterfaz() {
		setSize(400, 300);
		this.setLocationRelativeTo(null);
		setLayout(new GridLayout(0, 1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		componente = new Componente3();
		add(componente);
		add(new Componente3());
		add(new Componente3());
		setVisible(true);

	}

	public static void main(String[] args) {
		new AplComponentes();

	}

}
