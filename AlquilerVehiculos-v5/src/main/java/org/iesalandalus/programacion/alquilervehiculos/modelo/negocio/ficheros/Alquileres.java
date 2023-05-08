package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.*;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
public class Alquileres implements IAlquileres {

	
	//DECLARACION
	private static final String RUTA_FICHERO="datos"+File.separator+"alquileres.xml";
	private static final DateTimeFormatter FORMATO_FECHA= DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String RAIZ="Alquileres";
	private static final String ALQUILER="Alquiler";
	private static final String DNI_CLIENTE="Dni";
	private static final String MATRICULA_VEHICULO="Matricula";
	private static final String FECHA_ALQUILER="FechaAlquiler";
	private static final String FECHA_DEVOLUCION="FechaDevolucion";
	private static final String FORMATO="dd/MM/yyyy";
	private static final String TIPO_DATO="TipoDato";
	
	private static Alquileres alquileres;
	private List<Alquiler> coleccionAlquileres;
	
	
	//CONSTRUCTORES
	
	private Alquileres() {
		coleccionAlquileres= new ArrayList<>();
	}
	
	static Alquileres getInstance() {
		if(alquileres==null) {
			return alquileres=new Alquileres();
		}
		return alquileres;
	}
	
	public void comenzar() {
		leerXml();
		
	}
	
