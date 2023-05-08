
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.DOMImplementation;
//import org.w3c.dom.Document;
//
//public class UtilidadesXml {
//
//	private UtilidadesXml() {
//		
//	}
//	public static Document xmlToDom(String rutaXml) {
//		
//		
//	}
//	public static void domToXml(Document dom, String rutaXml) {
//
//	}
//	public static Document crearDomVacio(String raiz) {
//		Document document=null;
//		DocumentBuilder builder;
//		DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
//		
//		try {
//			builder=factory.newDocumentBuilder();
//			DOMImplementation implementation=builder.getDOMImplementation();
//			document=implementation.createDocument(null, raiz, null);
//			document.setXmlVersion("1.0");
//		} catch (ParserConfigurationException e) {
//			System.out.println("El procesador o parser XML ha fallado.");
//		}
//		return document;
//	}
//}
package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades;


import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 * Utilidades para pasar �rboles DOM a documentos XML y viceversa.
 * @author Salvador Romero Villegas
 */
public class UtilidadesXml {
    
    /**
     * Al ser una clase de utilidades que solo contiene m�todos est�ticos no
     * se deber�an poder instanciar objetos de la misma ya que no tiene sentido
     * y no es una buena pr�ctica de programaci�n. Por ello se a�ade 
     * el constructor por defecto privado.
     */
    private UtilidadesXml() {       
    }

    /**
     * Carga un archivo con un documento XML a un �rbol DOM.      
     * @param CaminoAlArchivoXml puede ser un archivo local de tu disco duro
     * o una URI de Internet (http://...).
     * @return el documento DOM o null si no se ha podido cargar el documento.
     */    
    public static Document xmlToDom (String CaminoAlArchivoXml) {
        Document doc=null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc=db.parse(new File(CaminoAlArchivoXml));            
           
        } catch (Exception ex) {
            Logger.getLogger(UtilidadesXml.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return doc;
    }

    /**
     * Convierte un �rbol DOM a XML y lo guarda en un archivo.
     * @param doc Documento a convertir en XML.
     * @param CaminoAlArchivoXML Camino o path para llegar al archivo en el
     * disco.
     * @return true si se ha podido convertir y false en cualquier otra
     *   situaci�n.
     */
    public static void domToXml (Document doc, String CaminoAlArchivoXML) {
        try {
            TransformerFactory tFactory=TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
           
            File f=new File(CaminoAlArchivoXML);
            StreamResult result = new StreamResult(f);  
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);         
        } catch (TransformerException ex) {
            Logger.getLogger(UtilidadesXml.class.getName()).log(Level.SEVERE, null, ex);            
        }
    }
    
    /**
     * Crea un �rbol DOM vac�o.
     * @param etiquetaRaiz Nombre de la etiqueta ra�z del �rbol DOM, donce
     * estará contenida el resto del documento.
     * @return Retornar� el documento creado o null si se ha producido alg�n 
     * error.
     */    
    public static Document crearDomVacio(String etiquetaRaiz) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document d = null ;
        try {
            db = dbf.newDocumentBuilder() ;
            d = db.newDocument() ;
            d.appendChild(d.createElement(etiquetaRaiz));
            return d;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(UtilidadesXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d ;
    }
    
}
