package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.ControladorMVC;
import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mongodb.FactoriaFuenteDatosMongoDB;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.iugrafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.iutextual.VistaTexto;

public class MainApp {

	public static void main(String[] args) {
		//v5
		Modelo modelo=new ModeloCascada(new FactoriaFuenteDatosMongoDB());
		Vista vista=new VistaGrafica();
		IControlador controlador= new ControladorMVC(modelo,vista);
		controlador.comenzar();
	}

}
