package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.FuenteDatosMemoria;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mongodb.FactoriaFuenteDatosMongoDB;

public enum FactoriaFuenteDatos {
	MEMORIA{
		IFuenteDatos crear() {
			return new FuenteDatosMemoria();
		}
	},
	FICHEROS{

		@Override
		IFuenteDatos crear() {
			return new FuenteDatosFicheros();
		}
		
	},
	MONGODB{
		@Override
		IFuenteDatos crear() {
			return new FactoriaFuenteDatosMongoDB();
		}
	};
	abstract IFuenteDatos crear();
	
}
