package org.iesalandalus.programacion.alquilervehiculos.vista.iutextual;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.TipoVehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final String PATRON_FECHA="(0[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/([0-9]{4})";
	private static final DateTimeFormatter FORMATO_FECHA=DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Consola() {
		
	}
	
	public static void mostrarCabecera(String mensaje) {
		System.out.println(mensaje);
		System.out.println("------------------------------");
	}
	
	public static void mostrarMenu() {
		for(Accion opcion:Accion.values()) {
			System.out.println(opcion);
		}
	}
	
	private static String leerCadena(String mensaje) {
		System.out.println(mensaje);
		String cadenaIntroducida=Entrada.cadena();
		return cadenaIntroducida;
	}
	private static int leerEntero(String mensaje) {
		System.out.println(mensaje);
		int enteroIntroducido=Entrada.entero();
		return enteroIntroducido;
	}
	private static LocalDate leerFecha(String mensaje) throws DateTimeParseException{
		System.out.println(mensaje);
		String fechaIntroducida;
		do {
			System.out.println("El formato debe ser dd/MM/yyyy");
			fechaIntroducida=Entrada.cadena();
		}while(!fechaIntroducida.matches(PATRON_FECHA));
		
	    try {
	        return LocalDate.parse(fechaIntroducida, FORMATO_FECHA);

	    } catch (DateTimeParseException e) {
	        throw new IllegalArgumentException("ERROR: La fecha no cumple con el formato deseados --> dd/MM/yyyy");
	    }
	}
	public static Accion elegirOpcion(){
		int opcionElegida=-1;
		do {
			opcionElegida=leerEntero("Introduzca la opción a elegir.");
		}while(opcionElegida>Accion.values().length || opcionElegida<0);
		
		return Accion.get(opcionElegida);
		
	}
	
	public static Cliente leerCliente() {
		String dni=leerCadena("Introduza el dni del cliente.");
		String nombre=leerNombre();
		String telefono=leerTelefono();
		Cliente cliente= new Cliente(nombre,dni,telefono);
		
		return cliente;
	}
	
	public static Vehiculo leerVehiculo(){
		mostrarMenuTiposVehiculos();
		return leerVehiculo(elegirTipoVehiculo());
	}
	public static Cliente leerClienteDni() {
		return Cliente.getClienteConDni(leerCadena("Introduce el dni."));
	}
	
	public static String leerNombre() {
		String nombre=leerCadena("Introduzca el nombre.");
		return nombre;
	}
	public static String leerTelefono() {
		String telefono=leerCadena("Introduzca el telefono.");
		return telefono;
	}

	public static Vehiculo leerVehiculoMatricula() {
		return Vehiculo.getVehiculoConMatricula(leerCadena("Introduce la matricula."));
	}
	
	public static Alquiler leerAlquiler() {
		Cliente cliente=leerClienteDni();
		Vehiculo vehiculo=leerVehiculoMatricula();
		LocalDate fecha=leerFecha("Introduzca la fecha de alquiler.");
		
		if(TipoVehiculo.get(vehiculo).equals(TipoVehiculo.TURISMO)) {
			vehiculo=new Turismo((Turismo)vehiculo);
		}else if(TipoVehiculo.get(vehiculo).equals(TipoVehiculo.AUTOBUS)) {
			vehiculo=new Autobus((Autobus)vehiculo);
		}else if(TipoVehiculo.get(vehiculo).equals(TipoVehiculo.FURGONETA)) {
			vehiculo=new Furgoneta((Furgoneta)vehiculo);
		}
		Alquiler alquiler=new Alquiler(cliente, vehiculo, fecha);
		return alquiler;
	}
	public static LocalDate leerFechaDevolucion() {
		LocalDate fechaDevolucion=leerFecha("Introduzca la fecha de devolución.");
		return fechaDevolucion;
	}
	
	private static void mostrarMenuTiposVehiculos() {
		for(TipoVehiculo tipo:TipoVehiculo.values()) {
			System.out.println(tipo);
		}
	}
	
	private static TipoVehiculo elegirTipoVehiculo() {
		return TipoVehiculo.get(leerEntero("Especifique el tipo de vehículo"));
	}
	
	private static Vehiculo leerVehiculo(TipoVehiculo tipo) {
		if(tipo==null) {
			throw new NullPointerException("El tipo del vehículo no puede ser nulo.");
		}
		Vehiculo vehiculo=null;
		
		if(tipo.equals(TipoVehiculo.TURISMO)) {
			String marca=leerCadena("Introduzca la marca.");
			String modelo=leerCadena("Introduzca el modelo.");
			String matricula=leerCadena("Introduzca la matricula.");
			int cilindrada=leerEntero("Introduzca la cilindrada.");
			vehiculo=new Turismo(marca, modelo, cilindrada, matricula);
		}
		
		if(tipo.equals(TipoVehiculo.AUTOBUS)) {
			String marca=leerCadena("Introduzca la marca.");
			String modelo=leerCadena("Introduzca el modelo.");
			String matricula=leerCadena("Introduzca la matricula.");
			int plazas=leerEntero("Introduce el numero de plazas.");
			vehiculo=new Autobus(marca, modelo, plazas, matricula);
		}
		if(tipo.equals(TipoVehiculo.FURGONETA)) {
			String marca=leerCadena("Introduzca la marca.");
			String modelo=leerCadena("Introduzca el modelo.");
			String matricula=leerCadena("Introduzca la matricula.");
			int plazas=leerEntero("Introduce el numero de plazas.");
			int pma=leerEntero("Introduce el peso máximo autorizado.");
			vehiculo=new Furgoneta(marca, modelo, plazas, pma, matricula);
		}
		return vehiculo;
	}
	
	public static LocalDate leerMes() {
		return leerFecha("Introduzca la fecha completa del mes a obtener");
		
		
	}
}
