package org.iesalandalus.programacion.alquilervehiculos.vista;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {
	TURISMO("Turismo"),
	AUTOBUS("Autobus"),
	FURGONETA("Furgoneta");
	
	private String nombre="";
	
	private TipoVehiculo(String nombre) {
		this.nombre=nombre;
	}
	
	private static boolean esOrdinalValido(int ordinal) {
		if(ordinal<values().length || ordinal>=0) {
			return true;
		}
		return false;
	}
	
	public static TipoVehiculo get(int ordinal) {
		if(esOrdinalValido(ordinal)==false) {
			throw new IllegalArgumentException("ERROR: El ordinal introducido no es válido.");
		}
		return values()[ordinal];
	}
	
	public static TipoVehiculo get(Vehiculo vehiculo) {
		if(vehiculo==null) {
			throw new NullPointerException("ERROR: El vehículo es nulo.");
		}
		if(vehiculo instanceof Turismo) {
			return TURISMO;
		}
		if(vehiculo instanceof Autobus) {
			return AUTOBUS;
		}
		if(vehiculo instanceof Furgoneta) {
			return FURGONETA;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(),nombre);
	}
	
}
