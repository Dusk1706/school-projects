package unidad1.programs;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
public class JTableModel extends JFrame implements ActionListener{

	JTable tabla;
	
	DefaultTableModel modelo;
	
	JButton btnMostrar,btnBorrar,btnModificar;
	
	JDialog pantalla;
	
	JTextField txtId, txtNombre, txtEdad, txtEstatura;
	JButton btnModificarDatos, btnRestaurar;
	
	public JTableModel() {
		super("Manejo de tabla y modelo");
		HazInterfaz();
		HazEscuchas();
	}
	public void HazInterfaz() {
		setSize(600,500);
		
	
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		String [] cols= {"ID","Nombre","Edad (a�os)","Estatura (cm)"};
		tabla=new JTable();
		modelo=new DefaultTableModel();
		modelo.setColumnIdentifiers(cols);
		tabla.setModel(modelo);
		
		Agrega();
		JScrollPane sp =new JScrollPane(tabla);
		add(sp);
		
		JPanel panel=new JPanel();
		//panel.setLayout(new GridLayout(0,3));
		btnMostrar=new JButton("Mostrar");
		btnBorrar = new JButton("Borrar");
		btnModificar=new JButton("Modificar");
		panel.add(btnMostrar);
		panel.add(btnBorrar);
		panel.add(btnModificar);
		add(panel,BorderLayout.SOUTH);
		setVisible(true);
		
		// Rutinas.Mensaje(tabla.getRowCount()+"<<-- Total de registros en la tabnla "+modelo.getRowCount());
	}
	public void HazEscuchas() {
		btnMostrar.addActionListener(this);
		btnBorrar.addActionListener(this);
		btnModificar.addActionListener(this);
	}
	private void Agrega() {
		Vector fila;
		for(int i=0 ; i<300; i++) {
			fila=new Vector();

			fila.addElement(i+1);
			fila.addElement(Rutinas.nextNombre(2));
			fila.addElement(Rutinas.nextInt(18,60));
			fila.addElement(Rutinas.nextInt(160, 190));
			modelo.addRow(fila);
		}
		
	}
	public static void main(String[] args) {
		new JTableModel();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource()==btnMostrar) {
			if(tabla.getSelectedRow()==-1) {
				Rutinas.Mensaje("DEBE DE SELECCIOANR UN RENGL�N DE LA TABLA");
				return;
			}
			pantalla=new JDialog();
			pantalla.setSize(500,200);
			pantalla.setModal(true);
			pantalla.setLocationRelativeTo(null);
			pantalla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			pantalla.setLayout(new GridLayout(0,2));
			
			txtId=new JTextField(modelo.getValueAt(tabla.getSelectedRow(), 0)+"");
			txtId.setEnabled(false);

			txtNombre=new JTextField(modelo.getValueAt(tabla.getSelectedRow(), 1)+"");
			txtEdad=new JTextField(modelo.getValueAt(tabla.getSelectedRow(), 2)+"");
			txtEstatura=new JTextField(modelo.getValueAt(tabla.getSelectedRow(), 3)+"");
			pantalla.add(new JLabel("Id:",JLabel.RIGHT));
			pantalla.add(txtId);
			pantalla.add(new JLabel("Nombre:",JLabel.RIGHT));
			pantalla.add(txtNombre);
			pantalla.add(new JLabel("Edad:",JLabel.RIGHT));
			pantalla.add(txtEdad);
			pantalla.add(new JLabel("Estatura:",JLabel.RIGHT));
			pantalla.add(txtEstatura);
			btnModificarDatos=new JButton("Actualizar");
			btnRestaurar=new JButton("Restaurar");
			
			btnModificarDatos.addActionListener(this);
			btnRestaurar.addActionListener(this);
			pantalla.add(btnModificarDatos);
			pantalla.add(btnRestaurar);
			pantalla.setVisible(true);
			
//			// recupero cada columna
//			int Valor1 = (int) modelo.getValueAt(tbl.getSelectedRow(), 0);
//			String Valor2 = (String) modelo.getValueAt(tbl.getSelectedRow(), 1);
//			int Valor3 = (int) modelo.getValueAt(tbl.getSelectedRow(), 2);
//			int Valor4 = (int) modelo.getValueAt(tbl.getSelectedRow(), 3);
//		Rutinas.Mensaje(Valor1+" "+Valor2+" "+Valor3+" "+ Valor4);		
//			return;
		}
		if( e.getSource()==btnBorrar) {
			if(tabla.getSelectedRow()==-1)
				return;
			int resp = JOptionPane.showConfirmDialog(null, "�Est� seguro?");
			if( resp==0)
			   modelo.removeRow((int)tabla.getSelectedRow());
			return;
		}
		if( e.getSource()==btnModificar) {
			if(tabla.getSelectedRow()==-1)
				return;
			modelo.setValueAt(9999, (int)tabla.getSelectedRow(), 2);
			return;
		}
		if( e.getSource()==	btnModificarDatos) {
			
			modelo.setValueAt(txtNombre.getText(), (int)tabla.getSelectedRow(), 1);
			modelo.setValueAt(txtEdad.getText(), (int)tabla.getSelectedRow(), 2);
			modelo.setValueAt(txtEstatura.getText(), (int)tabla.getSelectedRow(), 3);
			pantalla.setVisible(false);
			return;
		}
		if ( e.getSource()==btnRestaurar) {
			txtNombre.setText(modelo.getValueAt(tabla.getSelectedRow(), 1)+"");
			txtEdad.setText(modelo.getValueAt(tabla.getSelectedRow(), 2)+"");
			txtEstatura.setText(modelo.getValueAt(tabla.getSelectedRow(), 3)+"");	
			
			return;
		}
	}

}


