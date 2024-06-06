package unidad4.proyecto1.validaciones;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLBIENFORMADO {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("error, debe de proporcionar el nombre del archivo");
			return;
		}
		String arch = args[0];
		System.out.println("*****VALIDADNDO SI ESTA BIEN FORMADOS EL ARCHIVO " + arch);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			dBuilder.setErrorHandler(new ParseErrorHandler());
			Document doc = dBuilder.parse(arch);

			System.out.println("********fin del analisis todo bien************");
		} catch (SAXException e) {
			System.out.println(e.getMessage());
			return;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return;
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
}
