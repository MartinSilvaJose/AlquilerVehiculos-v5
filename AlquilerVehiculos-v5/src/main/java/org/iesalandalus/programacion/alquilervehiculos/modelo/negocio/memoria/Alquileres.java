package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.*;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
public class Alquileres implements IAlquileres {

	
	//DECLARACION
	
	private List<Alquiler> coleccionAlquileres;
	
	
	//CONSTRUCTORES
	
	public Alquileres() {
		coleccionAlquileres= new ArrayList<>();
	}
	
	
	//METODOS DE CLASE
	
	public void comenzar() {
	}
	public void terminar() {
	}
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
			if(i.getCliente().equals(cliente));
				alquilerPorCliente.add(i);
		}
		return alquilerPorCliente;
	}
	
	@Override
	public List<Alquiler> get(Vehiculo vehiculo){
		if(vehiculo==null) {
			throw new NullPointerException("ERROR:El turismo del cual desea obtener un lista no puede ser nulo.");
		}
		List<Alquiler> alquilerPorVehiculo=new ArrayList<>();
		for(Alquiler i:coleccionAlquileres) {
			if(i.getVehiculo().equals(vehiculo)) {
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
		Alquiler a =getAlquilerAbierto(vehiculo);
		if(a==null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		a.devolver(fechaDevolucion);
	}
	
	public void devolver(Cliente cliente,LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if(fechaDevolucion==null) {
			throw new OperationNotSupportedException("ERROR:No puedes confirmar una devolución si la fecha es nula");
		}
		Alquiler a=getAlquilerAbierto(cliente);
		
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
		// TODO Auto-generated method stub
		
	}
}
