package IntroduccionAPooTres;

public class Fecha {

	private int dia;
	private int mes;
	private int anno;

	public Fecha() {

	}

	public Fecha(int dia, int mes, int anno) {
		this.dia = dia;
		this.mes = mes;
		this.anno = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	
	
	
	public String toString() {
		String dia2=(dia<10)?"0"+dia:Integer.toString(dia);
		String mes2=(mes<10)?"0"+mes:Integer.toString(mes);
		return dia2 + "-" + mes2 + "-" + anno;
	}

	public boolean fechaCorrecta() {
		boolean correcto=false;
		if (anno > 0 && mes > 0 && mes < 13 && dia > 0 && dia < 32) {
			if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 12) {
				correcto=true;
			} else if (mes == 2) {
				if (!esBisiesto()) {
					correcto=dia < 29;
				}
			} else {
				correcto=dia < 31;
			}
		}
		return correcto;
	}

	private boolean esBisiesto() {
		return (anno % 4 == 0) && ((anno % 100 != 0) || (anno % 400 == 0));
	}
	
	public void diasSiguiente() {
			if(mes<12) {
				if(mes==2) {
					if(esBisiesto()&&dia==29) {
						dia=1;
						mes++;
					}else if(dia ==28) {
					 dia=1;
					 mes++;
					}else {
						dia++;
					}
				}else {
					if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8) {
						if(dia==31) {
							dia=1;
							mes++;
						}
					}else if(dia==30) {
						dia=1;
						mes++;
					}else {
						dia++;
					}
				}
				
			}else if(dia==31){
				mes=1;
				anno++;
				dia=1;
			}else {
					dia++;
			}
	}

}
