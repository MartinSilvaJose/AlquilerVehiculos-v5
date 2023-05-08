package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;
public class Turismo extends Vehiculo{
	
	
	//DECLARACIÓN
	private final int FACTOR_CILINDRADA=10;
	private int cilindrada;
	
	
	//CONSTRUCTORES
	
	public Turismo(String marca,String modelo,int cilindrada,String matricula) {
		super(marca,modelo,matricula);
		setCilindrada(cilindrada);
	}
	
	public Turismo(Turismo t) {
		super(t);
		setCilindrada(t.getCilindrada());

	}
	
	//CILINDRADA
	
	public int getCilindrada() {
		return cilindrada;
	}
	public void setCilindrada(int cilindrada) {
		if(cilindrada<1 || cilindrada>5000) {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		}
		
		this.cilindrada = cilindrada;
	}
	
	
	//METODOS DE CLASE
	@Override
	protected int getFactorPrecio() {
		int precio=this.cilindrada/FACTOR_CILINDRADA;
		return precio;
	}
	
	
	//EQUALS & HASCODE
	
	@Override
	public int hashCode() {
		return Objects.hash(super.getMatricula());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turismo other = (Turismo) obj;
		return Objects.equals(super.getMatricula(), other.getMatricula());
	}

	
	//TOSTRING
	
	@Override
	public String toString() {
		return String.format("%s %s (%sCV) - %s", super.getMarca(), super.getModelo(), this.cilindrada, super.getMatricula(), "disponible");
	}

}
