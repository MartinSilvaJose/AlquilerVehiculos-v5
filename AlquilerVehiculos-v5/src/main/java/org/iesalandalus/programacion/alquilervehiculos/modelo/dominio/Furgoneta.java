package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;
public class Furgoneta extends Vehiculo{
	
	
	//DECLARACIÓN
	private final int FACTOR_PMA=100;
	private final int FACTOR_PLAZAS=1;
	private int plazas;
	private int pma;
	
	//CONSTRUCTORES
	
	public Furgoneta(String marca,String modelo,int plazas,int pma,String matricula) {
		super(marca,modelo,matricula);
		setPlazas(plazas);
		setPma(pma);
	}
	
	public Furgoneta(Furgoneta f) {
		super(f);
		setPlazas(f.getPlazas());
		setPma(f.getPma());

	}
	
	//PLAZAS
	
	public int getPlazas() {
		return this.plazas;
	}
	public void setPlazas(int plazas) {
		if(plazas<3 || plazas>8) {
			throw new IllegalArgumentException("ERROR: El número de plazas no se corresponde con una furgoneta");
		}
		
		this.plazas = plazas;
	}
	
	//PMA
	
	public int getPma() {
		return this.pma;
	}
	public void setPma(int pma) {
		if(pma<3500 || pma>7500) {
			throw new IllegalArgumentException("ERROR: El PMA no se corresponde con una furgoneta");
		}
		
		this.pma = pma;
	}
	
	
	//METODOS DE CLASE
	@Override
	protected int getFactorPrecio() {
		int precio=this.pma/FACTOR_PMA+this.plazas*FACTOR_PLAZAS;
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
		Furgoneta other = (Furgoneta) obj;
		return Objects.equals(super.getMatricula(), other.getMatricula());
	}

	
	//TOSTRING
	
	@Override
	public String toString() {
		return String.format("%s %s (%s Plazas con %s PMA) - %s", super.getMarca(), super.getModelo(), this.plazas, this.pma, super.getMatricula(), "disponible");
	}

}
