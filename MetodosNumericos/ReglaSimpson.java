package Unidad4;

import java.util.Scanner;

public class ReglaSimpson {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Instituto Tecnologico de Culiacan");
        System.out.println("Ing. En Sistemas Computacionales");
        System.out.println("Dylan Michel Garcia Figueroa");
        System.out.println("De 9:00 a 10:00 horas");
        System.out.println("Integracion Numerica - Metodo de Simpson");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.print("Ingresa la pregunta del problema: ");
        String pregunta=entrada.nextLine();
        System.out.print("Ingresa el valor real del problema: ");
        double Vreal=entrada.nextDouble();
        System.out.print("Ingresa el limite inferior (a): ");
        double a=entrada.nextInt();
        System.out.print("Ingresa el limite superior (b): ");
        double b=entrada.nextInt();
        System.out.print("Ingrese la unidad del problema: ");
        entrada.nextLine();
        String unidad=entrada.nextLine();
        
        while(true) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Metodo Reglas de Simpson");
            System.out.println("Regla 1 n=2");
            System.out.println("Regla 2 n=3");
            System.out.println("Regla 3 n>2 y n=par");
            System.out.println("Regla 4 n>3, n=impar y n=*3");
            System.out.println("Fin n=0");
            System.out.print("Cual es el numero de divisiones: ");
            int n = entrada.nextInt();
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Instituto Tecnologico de Culiacan");
            System.out.println("Ing. En Sistemas Computacionales");
            System.out.println("Dylan Michel Garcia Figueroa");
            System.out.println("De 9:00 a 10:00 horas");
            System.out.println("Integracion Numerica - Metodo de Simpson");
            System.out.println("Funcion f(x)=100 + ((8*Math.sqrt(x))*1.0955)");
            if (n == 0) {
                break;
            } else if (n == 2) {
                System.out.println("Simpson de 1/3 formula simple");
                System.out.println("Pregunta: "+pregunta);
                System.out.println("--------------------------------------------------------------------------------------");
                double h = (b - a) / n;
                double fa = 100 + ((8*Math.sqrt(a))*1.0955);
                double x1 = a + h;
                double fx1 = 100 + ((8*Math.sqrt(x1))*1.0955);
                double fb = 100 + ((8*Math.sqrt(b))*1.0955);
                double Vcalc = ((1 * h) / 3) * (fa + (4 * fx1) + fb);
                double error = Math.abs(Vreal - Vcalc);
                System.out.println("--------------------------------------------------");
                System.out.printf("| %2s | %3s   | %7s    | %6s | %7s    |%n","Pxy","X","f(x)","Factor","Area");
                System.out.println("--------------------------------------------------");
                System.out.printf("|  %s  | %05.2f | %8.6f | %3s    | %8.6f |%n","1",a,fa,"1",fa);
                System.out.println("--------------------------------------------------");
                System.out.printf("|  %s  | %05.2f | %8.6f | %3s    | %8.6f |%n","2",x1,fx1,"4",fx1*4);
                System.out.println("--------------------------------------------------");
                System.out.printf("|  %s  | %05.2f | %8.6f | %3s    | %8.6f |%n","3",b,fb,"1",fb);
                System.out.println("--------------------------------------------------");
                System.out.println("No. de divisiones: "+n);
                System.out.printf("Valor real de la integral: %.6f %s%n",Vreal,unidad);
                System.out.printf("Valor por el metodo: %.6f %s%n",Vcalc,unidad);
                System.out.printf("Error del metodo: %.6f %s%n",error,unidad);
            } else if (n == 3) {
                System.out.println("Simpson de 3/8 formula simple");
                System.out.println("Pregunta: "+pregunta);
                System.out.println("--------------------------------------------------------------------------------------");
                double h = (b - a) / n;
                double fa = 100 + ((8*Math.sqrt(a))*1.0955);
                double x1 = a + (1 * h);
                double x2 = a + (2 * h);
                double fx1 = 100 + ((8*Math.sqrt(x1))*1.0955);
                double fx2 = 100 + ((8*Math.sqrt(x2))*1.0955);
                double fb = 100 + ((8*Math.sqrt(b))*1.0955);
                double Vcalc = ((3 * h) / 8) * (fa + (3 * fx1) + (3 * fx2) + fb);
                double error = Math.abs(Vreal - Vcalc);
                System.out.println("--------------------------------------------------");
                System.out.printf("| %2s | %3s   | %7s    | %6s | %7s    |%n","Pxy","X","f(x)","Factor","Area");
                System.out.println("--------------------------------------------------");
                System.out.printf("|  %s  | %05.2f | %8.6f | %3s    | %8.6f |%n","1",a,fa,"1",fa);
                System.out.println("--------------------------------------------------");
                System.out.printf("|  %s  | %05.2f | %8.6f | %3s    | %8.6f |%n","2",x1,fx1,"3",fx1*3);
                System.out.println("--------------------------------------------------");
                System.out.printf("|  %s  | %05.2f | %8.6f | %3s    | %8.6f |%n","3",x2,fx2,"3",fx2*3);
                System.out.println("--------------------------------------------------");
                System.out.printf("|  %s  | %05.2f | %8.6f | %3s    | %8.6f |%n","4",b,fb,"1",fb);
                System.out.println("--------------------------------------------------");
                System.out.println("No. de divisiones: "+n);
                System.out.printf("Valor real de la integral: %.6f %s%n",Vreal,unidad);
                System.out.printf("Valor por el metodo: %.6f %s%n",Vcalc,unidad);
                System.out.printf("Error del metodo: %.6f %s%n",error,unidad);
            } else if (n > 2 && n % 2 == 0) {
                System.out.println("Simpson de 1/3 formula compleja");
                System.out.println("Pregunta: "+pregunta);
                System.out.println("--------------------------------------------------------------------------------------");
                double h = (b - a) / n;
                double Vcalc=0;
                System.out.println("--------------------------------------------------");
                System.out.printf("|  %2s | %3s   | %7s    | %6s | %7s    |%n","Pxy","X","f(x)","Factor","Area");
                System.out.println("--------------------------------------------------");
                for(int i = 1; i <= n + 1; i++) {
                    if(i==1 || i==n+1) {
                        double x = (i==1)?a:b;
                        double fx=100 + ((8*Math.sqrt(x))*1.0955);
                        Vcalc+=fx;
                        System.out.printf("|  %02d  | %05.2f | %8.6f | %3s    | %8.6f |%n",i,x,fx,"1",fx);
                        System.out.println("--------------------------------------------------");
                    }else if(i%2==0) {
                        double x = a + ((i-1) * h);
                        double fx=100 + ((8*Math.sqrt(x))*1.0955);
                        Vcalc+=fx*4;
                        System.out.printf("|  %02d  | %05.2f | %8.6f | %3s    | %8.6f |%n",i,x,fx,"4",fx*4);
                        System.out.println("--------------------------------------------------");
                    }else {
                        double x = a + ((i-1) * h);
                        double fx=100 + ((8*Math.sqrt(x))*1.0955);
                        Vcalc+=fx*2;
                        System.out.printf("|  %02d  | %05.2f | %8.6f | %3s    | %8.6f |%n",i,x,fx,"2",fx*2);
                        System.out.println("--------------------------------------------------");
                    }
                }
                Vcalc=Vcalc*(h/3);
                double error = Math.abs(Vreal - Vcalc);
                System.out.println("No. de divisiones: "+n);
                System.out.printf("Valor real de la integral: %.6f %s%n",Vreal,unidad);
                System.out.printf("Valor por el metodo: %.6f %s%n",Vcalc,unidad);
                System.out.printf("Error del metodo: %.6f %s%n",error,unidad);
            } else if (n > 3 && n % 2 != 0 && n % 3 == 0) {
                System.out.println("Simpson de 3/8 formula compleja");
                System.out.println("Pregunta: "+pregunta);
                System.out.println("--------------------------------------------------------------------------------------");
                double h = (b - a) / n;
                double Vcalc = 0;
                System.out.println("--------------------------------------------------");
                System.out.printf("|  %2s | %3s   | %7s    | %6s | %7s    |%n","Pxy","X","f(x)","Factor","Area");
                System.out.println("--------------------------------------------------");
                for (int i = 1; i <= n + 1; i++) {
                    if (i==1 || i==n+1) {
                        double x = (i==1)?a:b;
                        double fx=100 + ((8*Math.sqrt(x))*1.0955);
                        Vcalc+=fx;
                        System.out.printf("|  %02d  | %05.2f | %8.6f | %3s    | %8.6f |%n",i,x,fx,"1",fx);
                        System.out.println("--------------------------------------------------");
                    } else if ((i-1) % 3 == 0) {
                        double x = a + ((i-1) * h);
                        double fx=100 + ((8*Math.sqrt(x))*1.0955);
                        Vcalc+=fx*2;
                        System.out.printf("|  %02d  | %05.2f | %8.6f | %3s    | %8.6f |%n",i,x,fx,"2",fx*2);
                        System.out.println("--------------------------------------------------");
                    } else {
                        double x = a + ((i-1) * h);
                        double fx=100 + ((8*Math.sqrt(x))*1.0955);
                        Vcalc+=fx*3;
                        System.out.printf("|  %02d  | %05.2f | %8.6f | %3s    | %8.6f |%n",i,x,fx,"3",fx*3);
                        System.out.println("--------------------------------------------------");
                    }
                }
                Vcalc *= ((3 * h) / 8);
                double error = Math.abs(Vreal - Vcalc);
                System.out.println("No. de divisiones: "+n);
                System.out.printf("Valor real de la integral: %.6f %s%n",Vreal,unidad);
                System.out.printf("Valor por el metodo: %.6f %s%n",Vcalc,unidad);
                System.out.printf("Error del metodo: %.6f %s%n",error,unidad);
            }
            
        }

        
        
    }

}
