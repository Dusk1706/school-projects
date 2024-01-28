package Pruebas;
import java.util.Scanner;

public class GausJordan_Seidel{
    public static void main(String[] args) {
        
        Scanner entrada=new Scanner(System.in);
        String pregunta;
        int a,b,opcion;
        double Vreal;
        System.out.printf("--------------------------------------------------------------------------------------%n");
        System.out.println("Instituto Tecnologico de Culiacan");
        System.out.println("Ing. En Sistemas Computacionales");
        System.out.println("Dylan Michel Garcia Figueroa");
        System.out.println("De 9:00 a 10:00 horas");

        do {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Seleccione el metodo a utilizar");
            System.out.println("1-Metodo de Gauss Jordan");
            System.out.println("2-Metodo de Gauss Seidel");
            System.out.println("3-Salir");
            opcion=entrada.nextInt();
            switch(opcion){
                case 1:
                System.out.println("Ingresa la pregunta del problema");
                entrada.nextLine();
                pregunta=entrada.nextLine();
                System.out.println("Ingresa algo");
                int algo=entrada.nextInt();
                System.out.println(pregunta);
                System.out.println(algo);
                break;
                case 2:
                System.out.println("Ingresa la pregunta del problema");

                break;

                case 3:
                System.out.println("Programa Finalizado");
                break;
                default: 
                System.out.println("Ingresa una opcion valida");
                break;
            }

        }while(opcion!=3);

    }
}