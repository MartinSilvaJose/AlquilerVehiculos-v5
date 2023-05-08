package org.iesalandalus.programacion.alquilervehiculos.controlador;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public interface IControlador {

	//	COMENZAR Y TERMINAR 
	void comenzar();

	void terminar();

	//	INSERTAR
	void insertar(Cliente cliente) throws OperationNotSupportedException;

	void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;

	void insertar(Alquiler alquiler) throws OperationNotSupportedException;

	//	BUSCAR
	Cliente buscar(Cliente cliente);

	Vehiculo buscar(Vehiculo vehiculo);

	Alquiler buscar(Alquiler alquiler);

	// MODIFICAR Y DEVOLVER
	void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException;

	void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	//BORRAR
	void borrar(Cliente cliente) throws OperationNotSupportedException;

	void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;

	void borrar(Alquiler alquiler) throws OperationNotSupportedException;

	//	LISTAR
	List<Cliente> getClientes();

	List<Vehiculo> getVehiculo();

	List<Alquiler> getAlquileres();

	//	LISTAR CON PARAMETROS
	List<Alquiler> getAlquileres(Cliente cliente);

	List<Alquiler> getAlquileres(Vehiculo vehiculo);

	void guardar();

}