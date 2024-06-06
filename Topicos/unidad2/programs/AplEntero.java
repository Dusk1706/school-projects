package unidad2.programs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplEntero extends JFrame implements ActionListener {
	JButton btnGrabar;

	public AplEntero() {
		HazInterfaz();
		btnGrabar.addActionListener(this);
	}

	private void HazInterfaz() {
		setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 1));
		JEntero entero = new JEntero(10);
		JEntero entero2 = new JEntero(5, 10, 100);
		add(entero, BorderLayout.NORTH);
		add(entero2, BorderLayout.SOUTH);

		UIManager.put("Label.font", new Font("tahoma", Font.BOLD, 18));
		UIManager.put("Button.font", new Font("tahoma", Font.BOLD, 22));
		UIManager.put("Button.focusInputMap", new UIDefaults.LazyInputMap(new Object[] {
				"ENTER", "pressed",
				"released ENTER", "released"
		}));
		btnGrabar = new JButton("Grabar");
		btnGrabar.setForeground(Color.MAGENTA);

		add(btnGrabar);
		setVisible(true);

	}

	public static void main(String[] args) {
		new AplEntero();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Boton con enter");

	}
}
