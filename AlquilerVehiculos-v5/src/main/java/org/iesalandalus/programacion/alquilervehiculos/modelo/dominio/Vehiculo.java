package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;
public abstract class Vehiculo {
	
	
	//DECLARACIÓN
	
	private String ER_MARCA= "[A-Za-z]+([ -]?[A-Za-z]+)?";
	private String ER_MATRICULA="(?i)[0-9]{4}[bcdfghjklmnpqrstvwxyz]{3}";

	private String marca,modelo,matricula;
	
	
	//CONSTRUCTORES
	
	public Vehiculo(String marca,String modelo,String matricula) {
		setMarca(marca);
		setModelo(modelo);
		setMatricula(matricula);
	}
	
	public Vehiculo(Vehiculo t) {
		if(t==null) {
			throw new NullPointerException("ERROR: No es posible copiar un turismo nulo.");
		}
		setMarca(t.getMarca());
		setModelo(t.getModelo());
		setMatricula(t.getMatricula());

	}
	
	
	//MARCA 
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		if(marca==null) {
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		}

		if(!marca.matches(ER_MARCA)) {
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido");
		}
		this.marca = marca;
	}
	
	
	//MODELO
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		if(modelo==null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}
		if(modelo.trim().isEmpty()) {
			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
		}
		this.modelo = modelo;
	}	
	
	
	//MATRICULA
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		if(matricula==null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		}
		if(!matricula.matches(ER_MATRICULA)) {
			throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
		}
		String letras=matricula.toUpperCase().substring(4, 7);
		matricula=matricula.substring(0, 4)+letras;
		this.matricula = matricula;
	}
	
	
	//METODOS DE CLASE
	
	protected abstract int getFactorPrecio();
	
	public static Vehiculo copiar(Vehiculo v) {
		if(v instanceof Turismo) {
			Turismo t=new Turismo((Turismo)v);
			 return t;
		}
		if(v instanceof Furgoneta) {
			Furgoneta f=new Furgoneta((Furgoneta)v);
			return f;
		}
		if(v instanceof Autobus) {
			Autobus a=new Autobus((Autobus)v);
			return a;
		}

		return null;
	}
	public static Vehiculo getVehiculoConMatricula(String matricula) {
		if(matricula==null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		}
		return new Turismo("Seat","Leon",2000, matricula);
	}
	
	
	//EQUALS & HASCODE
	
	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vehiculo))
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}
}
