package IntroduccionAPooCinco;

public class Agenda {

	String nombre, telefono;

	public Agenda(String nombre, String telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		String txt;
		if(nombre==null) {
			txt="Espacio disponible";
		}else {
			txt="Nombre: "+nombre+" Telefono: "+telefono;
		}
		return txt;
	}
	
	

}
