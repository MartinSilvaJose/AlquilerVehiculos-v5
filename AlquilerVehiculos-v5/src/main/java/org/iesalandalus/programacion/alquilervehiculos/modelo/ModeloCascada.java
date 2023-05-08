package org.iesalandalus.programacion.alquilervehiculos.modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.*;
public class ModeloCascada extends Modelo{
	

	public ModeloCascada(IFuenteDatos fuenteDatos) {
		setFuenteDatos(fuenteDatos);
	}
	
	//INSERTAR
	
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if(cliente==null) {
			throw new NullPointerException("ERROR: no puedes insertar un cliente nulo.");
		}

		clientes.insertar(new Cliente(cliente));
	}
	
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if(vehiculo==null) {
			throw new NullPointerException("ERROR: no puedes insertar un vehiculo nulo.");
		}
	
		vehiculos.insertar(Vehiculo.copiar(vehiculo));
	}
	
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if(alquiler==null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		
		alquileres.insertar(new Alquiler(alquiler));
	}
	
	
	//BUSCAR
	
	public Cliente buscar(Cliente cliente) {
		if(cliente==null) {
			throw new NullPointerException("ERROR: no puedes buscar un cliente nulo.");
		}
		
		return new Cliente(clientes.buscar(cliente));
	}
	
	public Vehiculo buscar(Vehiculo vehiculo) {
		if(vehiculo==null) {
			throw new NullPointerException("ERROR: no puedes buscar un vehiculo nulo.");
		}
		return Vehiculo.copiar(vehiculos.buscar(vehiculo));
	}
	
	public Alquiler buscar(Alquiler alquiler) {
		if(alquiler==null) {
			throw new NullPointerException("ERROR: no puedes buscar un alquiler nulo.");
		}
		return new Alquiler(alquileres.buscar(alquiler));

	}
	
	
	//MODIFICAR & DEVOLVER
	
	public void  modificar(Cliente cliente,String nombre,String telefono) throws OperationNotSupportedException {
		clientes.modificar(cliente, nombre, telefono);
	}
	
	public void devolver(Vehiculo vehiculo,LocalDate fechaDevolucion) throws OperationNotSupportedException {
		alquileres.devolver(vehiculo, fechaDevolucion);
	}
	public void devolver(Cliente cliente,LocalDate fechaDevolucion) throws OperationNotSupportedException {
		alquileres.devolver(cliente, fechaDevolucion);
	}
	
	
	//BORRAR
	
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		List<Alquiler> alquilerPorCliente=alquileres.get(cliente);
		for(Alquiler i:alquilerPorCliente) {
			alquileres.borrar(i);
		}
		clientes.borrar(cliente);
	}
	
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		List<Alquiler> alquilerPorVehiculo=alquileres.get(vehiculo);
		for(Alquiler i:alquilerPorVehiculo) {
			alquileres.borrar(i);
		}
		vehiculos.borrar(vehiculo);
	}
	
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		alquileres.borrar(alquiler);
	}
	
	
	//GET
	
	public List<Cliente> getClientes(){
		List<Cliente> copiaClientes = new ArrayList<>(clientes.get());
		return copiaClientes;
		
	}
	
	public List<Vehiculo> getVehiculos(){
		List<Vehiculo> copiaVehiculo=new ArrayList<>(vehiculos.get());
		return copiaVehiculo;
	}
	
	public List<Alquiler> getAlquileres(){
		List<Alquiler> copiaAlquileres=new ArrayList<>(alquileres.get());
		return copiaAlquileres;
	}
	
	//GET CON PARAMETROS
	public List<Alquiler> getAlquileres(Cliente cliente){
		List<Alquiler> copiaAlquileresPorCliente=new ArrayList<>(alquileres.get(cliente));
		return copiaAlquileresPorCliente;
	}
	
	public List<Alquiler> getAlquileres(Vehiculo vehiculo){
		List<Alquiler> copiaAlquileresPorVehiculo=new ArrayList<>(alquileres.get(vehiculo));
		return copiaAlquileresPorVehiculo;
	}
}
