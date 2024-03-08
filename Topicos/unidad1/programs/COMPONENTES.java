package unidad1.programs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class COMPONENTES extends JFrame implements ActionListener{
	JRadioButton rb1,rb2,rb3;
	JComboBox cmb2;
	JTextField txt1;
	JButton btnImgDeshabilitada;
	public COMPONENTES() {
		super("CREACI�N DE LOS COMPONENTES SWING");
		HazInterfaz();
		HazEscuchas();
	}
	private void HazInterfaz() {
		setSize(400,800);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(0,2));
		// CAMBIAR LA IMAGEN DE LA PANTALLA 
		setIconImage (new ImageIcon("caballo.jpg").getImage());

	
		// ETIQUETA CON TEXTO E IMAGEN
				ImageIcon imagen =  new ImageIcon(Rutinas.AjustarImagen("caballo.jpg", 50, 50).getImage());
				JLabel etiqueta = new JLabel("CABALLO", imagen,JLabel.LEFT);
				add(new JLabel("ETQUETA CON IMAGEN Y TEXTO"));
				add(etiqueta);
		// Bot�n
		add(new JLabel("BOT�N",JLabel.RIGHT));
		add(new JButton());
		// Bot�n con texto
		add(new JLabel("BOT�N TEXTO",JLabel.CENTER));
		add(new JButton("Texto"));
		// Bot�n con IMAGEN
		add(new JLabel("Bot�n CON IMAGEN",JLabel.CENTER));
		add( new JButton(new ImageIcon("caballo.jpg")));
		// Bot�n con IMAGEN y TEXTO
		add(new JLabel("Bot�n CON IMAGEN Y TEXTO",JLabel.CENTER));
		add( new JButton("CABALLO",new ImageIcon("caballo.jpg")));
		
		// Boton con imagen ajustada al tama�o
		add(new JLabel("Boton con imagen ajustada"));
		JButton btnImagen=new JButton();
		add(btnImagen);
		// boton con imagen y deshabilitado 
		
		btnImgDeshabilitada= new JButton(new ImageIcon("caballo.jpg"));
		add(new JLabel("Btn imagen deshabilitada"));
		add(btnImgDeshabilitada);
		// caja de texto 
		
		add(new JLabel("Caja de texto"));
		add(txt1= new JTextField());
		
		add(new JLabel("Caja de texto inicializada"));
		add( new JTextField("escriba aqui"));
		
		add(new JLabel("Caja de texto especificando ancho"));
		
		add( new JTextField(20));
		
		// readio buttons
		add( new JLabel("Radio button"));
		add ( rb1=new JRadioButton());
		// readio buttons
		add( new JLabel("Radio button"));
		add ( rb2=new JRadioButton("Masculino"));
		// readio buttons
		add( new JLabel("Radio button"));
		add ( rb3=new JRadioButton("Masculino",true));
				
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rb1);
		grupo.add(rb2);
		grupo.add(rb3);
		// checks box
		add(new JLabel("Check box"));
		add( new JCheckBox());
		// checks box
		add(new JLabel("Check box"));
		add( new JCheckBox("Polo Ralph Lauren"));
		// checks box
		add(new JLabel("Check box"));
		add( new JCheckBox("Scappino",true));
		// combo
		add(new JLabel("combo vacio"));
		add( new JComboBox());
		
		// combo con la valore
		String [] cds= {"CULIACAN","MAZATLAN","GUASAVE","LOS MOCHIS"};
		add(new JLabel("COMBO CON INFORMACION"));
		add(cmb2=new JComboBox(cds));
		setVisible(true);
		System.out.println(btnImagen.getHeight()+" ***Boton "+btnImagen.getWidth());

		btnImagen.setIcon(Rutinas.AjustarImagen("caballo.jpg", btnImagen.getWidth(), btnImagen.getHeight()));

		

	}
	private void HazEscuchas() {
		rb1.addActionListener(this);
		cmb2.addActionListener(this);
		txt1.addActionListener(this);
		
		btnImgDeshabilitada.addActionListener(this);
	}
	public static void main(String[] args) {
		new COMPONENTES();

	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		if( evt.getSource() == btnImgDeshabilitada) {
			JButton boton = (JButton) evt.getSource();
System.out.println(btnImgDeshabilitada.getHeight()+" Boton "+boton.getWidth());
			boton.setEnabled(false);
			ImageIcon imagen2 = Rutinas.AjustarImagen("caballo.jpg",100,100);
			boton.setDisabledIcon(imagen2);


				
			return;
		}
		
		if( evt.getSource()== txt1)
			System.out.println(txt1.getText());
	}

}
