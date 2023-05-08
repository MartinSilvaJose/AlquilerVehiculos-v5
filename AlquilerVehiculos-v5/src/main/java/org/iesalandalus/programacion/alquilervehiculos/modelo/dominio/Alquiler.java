package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {
	
	
	//DECLARACIÓN
	
	static final DateTimeFormatter FORMATO_FECHA= DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private int PRECIO_DIA=20;
	private LocalDate fechaAlquiler,fechaDevolucion;
	private Cliente cliente;
	private Vehiculo vehiculo;

	
	//CONSTRUCTORES
	public Alquiler(Cliente cliente,Vehiculo vehiculo,LocalDate fechaAlquiler) {
		setCliente(cliente);
		setVehiculo(vehiculo);
		setFechaAlquiler(fechaAlquiler);
	}
	
	public Alquiler(Alquiler a) {
		if(a==null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		}
		setCliente(new Cliente(a.getCliente()));
		setVehiculo(Vehiculo.copiar(a.getVehiculo()));
		setFechaAlquiler(a.getFechaAlquiler());
		if(a.getFechaDevolucion()!=null) {
			setFechaDevolucion(a.getFechaDevolucion());
		}
	}
	
	
	//FECHA ALQUILER
	
	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}
	public void setFechaAlquiler(LocalDate fechaAlquiler) {
		if(fechaAlquiler==null) {
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		}
		if(fechaAlquiler.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		}
		this.fechaAlquiler = fechaAlquiler;
	}
	

	//FECHA DEVOLUCIÓN
	
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		if(fechaDevolucion==null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}
		if(fechaDevolucion.isBefore(fechaAlquiler)) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}
		if(fechaDevolucion.isEqual(fechaAlquiler)) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}
		if(fechaDevolucion.isAfter(LocalDate.now())){
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}
		this.fechaDevolucion = fechaDevolucion;
	}
	
	
	//CLIENTE
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		if(cliente==null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}

		this.cliente=cliente;
	}
	
	
	//TURISMO
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		if(vehiculo==null) {
			throw new NullPointerException("ERROR: El vehiculo no puede ser nulo.");
		}

		this.vehiculo=vehiculo;
	}
	
	
	//METODOS DE CLASE
	
	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
//		if(fechaDevolucion==null) {
//			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
//		}
		if(this.fechaDevolucion !=null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}
		setFechaDevolucion(fechaDevolucion);
	}
	
	public int getPrecio() {
		int precio =0;
		if(fechaDevolucion!=null) {
			int numDias=(int)ChronoUnit.DAYS.between(getFechaAlquiler(), getFechaDevolucion());
			precio=(PRECIO_DIA+vehiculo.getFactorPrecio())*numDias;
		}
		return precio;
	}
	
	
	//HASCODE & EQUALS

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, vehiculo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(vehiculo, other.vehiculo);
	}


	//TOSTRING
	
	@Override
	public String toString() {
		String string="ERROR.";
		if(fechaDevolucion==null) {
			string=String.format("%s <---> %s, %s - %s (%d€)", cliente, vehiculo,
					getFechaAlquiler().format(FORMATO_FECHA), "Aún no devuelto", getPrecio());
		}else {
			string=String.format("%s <---> %s, %s - %s (%d€)", cliente, vehiculo,
					fechaAlquiler.format(FORMATO_FECHA),fechaDevolucion.format(FORMATO_FECHA), getPrecio());
		}

		return string;
	}
}
