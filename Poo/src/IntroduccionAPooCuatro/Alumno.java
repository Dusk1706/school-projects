package IntroduccionAPooCuatro;

public class Alumno {

	private String numControl,nombre;
	private char carrera;
	private int cal1,cal2,cal3,cal4;
	private double promedio;

	public Alumno(String numControl, String nombre, char carrera, int cal1, int cal2, int cal3, int cal4,
			double promedio) {
		this.numControl = numControl;
		this.nombre = nombre;
		this.carrera = carrera;
		this.cal1 = cal1;
		this.cal2 = cal2;
		this.cal3 = cal3;
		this.cal4 = cal4;
		this.promedio = promedio;
	}

	public String getNumControl() {
		return numControl;
	}

	public void setNumControl(String numControl) {
		this.numControl = numControl;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getCarrera() {
		return carrera;
	}

	public void setCarrera(char carrera) {
		this.carrera = carrera;
	}

	public int getCal1() {
		return cal1;
	}

	public void setCal1(int cal1) {
		this.cal1 = cal1;
	}

	public int getCal2() {
		return cal2;
	}

	public void setCal2(int cal2) {
		this.cal2 = cal2;
	}

	public int getCal3() {
		return cal3;
	}

	public void setCal3(int cal3) {
		this.cal3 = cal3;
	}

	public int getCal4() {
		return cal4;
	}

	public void setCal4(int cal4) {
		this.cal4 = cal4;
	}

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	@Override
	public String toString() {
		String carrera2=(carrera=='S')?"Sistemas":"Tics";
		String promedio2=(promedio==0)?"Sin calcular":Double.toString(promedio);
		return "-----------------------------------------------------------------\nAlumno: " +nombre 
				+"\nNumero de control: "+numControl +"\nCarrera: " + carrera2 + "\nCalificacion 1: " + cal1
				+ ", Calificacion 2: " + cal2 + "\nCalificacion 3: " + cal3 + ", Calificacion 4: " + cal4 
				+ "\nPromedio: " + promedio2+"\n";
	}
	
	

}
