package unidad3.proyecto1;

public class CreditoCriterioNuevo {

    private String renta, patrimonio, estudios, funcionario, autorizo;
    private int credito, edad, hijos;

    public CreditoCriterioNuevo() {
        edad = getEdadRandom();
        estudios = getEstudiosRandom(edad);
        patrimonio = getPatrimonioRandom(estudios);
        renta = getRentaRandom(patrimonio);
        hijos = getHijosRandom(renta, patrimonio, edad);
        funcionario = getFuncionarioRandom(estudios, renta);
        credito = getCreditoRandom(renta, patrimonio);
        autorizo = getAutorizoRandom(patrimonio, credito);
    }

    private int getEdadRandom() {
        int probabilidad = Rutinas.nextInt(1, 100);
        if(probabilidad <= 10) {
            return Rutinas.nextInt(20, 30);
        }
        if(probabilidad <= 50) {
            return Rutinas.nextInt(31, 40);
        }
        if(probabilidad <= 80) {
            return Rutinas.nextInt(41, 50);
        }
        return Rutinas.nextInt(51, 60); 
	}

    private String getEstudiosRandom(int edad) {
        String[] nominal = { "NINGUNO", "LICENCIATURA", "POSGRADO" };
        if (edad < 23) {
            return nominal[0];
        }

        if(edad < 26) {
            int probabilidad = Rutinas.nextInt(1, 100);
            if(probabilidad <= 20) {
                return nominal[0];
            }
            return nominal[1];
        }

        int probabilidad = Rutinas.nextInt(1, 100);
        if(probabilidad <= 10) {
            return nominal[0];
        }
        return probabilidad <= 70 ? nominal[1] : nominal[2];

	}

    private String getPatrimonioRandom(String estudios) {
        if (estudios.equals("NINGUNO")) {
            int probabilidad = Rutinas.nextInt(1, 100);
            if (probabilidad <= 10) {
                return "ALTO";
            }
            return probabilidad <= 40 ? "MEDIO" : "BAJO";
        }

        if (estudios.equals("LICENCIATURA")) {
            int probabilidad = Rutinas.nextInt(1, 100);
            if (probabilidad <= 20) {
                return "ALTO";
            }
            return probabilidad <= 70 ? "MEDIO" : "BAJO";
        }

        int probabilidad = Rutinas.nextInt(1, 100);
        if (probabilidad <= 40) {
            return "ALTO";
        }
        return probabilidad <= 90 ? "MEDIO" : "BAJO";
	}

    private String getRentaRandom(String patrimonio) {
        String[] nominal = { "ALTO", "MEDIO", "BAJO" };
        if (patrimonio.equals("ALTO")) {
            int probabilidad = Rutinas.nextInt(1, 100);
            if (probabilidad <= 40) {
                return nominal[0];
            }
            return probabilidad <= 70 ? nominal[1] : nominal[2];
        }

        if (patrimonio.equals("MEDIO")) {
            int probabilidad = Rutinas.nextInt(1, 100);
            if (probabilidad <= 20) {
                return nominal[0];
            }
            return probabilidad <= 70 ? nominal[1] : nominal[2];
        }

        int probabilidad = Rutinas.nextInt(1, 100);
        if (probabilidad <= 5) {
            return nominal[0];
        }
        return probabilidad <= 35 ? nominal[1] : nominal[2];
    }
    
    private int getHijosRandom(String renta, String patrimonio, int edad) {
        int hijos = 0;
        int probabilidad = Rutinas.nextInt(1, 100);
        if (renta.equals("ALTO") && patrimonio.equals("ALTO") && edad >= 30) {
            if (probabilidad <= 60) {
                hijos = Rutinas.nextInt(0, 3);
            } else {
                hijos = Rutinas.nextInt(1, 6);
            }
        } else if (renta.equals("MEDIO") && patrimonio.equals("MEDIO") && edad >= 25) {
            if (probabilidad <= 40) {
                hijos = Rutinas.nextInt(0, 2);
            } else {
                hijos = Rutinas.nextInt(1, 4);
            }
        } else if (renta.equals("BAJO") && patrimonio.equals("BAJO") && edad >= 20) {
            if (probabilidad <= 80) {
                hijos = Rutinas.nextInt(0, 1);
            } else {
                hijos = Rutinas.nextInt(1, 2);
            }
        }
        return hijos;
    }
    
