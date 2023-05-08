package org.iesalandalus.programacion.alquilervehiculos.modelo;
import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.*;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.FuenteDatosMemoria;
public abstract class Modelo {
	
	//DECLARACION
	
	protected IClientes clientes;
	protected IAlquileres alquileres;
	protected IVehiculos vehiculos;
	protected IFuenteDatos fuenteDatos;
	
	
	//COMENZAR & TERMINAR & SETTER FUENTE DE DATOS
	
	public void comenzar() {
		clientes.comenzar();
		vehiculos.comenzar();
		alquileres.comenzar();
	}
	
	public void terminar() {
		clientes.terminar();
		vehiculos.terminar();
		alquileres.terminar();
		System.out.println("El modelo ha terminado");
	}
	public void guardar() {
		clientes.guardar();
		vehiculos.guardar();
		alquileres.guardar();
	}
	
	protected void setFuenteDatos(IFuenteDatos fuenteDatos) {
		if(fuenteDatos instanceof FuenteDatosMemoria) {
			clientes=FactoriaFuenteDatos.MEMORIA.crear().crearClientes();
			alquileres=FactoriaFuenteDatos.MEMORIA.crear().crearAlquileres();
			vehiculos=FactoriaFuenteDatos.MEMORIA.crear().crearVehiculos();
		}
		if(fuenteDatos instanceof FuenteDatosFicheros) {
			clientes=FactoriaFuenteDatos.FICHEROS.crear().crearClientes();
			alquileres=FactoriaFuenteDatos.FICHEROS.crear().crearAlquileres();
			vehiculos=FactoriaFuenteDatos.FICHEROS.crear().crearVehiculos();
		}
	}
	//INSERTAR
	
	public abstract void insertar(Cliente cliente) throws OperationNotSupportedException;
	
	public abstract void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;
	
	public abstract void insertar(Alquiler alquiler) throws OperationNotSupportedException;
	
	
	//BUSCAR
	
	public abstract Cliente buscar(Cliente cliente);
	
	public abstract Vehiculo buscar(Vehiculo vehiculo);
	
	public abstract Alquiler buscar(Alquiler alquiler);
	
	
	//MODIFICAR & DEVOLVER
	
	public abstract void  modificar(Cliente cliente,String nombre,String telefono) throws OperationNotSupportedException;
	
	public abstract void devolver(Cliente cliente,LocalDate fechaDevolucion) throws OperationNotSupportedException;
	
	public abstract void devolver(Vehiculo vehiculo,LocalDate fechaDevolucion) throws OperationNotSupportedException;
	//BORRAR
	
	public abstract void borrar(Cliente cliente) throws OperationNotSupportedException;
	
	public abstract void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;
	
	public abstract void borrar(Alquiler alquiler) throws OperationNotSupportedException;
	
	
	//GET
	
	public abstract List<Cliente> getClientes();
	
	public abstract List<Vehiculo> getVehiculos();
	
	public abstract List<Alquiler> getAlquileres();
	
	//GET CON PARAMETROS
	public abstract List<Alquiler> getAlquileres(Cliente cliente);
	
	public abstract List<Alquiler> getAlquileres(Vehiculo vehiculo);
}
