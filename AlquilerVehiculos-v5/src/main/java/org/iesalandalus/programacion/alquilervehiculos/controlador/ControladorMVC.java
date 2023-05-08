package org.iesalandalus.programacion.alquilervehiculos.controlador;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class ControladorMVC implements IControlador {
	private Vista vista;
	private Modelo modelo;
	
	public ControladorMVC(Modelo modelo,Vista vista) {
		if(modelo==null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo");
		}
		if(vista==null) {
			throw new NullPointerException("ERROR: La vista no puede ser nula");
		}
		this.modelo=modelo;
		this.vista=vista;
		vista.setControlador(this);
	}
	
	
	//	COMENZAR, TERMINAR y GUARDAR 
	@Override
	public void comenzar() {
		modelo.comenzar();
		vista.comenzar();
	}
	@Override
	public void terminar() {
		modelo.terminar();
	}
	@Override
	public void guardar() {
		modelo.guardar();
		
	}
	
	//	INSERTAR
	@Override
	public void insertar(Cliente cliente)throws OperationNotSupportedException {
		modelo.insertar(cliente);
	}
	@Override
	public void insertar(Vehiculo vehiculo)throws OperationNotSupportedException {
		modelo.insertar(vehiculo);
	}
	@Override
	public void insertar(Alquiler alquiler)throws OperationNotSupportedException {
		modelo.insertar(alquiler);
	}
	
	
	//	BUSCAR
	@Override
	public Cliente buscar(Cliente cliente) {
		return modelo.buscar(cliente);
		
	}
	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		return modelo.buscar(vehiculo);
	}
	@Override
	public Alquiler buscar(Alquiler alquiler) {
		return modelo.buscar(alquiler);
	}
	
	
	// MODIFICAR Y DEVOLVER
	@Override
	public void modificar(Cliente cliente, String nombre, String telefono)throws OperationNotSupportedException {
		modelo.modificar(cliente, nombre, telefono);
	}
	@Override
	public void devolver(Cliente cliente,LocalDate fechaDevolucion)throws OperationNotSupportedException {
		modelo.devolver(cliente, fechaDevolucion);
	}
	@Override
	public void devolver(Vehiculo vehiculo,LocalDate fechaDevolucion)throws OperationNotSupportedException {
		modelo.devolver(vehiculo, fechaDevolucion);
	}
	
	//BORRAR
	@Override
	public void borrar(Cliente cliente)throws OperationNotSupportedException {
		modelo.borrar(cliente);
	}
	@Override
	public void borrar(Vehiculo vehiculo)throws OperationNotSupportedException {
		modelo.borrar(vehiculo);
	}
	@Override
	public void borrar(Alquiler alquiler)throws OperationNotSupportedException {
		modelo.borrar(alquiler);
	}
	
	
	//	LISTAR
	@Override
	public List<Cliente> getClientes(){
		return modelo.getClientes();
	}
	@Override
	public List<Vehiculo> getVehiculo(){
		return modelo.getVehiculos();
	}
	@Override
	public List<Alquiler> getAlquileres(){
		return modelo.getAlquileres();
	}
	
	
	//	LISTAR CON PARAMETROS
	@Override
	public List<Alquiler> getAlquileres(Cliente cliente){
		return modelo.getAlquileres(cliente);
	}
	@Override
	public List<Alquiler> getAlquileres(Vehiculo vehiculo){
		return modelo.getAlquileres(vehiculo);
	}



	
}
