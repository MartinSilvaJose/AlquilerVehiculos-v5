package org.iesalandalus.programacion.alquilervehiculos.vista.iutextual;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.TipoVehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

import javafx.stage.Stage;

public class VistaTexto extends Vista {	
	
	//CONSTRUCTOR
	public VistaTexto() {
		
	}
	
	//	COMENZAR Y TERMINAR
	public void comenzar() {
		Consola.mostrarCabecera("Programa de administración de alquileres");
		Consola.mostrarMenu();
		ejecutar(Consola.elegirOpcion());
	}
	public void terminar() {
		controladorMVC.terminar();
		System.out.println("Hasta la próxima.");
	}
	
	
	//	EJECUTAR	
	protected void ejecutar(Accion opcion) {
		switch(opcion) {
		case SALIR:
			terminar();
			System.exit(0);
			break;
		case INSERTAR_CLIENTE:
			insertarCliente();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case INSERTAR_VEHICULO:
			insertarVehiculo();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case INSERTAR_ALQUILER:
			insertarAlquiler();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case BUSCAR_CLIENTE:
			buscarCliente();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case BUSCAR_VEHICULO:
			buscarVehiculo();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case BUSCAR_ALQUILER:
			buscarAlquiler();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case MODIFICAR_CLIENTE:
			modificarCliente();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case DEVOLVER_ALQUILER_CLIENTE:
			devolverAlquilerCliente();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case DEVOLVER_ALQUILER_VEHICULO:
			devolverAlquilerVehiculo();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case BORRAR_CLIENTE:
			borrarCliente();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case BORRAR_VEHICULO:
			borrarVehiculo();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case BORRAR_ALQUILER:
			borrarAlquiler();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case LISTAR_CLIENTES:
			listarClientes();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case LISTAR_VEHICULO:
			listarVehiculos();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case LISTAR_ALQUILERES:
			listarAlquileres();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case LISTAR_ALQUILERES_CLIENTE:
			listarAlquileresCliente();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case LISTAR_ALQUILERES_VEHICULO:
			listarAlquileresVehiculo();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		case MOSTRAR_ESTADISTICAS_MENSUALES:
			mostrarEstadisticasMensualesTipoVehiculo();
			System.out.println("Operación realizada con éxito");
			comenzar();
			break;
		}
				
	}
	
	
	//	INSERTAR 
	protected void insertarCliente() {
		Consola.mostrarCabecera("Insertar cliente");
		try {
			controladorMVC.insertar(Consola.leerCliente());
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}
	protected void insertarVehiculo() {
		Consola.mostrarCabecera("Insertar Vehiculo");
		try {
			controladorMVC.insertar(Consola.leerVehiculo());
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}
	protected void insertarAlquiler() {
		Consola.mostrarCabecera("Insertar alquiler");
		try {
			Alquiler alquiler=Consola.leerAlquiler();
			Cliente cliente=alquiler.getCliente();
			Vehiculo vehiculo=alquiler.getVehiculo();
			
			cliente = controladorMVC.buscar(cliente);
			vehiculo =controladorMVC.buscar(vehiculo);
			LocalDate fAlquiler=alquiler.getFechaAlquiler();
			
			controladorMVC.insertar(new Alquiler(cliente, vehiculo, fAlquiler ));
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}
	
	//	BUSCAR
	protected void buscarCliente() {
		Consola.mostrarCabecera("Buscar cliente");
		Cliente cliente=controladorMVC.buscar(Consola.leerClienteDni());
		if(cliente==null) {
			System.out.println("ERROR:El cliente que busca no existe");
		}
		else {
			System.out.println(cliente);
		}

	}
	protected void buscarVehiculo() {
		Consola.mostrarCabecera("Buscar vehiculo");
		Vehiculo vehiculo=controladorMVC.buscar(Consola.leerVehiculoMatricula());
		if(vehiculo==null) {
			System.out.println("ERROR:El turismo que busca no existe");
		}
		else {
			System.out.println(vehiculo);

		}

	}
	protected void buscarAlquiler() {
		Consola.mostrarCabecera("Buscar alquiler");
		Alquiler alquiler=controladorMVC.buscar(Consola.leerAlquiler());
		if(alquiler==null) {
			System.out.println("ERROR:El Alquiler que busca no existe");
		}
		else {
			System.out.println(alquiler);
		}
	}
	
	
	//	MODIFICAR Y DEVOLVER
	protected void modificarCliente() {
		Consola.mostrarCabecera("Modificar cliente");
		try {
			Cliente cliente=controladorMVC.buscar(Consola.leerClienteDni());
			controladorMVC.modificar(cliente,Consola.leerNombre(),Consola.leerTelefono());
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	protected void devolverAlquilerCliente() {
		Consola.mostrarCabecera("Devolver alquiler cliente");
		try {
			controladorMVC.devolver(controladorMVC.buscar(Consola.leerClienteDni()) ,Consola.leerFechaDevolucion());
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	protected void devolverAlquilerVehiculo() {
		Consola.mostrarCabecera("Devolver alquiler vehículo");
		try {
			controladorMVC.devolver(controladorMVC.buscar(Consola.leerVehiculoMatricula()),Consola.leerFechaDevolucion());
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//	BORRAR
	protected void borrarCliente() {
		Consola.mostrarCabecera("Borrar cliente");
		try {
			controladorMVC.borrar(controladorMVC.buscar(Consola.leerClienteDni()));
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}
	protected void borrarVehiculo() {
		Consola.mostrarCabecera("Borrar vehiculo");
		try {
			controladorMVC.borrar(controladorMVC.buscar(Consola.leerVehiculoMatricula()));
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}
	protected void borrarAlquiler() {
		Consola.mostrarCabecera("Borrar alquiler");
		try {
			controladorMVC.borrar(Consola.leerAlquiler());
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	//	LISTAR
	protected void listarClientes() {
		Consola.mostrarCabecera("Listar clientes");
		List<Cliente> clientes=new ArrayList<>(controladorMVC.getClientes());
		clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
		
		if(clientes.size()==0) {
			System.out.println("No hay clientes que mostrar");
		}
		else {
			for(Cliente cliente:clientes) {
				
				System.out.println(cliente);
			}
		}

	}
	protected void listarVehiculos() {
		Consola.mostrarCabecera("Listar vehiculos");
		List<Vehiculo> vehiculos=new ArrayList<>(controladorMVC.getVehiculo());
		vehiculos.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo).thenComparing(Vehiculo::getMatricula));
		
		if(vehiculos.size()==0) {
			System.out.println("No hay vehiculos que mostrar");
		}
		else {
			for(Vehiculo vehiculo:vehiculos) {
				
				System.out.println(vehiculo);
			}
		}
	}
	protected void listarAlquileres() {
			Consola.mostrarCabecera("Listar alquileres");
				
			List<Alquiler> alquileres=new ArrayList<>(controladorMVC.getAlquileres());
			Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
			alquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente, comparadorCliente));
				
			if (alquileres.size()==0)
				System.out.println("No hay alquileres que mostrar.");
			else
			{
				for(Alquiler alquiler:alquileres)
				{
					System.out.println(alquiler);
				}
			}
	}
	
	
	//	LISTAR CON PARAMETROS
	protected void listarAlquileresCliente() {
		Consola.mostrarCabecera("Listar alquileres por clientes");
		try {
			System.out.println(controladorMVC.getAlquileres(controladorMVC.buscar(Consola.leerClienteDni())));
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
	}
	protected void listarAlquileresVehiculo() {
		Consola.mostrarCabecera("Listar alquileres por vehiculo");
		try {
			System.out.println(controladorMVC.getAlquileres(controladorMVC.buscar(Consola.leerVehiculoMatricula())));
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//ESTADISTICAS
	
	private Map<TipoVehiculo,Integer> inicializarEstadisticas(){
		Map<TipoVehiculo, Integer> estadisticas=new EnumMap<>(TipoVehiculo.class);
		for(TipoVehiculo tipo:TipoVehiculo.values()) {
			estadisticas.put(tipo, 0);
		}
		return estadisticas;
		
	}
	
	public void mostrarEstadisticasMensualesTipoVehiculo() {
		
		Map<TipoVehiculo,Integer> estadistica= inicializarEstadisticas();
		LocalDate inicioMes=Consola.leerMes().withDayOfMonth(1);
		LocalDate finMes=inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());
		List<Alquiler> alquileres=new ArrayList<>(controladorMVC.getAlquileres());
		
		for(Alquiler a:alquileres) {
			if((a.getFechaAlquiler().isAfter(inicioMes) || a.getFechaAlquiler().isEqual(inicioMes)) && (a.getFechaAlquiler().isBefore(finMes) || a.getFechaAlquiler().isEqual(finMes))) {
				TipoVehiculo tipoVehiculo=TipoVehiculo.get(a.getVehiculo());
				estadistica.put(tipoVehiculo, estadistica.get(tipoVehiculo)+1);
			}
		}
		System.out.println(estadistica);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
