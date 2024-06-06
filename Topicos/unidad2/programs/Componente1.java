package unidad2.programs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Componente1 extends JPanel implements ActionListener {
	private JTextField texto;
	private JButton rbOcultar, rbMostrar;
	private JPanel panel;

	public Componente1() {
		HazInterfaz();
		HazEscuchas();
	}

	private void HazInterfaz() {
		setLayout(new GridLayout(0, 2));
		texto = new JTextField();
		rbOcultar = new JButton("Ocultar");
		rbMostrar = new JButton("Mostrar");
		panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		panel.add(rbOcultar);
		panel.add(rbMostrar);

		add(texto);
		add(panel);

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
