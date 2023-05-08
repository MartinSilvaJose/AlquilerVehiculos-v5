package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.*;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class Vehiculos implements IVehiculos {
	
	
	//DECLARACION
	
	private List<Vehiculo> coleccionVehiculos;
	
	
	//CONSTRUCTORES
	
	public Vehiculos() {
		coleccionVehiculos=new ArrayList<>();
	}
	
	
	//METODOS DE CLASE
	public void comenzar() {
		
	}
	public void terminar() {
		
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
			if(vehiculo.equals(i)) {
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
		// TODO Auto-generated method stub
		
	}
}