    private String getFuncionarioRandom(String estudios, String renta) {
        int probabilidad = Rutinas.nextInt(1, 100);
        if (estudios.equals("NINGUNO") && renta.equals("BAJO")) {
            return probabilidad <= 3 ? "SI" : "NO";
        }

        if (renta.equals("ALTO")) {
            return probabilidad <= 90 ? "SI" : "NO";
        }

        if (renta.equals("MEDIO")) {
            if (estudios.equals("NINGUNO")) {
                return probabilidad <= 5 ? "SI" : "NO";
            }else {
                return probabilidad <= 50 ? "SI" : "NO";
            }
        }
        return probabilidad <= 10 ? "SI" : "NO";
    }

    private int getCreditoRandom(String renta, String patrimonio) {
        int credito;
        if (patrimonio.equals("ALTO")) {
            if (renta.equals("ALTO")) {
                credito = Rutinas.nextInt(150, 300) * 1000;
            } else if (renta.equals("MEDIO")) {
                credito = Rutinas.nextInt(100, 200) * 1000;
            } else {
                credito = Rutinas.nextInt(50, 100) * 1000;
            }
        } else if (patrimonio.equals("MEDIO")) {
            if (renta.equals("ALTO")) {
                credito = Rutinas.nextInt(100, 200) * 1000;
            } else if (renta.equals("MEDIO")) {
                credito = Rutinas.nextInt(50, 150) * 1000;
            } else {
                credito = Rutinas.nextInt(20, 80) * 1000;
            }
        } else {
            if (renta.equals("ALTO")) {
                credito = Rutinas.nextInt(50, 150) * 1000;
            } else if (renta.equals("MEDIO")) {
                credito = Rutinas.nextInt(10, 110) * 1000;
            } else {
                credito = Rutinas.nextInt(1, 50) * 1000;
            }
        }
        return credito;
    }

    private String getAutorizoRandom(String patrimonio, int credito) {
        int probabilidad = Rutinas.nextInt(1, 100);
        if (patrimonio.equals("ALTO")) {
            if (credito <= 20_000) {
                return probabilidad <= 95 ? "SI" : "NO";
            }
            if(credito <= 50_000) {
                return probabilidad <= 80 ? "SI" : "NO";
            }
            if (credito <= 100_000) {
                return probabilidad <= 60 ? "SI" : "NO";
            }else {
                return probabilidad <= 15 ? "SI" : "NO";
            }
        }
        if (patrimonio.equals("MEDIO")) {
            if (credito <= 20_000) {
                return probabilidad <= 70 ? "SI" : "NO";
            }
            if(credito <= 50_000) {
                return probabilidad <= 55 ? "SI" : "NO";
            }
            if (credito <= 100_000) {
                return probabilidad <= 30 ? "SI" : "NO";
            }else {
                return probabilidad <= 7 ? "SI" : "NO";
            }
        }
        if (credito <= 20_000) {
            return probabilidad <= 35 ? "SI" : "NO";
        }
        if(credito <= 50_000) {
            return probabilidad <= 25 ? "SI" : "NO";
        }
        if (credito <= 100_000) {
            return probabilidad <= 15 ? "SI" : "NO";
        }else {
            return probabilidad <= 3 ? "SI" : "NO";
        }
    }

    public String getRenta() {
        return renta;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public String getEstudios() {
        return estudios;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public String getAutorizo() {
        return autorizo;
    }

    public int getCredito() {
        return credito;
    }

    public int getEdad() {
        return edad;
    }

    public int getHijos() {
        return hijos;
    }

    @Override
    public String toString() {
        return renta + "," + patrimonio + "," + credito + "," + edad + "," + hijos + "," + funcionario + "," + estudios + "," + autorizo;
    }
}
