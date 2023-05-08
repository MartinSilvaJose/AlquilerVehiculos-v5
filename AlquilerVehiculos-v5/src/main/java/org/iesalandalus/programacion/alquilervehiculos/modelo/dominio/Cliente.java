package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;


public class Cliente {
	
	
	//DECLARACIÓN
	
	private String ER_NOMBRE="[A-Z][a-zñ]+( [A-Z][a-zñ]+)*";
	private String ER_DNI="[0-9]{8}[a-zA-Z]";
	private String ER_TELEFONO="[0-9]{9}";
	private String nombre,dni,telefono;
	
	
	//CONSTRUCTORES
	
	public Cliente(String nombre,String dni,String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);

		
	}
	
	public Cliente(Cliente c) {
		if(c==null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}
		setNombre(c.getNombre());
		setTelefono(c.getTelefono());
		setDni(c.getDni());
	}
	
	
	//NOMBRE
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if(nombre==null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if(!nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}
//		if(nombre.trim().isEmpty()) {
//			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
//		}

//		String nombreAGuardar="";
//		String [] palabras = nombre.trim().split("\\s");
//		for(String i:palabras) {
//			String letraMayus=i.toUpperCase().charAt(0)+"";
//			
//			nombreAGuardar+=letraMayus+i.substring(1)+" ";
//		}
//		nombreAGuardar=nombreAGuardar.trim();
		this.nombre = nombre;
	}
	
	
	//DNI
	
	public String getDni() {
		
		return dni;
	}
	private void setDni(String dni) {

		
		if(dni==null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		if(!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if(!comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}

		this.dni = dni;
		
	}
	
	
	//TELEFONO
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		if(telefono==null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		}
		if(!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
		this.telefono = telefono;
	}
	
	
	//METODOS DE CLASE

	  private boolean comprobarLetraDni(String dni) {
		if(dni==null) {
			throw new NullPointerException("ERROR:No se puede comprobar la letra de un dni nulo.");
		}
		if(!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR:El dni no tiene un formato válido.");
		}
		int calculo=(Integer.parseInt(dni.substring(0,8)))%23;
		char letraDni=dni.toUpperCase().charAt(8);
		char [] letraPosible= {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
		
		
		if(letraPosible[calculo]==letraDni)  {
			return true;
		}
		else {
			return false;
		}
	}

	
	public static Cliente getClienteConDni(String dni) {
		if(dni==null) {
			throw new NullPointerException("ERROR: El dni no puede ser nulo.");
		}
		return new Cliente("Pedro",dni,"600500400");
	}
	
	
	//EQUALS & HASCODE
	
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	
	//TOSTRING
	
	@Override
	public String toString() {
		return String.format("%s - %s (%s)", this.nombre, this.dni, this.telefono);
	}
	
	
}
