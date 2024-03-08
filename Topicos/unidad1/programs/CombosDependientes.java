package unidad1.programs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CombosDependientes extends JFrame implements ActionListener, ItemListener{
	
	private JComboBox cmbEstados,cmbCiudades ;
	private String [][] mCiudades= {{"Tijuana","Mexicali","Rosarito","Tecate"},
							  {"La Paz","Los Cabos","Santa Rosalia"},
							  {"Hemosillo","Guaymas","Nogales","Obregon","Navojoa"},
							  {"Culiacan","Los Mochis","Mazatlan","Guasave"}};
	public CombosDependientes() {
		super("Combos depemdientes");
		HazInterfaz();
		HazEscuchas();
	}
	private void HazInterfaz() {
		setSize(400,100);
		UIManager.put("Label.font", new Font("tahoma", Font.BOLD, 18));
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(0,2,5,5));
		String [] vEstados= {"seleccione","BC","BCS","SONORA","SINALOA","CHIHUAHUA","DURANGO"};
		cmbEstados = new JComboBox(vEstados);
		cmbCiudades=new JComboBox();
		add(new JLabel("Estados",JLabel.CENTER));
		add(new JLabel("Ciudades",JLabel.CENTER));
		add(cmbEstados);
		add(cmbCiudades);
		
		
		setVisible(true);
	}
	private void HazEscuchas() {
		cmbEstados.addActionListener(this);
		cmbEstados.addItemListener(this);
	}
	public static void main(String [] a) {
		new CombosDependientes();
	}
	@Override
	public void itemStateChanged(ItemEvent evt) {
		if(evt.getStateChange() != ItemEvent.SELECTED || cmbEstados.getSelectedItem().equals("seleccione"))
			return;	
		int idEstado = cmbEstados.getSelectedIndex()-1;
		cmbCiudades.removeAllItems();
		cmbCiudades.addItem("Seleccione");
		for(int i=0 ; i<mCiudades[idEstado].length ; i++) {
			cmbCiudades.addItem(mCiudades[idEstado][i]);
		}
		cmbCiudades.insertItemAt("Seleccionx", 1);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("estoy en el action performed");
		
	}
}
