package unidad3;

import java.util.Scanner;

public class MetodoTrapecio {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Instituto Tecnologico de Culiacan");
        System.out.println("Ing. En Sistemas Computacionales");
        System.out.println("Dylan Michel Garcia Figueroa");
        System.out.println("Integracion Numerica. Metodo de los trapecios");
        System.out.println("De 9:00 a 10:00 horas");
        System.out.println("Este proceso realiza el cálculo de una Integral definida"+
        "utilizando el Método de los Trapecios con la siguiente informacion");
        System.out.println("Ingresa la pregunta del problema:");
        String pregunta = sc.nextLine();
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Ingresa el valor real:");
        double Vreal = sc.nextDouble();
        System.out.println("Ingresa la unidad del problema:");
        sc.nextLine();
        String unidad = sc.nextLine();
        System.out.println("Ingresa el punto inicial del intervalo:");
        double a=sc.nextDouble();
        System.out.println("Ingresa el punto final del intervalo:");
        int b = sc.nextInt();
        System.out.println("Ingresa la cantidad de trapecios inicial:");
        int n = sc.nextInt();
        System.out.println("Ingresa el error tolerante del problema:");
        double error = sc.nextDouble();
        System.out.println("Ingresa el total de procesos a realizar:");
        int tp = sc.nextInt();
        int np = 0;
        double ca = a;
        boolean R=true;
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Instituto Tecnologico de Culiacan");
        System.out.println("Ing. En Sistemas Computacionales");
        System.out.println("Dylan Michel Garcia Figueroa");
        System.out.println("Integracion Numerica. Metodo de los trapecios");
        System.out.println("De 9:00 a 10:00 horas");
        System.out.println("Pregunta: "+pregunta);
        System.out.println("------------------------------------------------------------------------------");
        while(R){
            np++;
            double Vcalc = 0;
            double h = (b-a)/n;
            double ah, fa, fah, area;
            System.out.println("No. de proceso: "+np);
            System.out.println("------------------------------------------------------------------------------");
            System.out.printf("| %8s | %5s     | %7s   | %8s    | %10s  | %8s    |\n", "Trapecio", "a", "a + h", "f(a)", "f(a + h)", "Area");
            System.out.println("------------------------------------------------------------------------------");
            for(int x = 1; x<=n; x++){
                ah = a + h;
                fa = (a*a*a) - Math.sin(a+1);
                fah = (ah*ah*ah) - Math.sin(ah+1);
                area = (h/2) * (fa + fah);
                Vcalc += area;
                System.out.printf("|    %02d    | %9.6f | %9.6f | %11.6f | %10.6f | %11.6f |\n", x, a, ah, fa ,fah, area);
                System.out.println("------------------------------------------------------------------------------");
                a = ah;
            }
            double errorP = Math.abs(Vreal - Vcalc);
            System.out.println("Resultado: " + (Vcalc)+"\n");
            if(errorP <= error){
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("Resultados:");
                System.out.println("No. de procesos= "+ np);
                System.out.println("Valor real de la integral: "+Vreal+" "+unidad);
                System.out.println("Valor calculado por trapecios: "+Vcalc+" "+unidad);
                System.out.println("Error del problema: "+error);
                System.out.println("Error del metodo: "+errorP);
                R=false;
            }else if(np==tp) {
                System.out.println("La mejor aproximacion no se ha encontrado en "+tp+" procesos");
                R=false;
            }
            a=ca;
            n*=2;
        }
        
    }
    
}