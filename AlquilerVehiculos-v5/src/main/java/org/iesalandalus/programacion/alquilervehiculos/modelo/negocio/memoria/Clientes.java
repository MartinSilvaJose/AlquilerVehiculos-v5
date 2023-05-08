package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.*;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;

public class Clientes implements IClientes {
	
	
	//DECLARACION
	
	private List<Cliente> coleccionClientes;
	
	
	//CONSTRUCTORES
	
	public Clientes() {
		coleccionClientes= new ArrayList<>();
	}
	
	
	//METODOS DE CLASE
	public void comenzar() {

	}
	public void terminar() {

	}
	
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
			if(cliente.equals(i)) {
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
		// TODO Auto-generated method stub
		
	}
	
}
