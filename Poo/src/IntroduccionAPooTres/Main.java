package IntroduccionAPooTres;

import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Fecha dia =pideDatos();
		for(int i=0;i<10;i++) {
			dia.diasSiguiente();
			System.out.println(dia.toString());
		}
	}

	public static Fecha pideDatos() {
		Fecha p=new Fecha();
		do {
			System.out.println("Ingresa el dia ");
			p.setDia(sc.nextInt());
			System.out.println("Ingresa el mes");
			p.setMes(sc.nextInt());
			System.out.println("Ingresa el año");
			p.setAnno(sc.nextInt());
			if(!p.fechaCorrecta()) {
				System.out.println("Fecha incorrecta ingresa de nuevo los datos");
			}
		}while(!p.fechaCorrecta());
		return p;
	}
	
	
	

}
