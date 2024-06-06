package unidad4.proyecto1.validaciones;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DOM {
	static String arch;

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("error, debe de proporcionar el nombre del archivo");
			return;
		}
		arch = args[0];
		System.out.println("*****PROCESANDO EL DOM Del archivo " + arch);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setValidating(true);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			dBuilder.setErrorHandler(new ParseErrorHandler());
			Document doc = dBuilder.parse(arch);
			System.out.println("********fin del analisis todo bien************");
			ProcesaDOM(doc);
		} catch (Exception e) {
			System.out.println("____________________________________________");
			System.out.println(e);
		}
	}

	public static void ProcesaDOM(Node nodo) {
		switch (nodo.getNodeType()) {
			case Node.DOCUMENT_NODE:
				System.out.println("<?xml version=\"1.0\"?>");
				System.out.println("<!DOCTYPE empresa SYSTEM \"empresa.dtd\">");
				Document doc = (Document) nodo;
				ProcesaDOM(doc.getDocumentElement());
				break;
			case Node.ELEMENT_NODE:
				String nombre = nodo.getNodeName();
				System.out.print("<" + nombre);
				if (nodo.hasAttributes()) {
					NamedNodeMap atributos = nodo.getAttributes();
					for (int i = 0; i < atributos.getLength(); i++) {
						Node atributo = atributos.item(i);
						System.out.print(" " + atributo.getNodeName() + "=\"" + atributo.getNodeValue() + "\"");
					}
				}
				System.out.println(">");

				NodeList hijos = nodo.getChildNodes();
				for (int i = 0; i < hijos.getLength(); i++) {
					ProcesaDOM(hijos.item(i));
				}

				System.out.println("</" + nombre + ">");
				break;
			case Node.TEXT_NODE:
				String contenido = nodo.getTextContent().trim();
				if (!contenido.isEmpty()) {
					System.out.println(contenido);
				}
				break;
		}
	}

}
