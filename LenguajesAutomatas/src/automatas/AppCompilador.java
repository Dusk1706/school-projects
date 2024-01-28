package automatas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class AppCompilador extends JFrame implements ActionListener {
    private JButton btnAbrir, btnLexico, btnSintactico;
    private JTextArea areaArchivo, areaErrores, areaTokens, areaTablaSimbolos;
    private JFileChooser fileChooser;
    private JScrollPane scrollPane;
    private String codigo, erroresLexicos, erroresSintacticos;
    private ArrayList<Token> tokens;
    public AppCompilador(){
        super("Proyecto Lenguajes y Automatas I");
        fileChooser = new JFileChooser();
        gui();
        escuchadores();
    }
    private void gui(){
        setSize(1200, 800);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        btnAbrir = new JButton("Abrir");
        btnAbrir.setBounds(100, 20, 150, 50);
        add(btnAbrir);

        btnLexico = new JButton("Analisis Lexico");
        btnLexico.setBounds(100, 90, 150, 50);
        btnLexico.setEnabled(false);
        add(btnLexico);

        btnSintactico = new JButton("Analisis Sintactico");
        btnSintactico.setBounds(100, 170, 150, 50);
        btnSintactico.setEnabled(false);
        add(btnSintactico);

        areaArchivo = new JTextArea();
        areaArchivo.setBounds(350, 20, 500, 400);
        areaArchivo.setEditable(false);
        scrollPane = new JScrollPane(areaArchivo);
        scrollPane.setBounds(350, 20, 500, 400);
        add(scrollPane);

        areaErrores = new JTextArea();
        areaErrores.setBounds(350, 430, 500, 310);
        areaErrores.setEditable(false);
        scrollPane = new JScrollPane(areaErrores);
        scrollPane.setBounds(350, 430, 500, 310);
        add(scrollPane);

        areaTokens = new JTextArea();
        areaTokens.setBounds(900, 20, 250, 720);
        areaTokens.setEditable(false);
        scrollPane = new JScrollPane(areaTokens);
        scrollPane.setBounds(900, 20, 250, 720);
        add(scrollPane);

        JLabel lblTablaSimbolos = new JLabel("Tabla de Simbolos");
        lblTablaSimbolos.setBounds(100, 230, 250, 20);
        add(lblTablaSimbolos);
        areaTablaSimbolos = new JTextArea();
        areaTablaSimbolos.setBounds(50, 260, 250, 480);
        areaTablaSimbolos.setEditable(false);
        scrollPane = new JScrollPane(areaTablaSimbolos);
        scrollPane.setBounds(50, 260, 250, 480);
        add(scrollPane);


        setVisible(true);
    }
    private void escuchadores(){
        btnAbrir.addActionListener(this);
        btnLexico.addActionListener(this);
        btnSintactico.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAbrir){
            int opcion = fileChooser.showOpenDialog(this);
            if(opcion == JFileChooser.APPROVE_OPTION){
                try{
                    File archivo = fileChooser.getSelectedFile();
                    String lectura = LeerArchivo.leerArchivo(archivo);
                    areaArchivo.setText(lectura);
                    codigo = lectura;
                    btnLexico.setEnabled(true);
                }catch (Exception ex){
                    JDialog dialog = new JDialog(this, "Error al abrir el archivo");
                }
            }
            return;
        }
        if(e.getSource() == btnLexico){
            AnalizadorLexico analizadorLexico = new AnalizadorLexico();
            String resultado = analizadorLexico.analizar(codigo);
            areaTokens.setText(resultado);
            String errores = analizadorLexico.getErrores();
            areaErrores.setText(errores);
            areaTablaSimbolos.setText(analizadorLexico.getTablaSimbolos());
            tokens = analizadorLexico.getTokens();
            if(errores.isEmpty()){
                areaErrores.setText("No se encontraron errores lexicos");
                btnSintactico.setEnabled(true);
            }
            return;
        }
        if(e.getSource() == btnSintactico){
            areaErrores.setText("");
            AnalizadorSintactico analizadorSintactico = new AnalizadorSintactico();
            analizadorSintactico.analizar(tokens);
            String errores = analizadorSintactico.getErroresSintacticos();
            areaErrores.setText(errores);
            if(errores.isEmpty()){
                areaErrores.setText("No se encontraron errores sintacticos");
            }
        }
    }

    public static void main(String[] args){
        AppCompilador interfaz = new AppCompilador();
    }

}