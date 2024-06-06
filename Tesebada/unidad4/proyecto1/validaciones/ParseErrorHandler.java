package unidad4.proyecto1.validaciones;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ParseErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        throw new SAXException("Advertencia: " + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        throw new SAXException("Error: " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        throw new SAXException("Error fatal: " + exception.getMessage());
    }

}