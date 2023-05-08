package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;
public class Autobus extends Vehiculo{
	
	
	//DECLARACIÓN
	private final int FACTOR_PLAZAS=2;
	private int plazas;
	
	
	//CONSTRUCTORES
	
	public Autobus(String marca,String modelo,int plazas,String matricula) {
		super(marca,modelo,matricula);
		setPlazas(plazas);
	}
	
	public Autobus(Autobus a) {
		super(a);
		setPlazas(a.getPlazas());

	}
	
	//CILINDRADA
	
	public int getPlazas() {
		return plazas;
	}
	public void setPlazas(int plazas) {
		if(plazas<9 || plazas>256) {
			throw new IllegalArgumentException("ERROR: El número de plazas no se corresponde con un autobus");
		}
		
		this.plazas = plazas;
	}
	
	
	//METODOS DE CLASE
	@Override
	protected int getFactorPrecio() {
		int precio=this.plazas*FACTOR_PLAZAS;
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
		Autobus other = (Autobus) obj;
		return Objects.equals(super.getMatricula(), other.getMatricula());
	}

	
	//TOSTRING
	
	@Override
	public String toString() {
		return String.format("%s %s (%s Plazas) - %s", super.getMarca(), super.getModelo(), this.plazas, super.getMatricula(), "disponible");
	}

}
