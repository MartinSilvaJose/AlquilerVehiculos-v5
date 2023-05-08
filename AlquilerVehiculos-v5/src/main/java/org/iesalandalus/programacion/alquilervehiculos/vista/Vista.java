package org.iesalandalus.programacion.alquilervehiculos.vista;

import org.iesalandalus.programacion.alquilervehiculos.controlador.*;

import javafx.application.Application;

public abstract class Vista extends Application{
	
	//	DECLARACION
	protected IControlador controladorMVC;
	
	//	SETTER	
	public void setControlador(IControlador controlador) {
		if(controlador==null) {
			throw new NullPointerException("No puedes pasar un controlador nulo.");
		}
		this.controladorMVC=controlador;
	}
	
	public abstract void comenzar();
	
	public abstract void terminar();
	
	
}
