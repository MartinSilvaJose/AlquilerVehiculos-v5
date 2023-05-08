package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.*;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Clientes implements IClientes {
	
	
	//DECLARACION
	private static final String RUTA_FICHERO="datos"+File.separator+"clientes.xml";
	private static final String RAIZ="Clientes";
	private static final String CLIENTE="Cliente";
	private static final String NOMBRE="Nombre";
	private static final String DNI="Dni";
	private static final String TELEFONO="Telefono";
	private static final String TIPO_DATO="TipoDato";
	
	private List<Cliente> coleccionClientes;
	private static Clientes instancia;
	
	//CONSTRUCTORES
	
	private Clientes() {
		coleccionClientes= new ArrayList<>();
	}
	static Clientes getInstance() {
		 
		 if(instancia==null) {
			 return instancia=new Clientes();
			 
		 }
		 return instancia;
		
	 }
	
	//METODOS PARA EL XML
	
	public void comenzar() {
		leerXml();
		
	}
	
	private void leerXml() {
		Document doc = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		if(doc != null) {
			NodeList clientes = doc.getElementsByTagName(CLIENTE);
			for (int i = 0; i < clientes.getLength(); i++) {
				Node cliente = clientes.item(i);
				if(cliente.getNodeType() == Node.ELEMENT_NODE) {
					Cliente c = elementToCliente((Element) cliente);
					try {
						insertar(c);
					} catch (OperationNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		
	}
	
	private Cliente elementToCliente(Element elemento) {

		String nombre = elemento.getElementsByTagName(NOMBRE).item(0).getTextContent();
		String dni = elemento.getAttribute(DNI);
		String telefono = elemento.getElementsByTagName(TELEFONO).item(0).getTextContent();
		
		Cliente cliente = new Cliente(nombre, dni, telefono);
		return cliente;
		
	}
	
	public void terminar() {
		escribirXml();
	}
	
	private void escribirXml() {
		Document doc = UtilidadesXml.crearDomVacio(RAIZ);
		for (Cliente cliente : coleccionClientes) {
			Element ec = clienteToElement(doc, cliente);
			doc.getDocumentElement().appendChild(ec);
		}
		UtilidadesXml.domToXml(doc, RUTA_FICHERO);
		
	}
	
	private Element clienteToElement(Document dom,Cliente cliente){
		Element eCliente = dom.createElement(CLIENTE);
		
		eCliente.setAttribute(DNI, cliente.getDni());
		
		Element eNombre = dom.createElement(NOMBRE);
		Text textNombre = dom.createTextNode(cliente.getNombre());
		eNombre.appendChild(textNombre);
		eCliente.appendChild(eNombre);
		eNombre.setAttribute(TIPO_DATO, "String");
		
		Element eTelefono = dom.createElement(TELEFONO);
		Text textTelefono = dom.createTextNode(cliente.getTelefono());
		eTelefono.appendChild(textTelefono);
		eCliente.appendChild(eTelefono);
		eTelefono.setAttribute(TIPO_DATO, "String");
		
		return eCliente;
	}
	
	
	//METODOS DE CLASE
	
	@Override
	public List<Cliente> get() {
		List <Cliente> copia=new ArrayList<>();
		for(Cliente i:coleccionClientes) {
			copia.add(i);
		}
		return copia;
	}
	
	@Override
	public int getCantidad() {
		return coleccionClientes.size();
 
	}
	
	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if(cliente==null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		if(coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
		coleccionClientes.add(cliente);
	}
	
	@Override
	public Cliente buscar(Cliente cliente) {
		if(cliente==null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		for(Cliente i:coleccionClientes) {
			if(cliente.getDni().equals(i.getDni())) {
				return i;
			}
		}
		return null;
	}
	
	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if(cliente==null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		if(!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
		coleccionClientes.remove(cliente);
	}
	
	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if(cliente==null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}

		if(!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
		if(nombre==null || telefono==null) {
			if(nombre==null && telefono!=null) {
				buscar(cliente).setTelefono(telefono);
			}
			if(telefono==null && nombre!=null) {
				buscar(cliente).setNombre(nombre);
			}
		}else {
			buscar(cliente).setTelefono(telefono);
			buscar(cliente).setNombre(nombre);
		}



		
	}
	@Override
	public void guardar() {
		escribirXml();
	}
	
}
