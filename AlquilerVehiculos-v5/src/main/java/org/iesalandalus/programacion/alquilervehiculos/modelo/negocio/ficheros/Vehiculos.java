package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.*;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.iesalandalus.programacion.alquilervehiculos.vista.TipoVehiculo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Vehiculos implements IVehiculos {
	
	
	//DECLARACION
	private static final String RUTA_FICHERO="datos"+File.separator+"vehiculos.xml";
	private static final String RAIZ="Vehiculos";
	private static final String VEHICULO="Vehiculo";
	private static final String MARCA="Marca";
	private static final String MODELO="Modelo";
	private static final String MATRICULA="Matricula";
	private static final String CILINDRADA="Cilindrada";
	private static final String PLAZAS="Plazas";
	private static final String PMA="Pma";
	private static final String TIPO="Tipo";
	private static final String TURISMO="Turismo";
	private static final String AUTOBUS="Autobus";
	private static final String FURGONETA="Furgoneta";
	private static final String TIPO_DATO="TipoDato";
	
	private static Vehiculos vehiculos;
	private List<Vehiculo> coleccionVehiculos;
	
	
	//CONSTRUCTORES
	
	public Vehiculos() {
		coleccionVehiculos=new ArrayList<>();
	}
	
	static Vehiculos getInstance() {
		if(vehiculos==null) {
			return vehiculos=new Vehiculos();
		}
		return vehiculos;
	}
	
	//METODOS DE CLASE
	
	public void comenzar() {
		leerXml();
	}
	
	private void leerXml() {
		Document doc = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		if(doc != null) {
			NodeList vehiculos = doc.getElementsByTagName(VEHICULO);
			for (int i = 0; i < vehiculos.getLength(); i++) {
				Node vehiculo = vehiculos.item(i);
				if(vehiculo.getNodeType() == Node.ELEMENT_NODE) {
					Vehiculo v = elementToVehiculo((Element) vehiculo);
					try {
						insertar(v);
					} catch (OperationNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		
	}
	
	private Vehiculo elementToVehiculo(Element elemento) {
		String tipo = elemento.getAttribute(TIPO);
		String matricula = elemento.getAttribute(MATRICULA);
		String marca = elemento.getElementsByTagName(MARCA).item(0).getTextContent();
		String modelo = elemento.getElementsByTagName(MODELO).item(0).getTextContent();
		
		if(tipo.equals("Turismo")) {
			String cilindrada = elemento.getElementsByTagName(CILINDRADA).item(0).getTextContent();
			return new Turismo(marca, modelo, Integer.parseInt(cilindrada), matricula);
			
		}else if(tipo.equals("Autobus")) {
			String plazas = elemento.getElementsByTagName(PLAZAS).item(0).getTextContent();
			return new Autobus(marca, modelo, Integer.parseInt(plazas), matricula);
			
		}else if(tipo.equals("Furgoneta")) {
			String plazas = elemento.getElementsByTagName(PLAZAS).item(0).getTextContent();
			String pma = elemento.getElementsByTagName(PMA).item(0).getTextContent();
			return new Furgoneta(marca, modelo, Integer.parseInt(plazas), Integer.parseInt(pma), matricula);
		}
		
		return null;
		
	}
	
	public void terminar() {
		escribirXml();
	}
	
	private void escribirXml() {
		Document doc = UtilidadesXml.crearDomVacio(RAIZ);
		for (Vehiculo vehiculo : coleccionVehiculos) {
			Element ec = vehiculoToElement(doc, vehiculo);
			doc.getDocumentElement().appendChild(ec);
		}
		UtilidadesXml.domToXml(doc, RUTA_FICHERO);
	}
	
	private Element vehiculoToElement(Document dom,Vehiculo vehiculo){
		
		Element eVehiculo = dom.createElement(VEHICULO);
		eVehiculo.setAttribute(MATRICULA, vehiculo.getMatricula());
		
		Element eMarca = dom.createElement(MARCA);
		eMarca.setAttribute(TIPO_DATO, "String");
		Text textMarca = dom.createTextNode(vehiculo.getMarca());
		eMarca.appendChild(textMarca);
		eVehiculo.appendChild(eMarca);
		
		Element eModelo = dom.createElement(MODELO);
		eModelo.setAttribute(TIPO_DATO, "String");
		Text textModelo = dom.createTextNode(vehiculo.getModelo());
		eModelo.appendChild(textModelo);
		eVehiculo.appendChild(eModelo);
		
		
		if(TipoVehiculo.get(vehiculo) == TipoVehiculo.TURISMO) {
			
			eVehiculo.setAttribute(TIPO, TURISMO);
			Element eTurismo = dom.createElement(TURISMO);
			Element eCilindrada = dom.createElement(CILINDRADA);
			eCilindrada.setAttribute(TIPO_DATO, "Integer");
			Text textCilindrada = dom.createTextNode(((Turismo)vehiculo).getCilindrada()+"");
			
			eCilindrada.appendChild(textCilindrada);
			eTurismo.appendChild(eCilindrada);
			eVehiculo.appendChild(eTurismo);
			
		}else if (TipoVehiculo.get(vehiculo) == TipoVehiculo.AUTOBUS) {
			
			eVehiculo.setAttribute(TIPO, AUTOBUS);
			Element eAutobus = dom.createElement(AUTOBUS);
			Element ePlazas = dom.createElement(PLAZAS);
			ePlazas.setAttribute(TIPO_DATO, "Integer");
			Text textPlazas = dom.createTextNode(((Autobus)vehiculo).getPlazas()+"");
			
			ePlazas.appendChild(textPlazas);
			eAutobus.appendChild(ePlazas);
			eVehiculo.appendChild(eAutobus);
			
		}else if (TipoVehiculo.get(vehiculo) == TipoVehiculo.FURGONETA) {
			
			eVehiculo.setAttribute(TIPO, FURGONETA);
			Element eFurgoneta = dom.createElement(FURGONETA);
			Element ePlazas = dom.createElement(PLAZAS);
			ePlazas.setAttribute(TIPO_DATO, "Integer");
			Text textPlazas = dom.createTextNode(((Furgoneta)vehiculo).getPlazas()+"");
			
			ePlazas.appendChild(textPlazas);
			eFurgoneta.appendChild(ePlazas);
			
			Element ePma = dom.createElement(PMA);
			ePma.setAttribute(TIPO_DATO, "Integer");
			Text textPma = dom.createTextNode(((Furgoneta)vehiculo).getPma()+"");
			
			ePma.appendChild(textPma);
			eFurgoneta.appendChild(ePma);
			
			eVehiculo.appendChild(eFurgoneta);
		}
	
		return eVehiculo;
	}
	
	@Override
	public List<Vehiculo> get(){
		List<Vehiculo> copia=new ArrayList<>();
		for(Vehiculo i:coleccionVehiculos) {
			copia.add(i);
		}
		return copia;
	}
	
	@Override
	public int getCantidad() {
		return coleccionVehiculos.size();
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if(vehiculo==null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}
		if(coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}
		coleccionVehiculos.add(vehiculo);
	}
	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		if(vehiculo==null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}
		for(Vehiculo i:coleccionVehiculos) {
			if(vehiculo.getMatricula().equals(i.getMatricula())) {
				return i;
			}

		}
		return null;
	}
	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if(vehiculo==null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}
		if(!coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException( "ERROR: No existe ningún vehículo con esa matrícula.");
		}
		coleccionVehiculos.remove(vehiculo);
	}

	@Override
	public void guardar() {
		escribirXml();
	}
}
