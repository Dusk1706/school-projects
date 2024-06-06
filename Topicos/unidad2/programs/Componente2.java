package unidad2.programs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Componente2 extends JPanel implements ActionListener {
	private JTextField texto;
	private JButton rbOcultar, rbMostrar;
	private JPanel panel;

	public Componente2() {
		HazInterfaz();
		HazEscuchas();
	}

	private void HazInterfaz() {
		setLayout(null);
		texto = new JTextField();
		rbOcultar = new JButton("Ocultar");
		rbMostrar = new JButton("Mostrar");

		texto.setBounds(20, 30, 200, 30);
		rbOcultar.setBounds(235, 30, 80, 15);
		rbMostrar.setBounds(235, 45, 80, 15);
		add(texto);
		add(rbOcultar);
		add(rbMostrar);

	}

	private void HazEscuchas() {
		rbMostrar.addActionListener(this);
		rbOcultar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == rbMostrar) {

			return;
		}
		if (evt.getSource() == rbOcultar) {

			return;
		}

	}

}
