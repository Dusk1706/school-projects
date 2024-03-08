package unidad1.programs;

import java.awt.*;
import javax.swing.*;
import java.util.Vector;
public class Tabla extends JFrame {
	public Tabla()   {
		super("Mi tabla");
		this.setSize(500,600);
		this.setLocationRelativeTo(null);
		Vector columnas = new Vector();
		/* A ese vector le agrego datos, estos datos 
           vendr�n a ser las columnas de la tabla.   */
		columnas.addElement("Columna A");
		columnas.addElement("Columna B");
		columnas.addElement("Columna C");
		/*
            Creo una instancia de la clase Vector llamada 'filas�,
            este vector tendr� todas las filas de la tabla.
		 */
		Vector filas = new Vector();
		/*  objeto llamado 'fila', esto representar� a           una fila en particular y cada elemento que agregue a este vector ser� una celda. */
		Vector fila = new Vector();
		fila.addElement("X");
		fila.addElement("Y");
		fila.addElement("Z");
		filas.add(fila);
		fila=new Vector();
		fila.addElement("A");
		fila.addElement("B");
		fila.addElement(5);
		filas.addElement(fila);
		
		for(int i=0 ; i<10_000_000 ; i++) {
			fila=new Vector();
			fila.addElement(Rutinas.Color());
			fila.addElement(Rutinas.nextInt(10,20));
			if( i  % 2 ==0) {
			fila.addElement((i+1)+"");
			fila.addElement((i+1)+"");
			}
			
			filas.addElement(fila);
		}
		JTable tbl = new JTable(filas,columnas);
		/* Creo una instancia de JScrollPane y le paso como                  parametro la tabla */
		JScrollPane panel =new JScrollPane(tbl);
		/* Por ultimo agrego ese objeto de JScrollPane al contenedor de la ventana */
		add(panel);
		this.setVisible(true);
	}
	public static void main(String[] args)  {
		new Tabla();
	}
}


