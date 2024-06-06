package unidad3.proyecto1;

public class CreditoCriterioViejo {

    private String renta, patrimonio, estudios, funcionario, autorizo;
    private int credito, edad, hijos;

    public CreditoCriterioViejo() {
        renta = getRentaRandom();
        patrimonio = getPatrimonioRandom(renta);
        credito = getCreditoRandom();
        edad = getEdadRandom();
        hijos = getHijosRandom(renta, patrimonio);
        estudios = getEstudiosRandom();
        funcionario = getFuncionarioRandom(estudios);
        autorizo = getAutorizoRandom();
    }

    private String getRentaRandom() {
		String[] nominal = { "ALTO", "MEDIO", "BAJO" };
		return nominal[Rutinas.nextInt(0, nominal.length - 1)];
	}

	private String getPatrimonioRandom(String renta) {
        int probabilidad = Rutinas.nextInt(1, 100);
        if (renta.equals("BAJO")) {
            return probabilidad <= 90 ? "BAJO" : "MEDIO";
        } else {
            if(probabilidad <= 20) {
                return "BAJO";
            } else {
                return probabilidad <= 60 ? "ALTO" : "MEDIO";
            }
        }
	}

    private int getCreditoRandom() {
		return Rutinas.nextInt(1, 100) * 1000;
	}

    private int getEdadRandom() {
		return Rutinas.nextInt(20, 60);
	}

    private int getHijosRandom(String renta, String patrimonio) {
        if(renta.equals("BAJO") && patrimonio.equals("BAJO")) {
            return Rutinas.nextInt(4, 6);
        }
		return Rutinas.nextInt(0, 2);
	}

	private String getEstudiosRandom() {
		String[] nominal = { "NINGUNO", "LICENCIATURA", "POSGRADO" };
		return nominal[Rutinas.nextInt(0, nominal.length - 1)];
	}

    private String getFuncionarioRandom(String estudios) {
        int probabilidad = Rutinas.nextInt(1, 100);
        if(estudios.equals("NINGUNO")) {
            return probabilidad <= 50 ? "SI" : "NO";
        }else {
            return probabilidad <= 80 ? "SI" : "NO";
        }
    }

    private String getAutorizoRandom() {
        int probabilidad = Rutinas.nextInt(1, 100);
        return probabilidad <= 75 ? "SI" : "NO";
    } 

    @Override
    public String toString() {
        return renta + "," + patrimonio + "," + credito + "," + edad + "," + hijos + "," + funcionario + "," + estudios + "," + autorizo;
    }
}