	private void leerXml() {
		Document doc = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		if(doc != null) {
			NodeList alquileres = doc.getElementsByTagName(ALQUILER);
			for (int i = 0; i < alquileres.getLength(); i++) {
				Node alquiler = alquileres.item(i);
				if(alquiler.getNodeType() == Node.ELEMENT_NODE) {
					Alquiler c = elementToAlquiler((Element) alquiler);
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
	
	private Alquiler elementToAlquiler(Element elemento) {
		String dni = elemento.getAttribute(DNI_CLIENTE);
		String matricula = elemento.getAttribute(MATRICULA_VEHICULO);
		String fechaAlquiler = elemento.getElementsByTagName(FECHA_ALQUILER).item(0).getTextContent();
		String fechaDevolucion = elemento.getElementsByTagName(FECHA_DEVOLUCION).item(0).getTextContent();
		Cliente c = Clientes.getInstance().buscar(Cliente.getClienteConDni(dni));
		Vehiculo v = Vehiculos.getInstance().buscar(Vehiculo.getVehiculoConMatricula(matricula));
		LocalDate falquiler = LocalDate.parse(fechaAlquiler, FORMATO_FECHA);
		Alquiler a = new Alquiler(c, v, falquiler);
		if(!fechaDevolucion.isEmpty()) {
			LocalDate fdevolucion = LocalDate.parse(fechaDevolucion, FORMATO_FECHA);
			try {
				a.devolver(fdevolucion);
			} catch (OperationNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return a;
		
	}
	
	public void terminar() {
		escribirXml();
	}
	
	private void escribirXml() {
		Document doc = UtilidadesXml.crearDomVacio(RAIZ);
		for (Alquiler alquiler : coleccionAlquileres) {
			Element ec = alquilerToElement(doc, alquiler);
			doc.getDocumentElement().appendChild(ec);
		}
		UtilidadesXml.domToXml(doc, RUTA_FICHERO);
	}
	
	private Element alquilerToElement(Document dom,Alquiler alquiler){
		Element eAlquiler = dom.createElement(ALQUILER);
		eAlquiler.setAttribute(DNI_CLIENTE, alquiler.getCliente().getDni());
		eAlquiler.setAttribute(MATRICULA_VEHICULO, alquiler.getVehiculo().getMatricula());
		
		Element eFAlquiler = dom.createElement(FECHA_ALQUILER);
		eFAlquiler.setAttribute("Formato", FORMATO);
		eFAlquiler.setAttribute(TIPO_DATO, "LocalDate");
		Text textFAlquiler = dom.createTextNode(alquiler.getFechaAlquiler().format(FORMATO_FECHA));
		eFAlquiler.appendChild(textFAlquiler);
		eAlquiler.appendChild(eFAlquiler);
		
		Element eFDevolucion = dom.createElement(FECHA_DEVOLUCION);
		eFDevolucion.setAttribute("Formato", FORMATO);
		eFDevolucion.setAttribute(TIPO_DATO, "LocalDate");
		if(alquiler.getFechaDevolucion() != null) {
			Text textFDevolucion = dom.createTextNode(alquiler.getFechaDevolucion().format(FORMATO_FECHA));
			eFDevolucion.appendChild(textFDevolucion);
		}
		eAlquiler.appendChild(eFDevolucion);
	
		return eAlquiler;
	}
	
	//METODOS DE CLASE
	
	@Override
	public List<Alquiler> get(){
		List<Alquiler> copia=new ArrayList<>();
		for(Alquiler i:coleccionAlquileres) {
			copia.add(i);
		}
		return copia;
	}
	
	@Override
	public List<Alquiler> get(Cliente cliente){
		if(cliente==null) {
			throw new NullPointerException("ERROR: El cliente del cual deseas obtener una lista no puede ser nulo.");
		}
		List<Alquiler> alquilerPorCliente=new ArrayList<>();
		for(Alquiler i:coleccionAlquileres) {
			if(i.getCliente().equals(cliente)) {
				alquilerPorCliente.add(i);
			}
		}
		return alquilerPorCliente;
	}
	
	@Override
	public List<Alquiler> get(Vehiculo vehiculo){
		if(vehiculo==null) {
			throw new NullPointerException("ERROR:El vehiculo del cual desea obtener un lista no puede ser nulo.");
		}
		List<Alquiler> alquilerPorVehiculo=new ArrayList<>();
		for(Alquiler i:coleccionAlquileres) {
			if(i.getVehiculo().getMatricula().equals(vehiculo.getMatricula())) {
				alquilerPorVehiculo.add(i);
			}
		}
		return alquilerPorVehiculo;
	}
	
	@Override
	public int getCantidad() {
		return coleccionAlquileres.size();

	}
	
	@Override
	public void comprobarAlquiler(Cliente cliente,Vehiculo vehiculo,LocalDate fechaAlquiler)throws OperationNotSupportedException {
		if(cliente==null) {
			throw new NullPointerException("ERROR: No puede comprobar el alquiler con un cliente nulo.");
		}
		if(vehiculo==null) {
			throw new NullPointerException("ERROR: No puede comprobar el alquiler con un turismo nulo.");
		}
		if(fechaAlquiler==null) {
			throw new NullPointerException("ERROR: No puede comprobar el alquiler con una fecha de alquiler nula.");
		}
		
		for(Alquiler i:coleccionAlquileres) {
			if(i.getCliente().equals(cliente)) {
				if(i.getFechaDevolucion()==null) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if(i.getFechaDevolucion().isAfter(fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				if(i.getFechaDevolucion().isEqual(fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
			}
			if(i.getVehiculo().equals(vehiculo)) {
				if(i.getFechaDevolucion()==null) {
					throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
				}
				if(i.getFechaDevolucion().isEqual(fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
				}
				if(i.getFechaDevolucion().isAfter(fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
			}


		}
	}
	
	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if(alquiler==null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(),alquiler.getVehiculo(),alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);
	}
	
	//DEVOLVER
	

	
	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		if(vehiculo==null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		for(Alquiler a:get(vehiculo)) {
			if(a.getFechaDevolucion()==null) {
				return a;
			}
		}
		return null;
	}
	
	private Alquiler getAlquilerAbierto(Cliente cliente) {
		if(cliente==null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		for(Alquiler a:get(cliente)) {
			if(a.getFechaDevolucion()==null) {
				return a;
			}
		}
		return null;
	}
	@Override
	public void devolver(Vehiculo vehiculo,LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if(fechaDevolucion==null) {
			throw new OperationNotSupportedException("ERROR:No puedes confirmar una devolución si la fecha es nula");
		}
		Alquiler a = getAlquilerAbierto(vehiculo);
		if(a==null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		a.devolver(fechaDevolucion);
	}
	
	public void devolver(Cliente cliente,LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if(fechaDevolucion==null) {
			throw new OperationNotSupportedException("ERROR:No puedes confirmar una devolución si la fecha es nula");
		}
		Alquiler a = getAlquilerAbierto(cliente);
		if(a==null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		a.devolver(fechaDevolucion);
	}
	
	@Override
	public Alquiler buscar(Alquiler alquiler) {
		if(alquiler==null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		for(Alquiler i:coleccionAlquileres) {
			if(i.equals(alquiler)) {
				return alquiler;
			}
		}
		return null;
	}
	
	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if(alquiler==null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if(!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
			coleccionAlquileres.remove(alquiler);
		
	}

	@Override
	public void guardar() {
		escribirXml();
	}
}
