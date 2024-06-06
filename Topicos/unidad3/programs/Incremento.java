package unidad3.programs;

public class Incremento extends Thread {
	static int a;
	public Incremento() {
		a=0;
	}
	public void run() {
		Suma();
	}
	static public synchronized void Suma() {
		a++;
	}
	public String toString() {
		return "a= "+a;
	}
}
