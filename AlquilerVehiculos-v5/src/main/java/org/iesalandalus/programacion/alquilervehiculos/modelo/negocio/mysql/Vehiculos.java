package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mysql.utilidades.MySQL;
import org.iesalandalus.programacion.alquilervehiculos.vista.TipoVehiculo;

import com.mysql.cj.xdevapi.Statement;

public class Vehiculos implements IVehiculos {

	private Connection conexion = null;
	
	private static Vehiculos instancia;
	
	private Vehiculos() {
		
	}
	
	static Vehiculos getInstancia() {
		if(instancia==null) {
			instancia=new Vehiculos();
		}
		return instancia;
	}
	
	@Override
	public void comenzar() {
		conexion = MySQL.establecerConexion();
	}

	@Override
	public void terminar() {
		MySQL.cerrarConexion();
	}

	@Override
	public List<Vehiculo> get() {
		List<Vehiculo> vehiculos=new ArrayList<>();
		
		try {
			String sentenciaStr="select matricula, modelo, marca, tipo, cilindrada, plazas, pma from vehiculos";
			java.sql.Statement sentencia = conexion.createStatement();
			ResultSet filas = sentencia.executeQuery(sentenciaStr);
			while(filas.next()) {
				String matricula =filas.getString(1);
				String modelo =filas.getString(2);
				String marca =filas.getString(3);
				String tipo =filas.getString(4);
				
				if(tipo.equals(TipoVehiculo.TURISMO.getNombre().toLowerCase())) {
					int cilindrada =filas.getInt(5);
					
					Vehiculo vehiculo=new Turismo(marca,modelo,cilindrada,matricula);
					vehiculos.add(vehiculo);
					
				}else if(tipo.equals(TipoVehiculo.AUTOBUS.getNombre().toLowerCase())) {
					int plazas =filas.getInt(6);
					
					Vehiculo vehiculo=new Autobus(marca, modelo, plazas, matricula);
					vehiculos.add(vehiculo);
					
				}else if(tipo.equals(TipoVehiculo.FURGONETA.getNombre().toLowerCase())) {
					int plazas =filas.getInt(6);
					int pma =filas.getInt(7);
					
					Vehiculo vehiculo=new Furgoneta(marca, modelo, plazas, pma, matricula);
					vehiculos.add(vehiculo);
				}
				
			}
		}catch (SQLException e) {
			throw new IllegalArgumentException("ERROR"+e.getMessage());
		}
		
		return vehiculos;
	}

	@Override
	public int getCantidad() {
		int tamano = 0;
		try {
			String sentenciaStr = "select count(*) from vehiculos";
			java.sql.Statement sentencia = conexion.createStatement();
			ResultSet filas =sentencia.executeQuery(sentenciaStr);
			if(filas.next()) {
				tamano=filas.getInt(1);
			}	
		} catch (SQLException e) {
			throw new IllegalArgumentException("ERROR:"+e.getMessage());
		}
		
		return tamano;
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if(vehiculo==null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehiculo nulo.");
		}
		try {
			String sentenciaStr = "insert into vehiculos values (?,?,?,?,?,?,?)";
			PreparedStatement sentencia =conexion.prepareStatement(sentenciaStr);
			sentencia.setString(1, vehiculo.getMatricula());
			sentencia.setString(2, vehiculo.getModelo());
			sentencia.setString(3, vehiculo.getMarca());
			
			
			if(TipoVehiculo.get(vehiculo).equals(TipoVehiculo.TURISMO)) {
				Turismo turismo=new Turismo((Turismo)vehiculo);
				sentencia.setString(4, "turismo");
				sentencia.setInt(5,turismo.getCilindrada());
				sentencia.setObject(6, null);
				sentencia.setObject(7, null);
				
			}else if(TipoVehiculo.get(vehiculo).equals(TipoVehiculo.AUTOBUS)) {
				Autobus autobus=new Autobus((Autobus)vehiculo);
				sentencia.setString(4, "autobus");
				sentencia.setObject(5, null);
				sentencia.setInt(6, autobus.getPlazas());
				sentencia.setObject(7, null);

			}else if(TipoVehiculo.get(vehiculo).equals(TipoVehiculo.FURGONETA)) {
				Furgoneta furgoneta=new Furgoneta((Furgoneta)vehiculo);
				sentencia.setString(4, "furgoneta");
				sentencia.setObject(5, null);
				sentencia.setInt(6, furgoneta.getPlazas());
				sentencia.setObject(7, furgoneta.getPma());
				
			}
			sentencia.executeUpdate();

		} catch (SQLException e) {
			throw new IllegalArgumentException("ERROR:"+e.getMessage());
		}
		
		
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo vehiculoBuscado = null;
		
		if(vehiculo==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un vehículo nulo.");
			
		}
		try {
			String sentenciaStr="select matricula, modelo, marca, tipo, cilindrada, plazas, pma from vehiculos where matricula=?";
			PreparedStatement sentencia=conexion.prepareStatement(sentenciaStr);
			sentencia.setString(1, vehiculo.getMatricula());
			ResultSet filas= sentencia.executeQuery();
			if(filas.next()) {
				String matricula = filas.getString(1);
				String modelo = filas.getString(2);
				String marca = filas.getString(3);
				String tipo = filas.getString(4);
				if(TipoVehiculo.TURISMO.getNombre().toLowerCase().equals(tipo)) {
					int cilindrada =filas.getInt(5);
					vehiculoBuscado =new Turismo(marca, modelo, cilindrada, matricula);
					
				}else if(TipoVehiculo.AUTOBUS.getNombre().toLowerCase().equals(tipo)) {
					int plazas = filas.getInt(6);
					vehiculoBuscado=new Autobus(marca, modelo, plazas, matricula);

				}else if(TipoVehiculo.FURGONETA.getNombre().toLowerCase().equals(tipo)) {
					int plazas = filas.getInt(6);
					int pma = filas.getInt(7);
					vehiculoBuscado=new Furgoneta(marca, modelo, plazas, pma, matricula);

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vehiculoBuscado;
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if(vehiculo==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un vehículo nulo.");
			
		}
		try {
			String sentenciaStr="delete from vehiculos where matricula=?";
			PreparedStatement sentencia=conexion.prepareStatement(sentenciaStr);
			sentencia.setString(1,vehiculo.getMatricula());
			
			if(sentencia.executeUpdate()==0) {
				throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con los datos indicados.");
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException("ERROR:"+e.getMessage());
		}
		
	}

	@Override
	public void guardar() {
		// TODO Auto-generated method stub
		
	}

}
