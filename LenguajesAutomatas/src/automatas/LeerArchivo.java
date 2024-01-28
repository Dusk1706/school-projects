package automatas;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeerArchivo{
    public static String leerArchivo(File archivo) throws FileNotFoundException {
        Scanner sc = new Scanner(archivo);
        StringBuilder lectura = new StringBuilder();
        while (sc.hasNextLine()) {
            lectura.append(sc.nextLine());
            lectura.append("\n");
        }
        return lectura.toString();
    }
}