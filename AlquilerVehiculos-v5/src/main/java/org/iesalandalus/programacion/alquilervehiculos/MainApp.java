package org.iesalandalus.programacion.alquilervehiculos;

import java.util.Iterator;

import org.iesalandalus.programacion.alquilervehiculos.controlador.ControladorMVC;
import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.FuenteDatosMemoria;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mongodb.FactoriaFuenteDatosMongoDB;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mysql.FactoriaFuenteDatosMysql;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.iugrafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.iutextual.VistaTexto;

public class MainApp {

	public static void main(String[] args) {
		//v5
		if(args.length==0) {
			Modelo modelo=new ModeloCascada(new FactoriaFuenteDatosMongoDB());
			Vista vista=new VistaGrafica();
			IControlador controlador= new ControladorMVC(modelo,vista);
			controlador.comenzar();
			
		}else {
			
		for (int i = 0; i<args.length;i++) {
			switch (args[i]) {
			case "-mt": {
				Modelo modelo=new ModeloCascada(new FuenteDatosMemoria());
				Vista vista=new VistaTexto();
				IControlador controlador= new ControladorMVC(modelo,vista);
				controlador.comenzar();
				break;
			}
			case "-mg": {
				Modelo modelo=new ModeloCascada(new FuenteDatosMemoria());
				Vista vista=new VistaGrafica();
				IControlador controlador= new ControladorMVC(modelo,vista);
				controlador.comenzar();
				break;
			}
			case "-ft": {
				Modelo modelo=new ModeloCascada(new FuenteDatosFicheros());
				Vista vista=new VistaTexto();
				IControlador controlador= new ControladorMVC(modelo,vista);
				controlador.comenzar();
				break;
			}
			case "-fg": {
				Modelo modelo=new ModeloCascada(new FuenteDatosFicheros());
				Vista vista=new VistaGrafica();
				IControlador controlador= new ControladorMVC(modelo,vista);
				controlador.comenzar();
				break;
			}
			case "-bmyt": {
				Modelo modelo=new ModeloCascada(new FactoriaFuenteDatosMysql());
				Vista vista=new VistaTexto();
				IControlador controlador= new ControladorMVC(modelo,vista);
				controlador.comenzar();
				break;
			}
			case "-bmyg": {
				Modelo modelo=new ModeloCascada(new FactoriaFuenteDatosMysql());
				Vista vista=new VistaGrafica();
				IControlador controlador= new ControladorMVC(modelo,vista);
				controlador.comenzar();
				break;
			}
			case "-bmot": {
				Modelo modelo=new ModeloCascada(new FactoriaFuenteDatosMongoDB());
				Vista vista=new VistaTexto();
				IControlador controlador= new ControladorMVC(modelo,vista);
				controlador.comenzar();
				break;
			}
			default:{
				System.err.println("ERROR: El argumento introducido no es correcto.");
			}
		}

	}	
	}
		

	}

}
