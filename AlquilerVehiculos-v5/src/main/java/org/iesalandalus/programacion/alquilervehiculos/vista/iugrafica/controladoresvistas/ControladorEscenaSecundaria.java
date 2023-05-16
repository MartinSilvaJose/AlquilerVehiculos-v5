package org.iesalandalus.programacion.alquilervehiculos.vista.iugrafica.controladoresvistas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.TipoVehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.iugrafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControladorEscenaSecundaria {
	
//	DECLARACIÓN
	
	private IControlador controladorMVC;
	private Cliente cliente=null;
	private Vehiculo vehiculo=null;
	private Alquiler alquiler=null;
	
//	SETTERS
	
    public void setControladorMVC(IControlador controlador){   
        controladorMVC = controlador;        
    }
    
    public void setAccion(String interfaz) {
        if(interfaz.equals("Clientes")) {
        	gpClientes.setVisible(true);
        }else if(interfaz.equals("Vehiculos")) {
        	gpVehiculos.setVisible(true);
        }else if(interfaz.equals("Alquileres")) {
        	gpAlquileres.setVisible(true);
        }else if(interfaz.equals("Modificar")) {
        	gpModificarClientes.setVisible(true);
        }else if(interfaz.equals("MostrarEstadisticas")) {
        	labelMEstadisticasYDevolver.setText("Mostrar Estadísticas");
        	btnMEstadisticasYDevolver.setText("Mostrar");
        	gpMEstadisticasYDevolver.setVisible(true);
        	gpMostrarEstadisticas.setVisible(true);
        }else if(interfaz.equals("Devolver")) {
        	labelMEstadisticasYDevolver.setText("Delvolver Alquiler");
        	btnMEstadisticasYDevolver.setText("Devolver");
        	gpMEstadisticasYDevolver.setVisible(true);
        }
        
    }
    
    public void setCliente(Cliente cliente) {
    	this.cliente=cliente;
    }
    
    public void setTfClienteMNombre(String nuevoNombre) {
    	this.tfClienteMNombre.setText(nuevoNombre);
    }
    
    public void setTfClienteMTelefono(String nuevoTelefono) {
    	this.tfClienteMTelefono.setText(nuevoTelefono);
    }
    
	public void setAlquiler(Alquiler alquiler) {
		this.alquiler=alquiler;
	}
	
//	FXML CLIENTES
	
    @FXML
    private GridPane gpClientes;
 
    @FXML
    private TextField tfClienteDni;

    @FXML
    private TextField tfClienteNombre;

    @FXML
    private TextField tfClienteTelefono;

    @FXML
    private Button btnInsertarCliente;
    
//    FXML VEHÍCULOS
    
    @FXML
    private GridPane gpVehiculos;

    @FXML
    private RadioButton rbAutobus;

    @FXML
    private RadioButton rbFurgoneta;

    @FXML
    private RadioButton rbTurismo;
    
    @FXML
    private ToggleGroup tgTipoVehiculo;

    @FXML
    private Label lVehiculoCilindrada;

    @FXML
    private Label lVehiculoPlazas;

    @FXML
    private Label lVehiculoPma;
    
    @FXML
    private TextField tfVehiculoCilindrada;

    @FXML
    private TextField tfVehiculoMarca;

    @FXML
    private TextField tfVehiculoMatricula;

    @FXML
    private TextField tfVehiculoModelo;

    @FXML
    private TextField tfVehiculoPlazas;

    @FXML
    private TextField tfVehiculoPma;
    
    @FXML
    private Button btnInsertarVehiculo;
    
//    FXML ALQUILER
    
    @FXML
    private GridPane gpAlquileres;
    
    @FXML
    private TextField tfAlquilerCliente;

    @FXML
    private TextField tfAlquilerVehiculo;

    @FXML
    private DatePicker dpInsertarAlquiler;
    
    @FXML
    private Button btnInsertarAlquiler;

//  FXML MODIFICAR CLIENTES
    
    @FXML
    private GridPane gpModificarClientes;

    @FXML
    private Button btnModificarCliente;

    @FXML
	 private TextField tfClienteMNombre;

    @FXML
    private TextField tfClienteMTelefono;
  
//  FXML MOSTRAR ESTADÍSTICAS Y DEVOLVER

    @FXML
    private GridPane gpMEstadisticasYDevolver;
    
    @FXML
    private GridPane gpMostrarEstadisticas;

    @FXML
    private Button btnMEstadisticasYDevolver;
    
    @FXML
    private DatePicker dpMEstadisticasYDevolver;
    
    @FXML
    private Label labelEstadisticaAutobuses;

    @FXML
    private Label labelEstadisticaFurgoneta;

    @FXML
    private Label labelEstadisticaTurismo;

    @FXML
    private Label labelMEstadisticasYDevolver;
    
    @FXML
    
    
    void accionMEstadisticaODevolver(ActionEvent event) {
		LocalDate fecha=dpMEstadisticasYDevolver.getValue();

	    if(gpMostrarEstadisticas.isVisible()) {
	    	if(fecha!=null) {
		    	mostrarEstadisticas(fecha);
	    	}else {
	    		Dialogos.mostrarDialogoInformacion("Error", "Debe de rellenar el campo fecha.");
	    	}
	    	}else if(!gpMostrarEstadisticas.isVisible() && gpMEstadisticasYDevolver.isVisible()) {
	    	if(fecha!=null) {
	    		try {
	    			
		    		controladorMVC.devolver(alquiler.getCliente(), fecha);
		    		Dialogos.mostrarDialogoInformacion("Devolver Alquiler", "Se ha establecido la fecha de devolución correctamente");
		    		salir(btnMEstadisticasYDevolver);
				} catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
					Dialogos.mostrarDialogoError("Devolver Alquiler",e.getMessage());
				}
	    	}else {
	    		Dialogos.mostrarDialogoInformacion("Error", "Debe de rellenar el campo fecha.");
	    	}
	    }
    }

    @FXML
    void ModificarCliente(ActionEvent event) {
    	String nuevoCliente=tfClienteMNombre.getText();
    	String nuevoTelefono=tfClienteMTelefono.getText();
    	
    	if(!nuevoCliente.trim().isEmpty() && !nuevoTelefono.trim().isEmpty()) {
    		try {
    			controladorMVC.modificar(cliente, nuevoCliente, nuevoTelefono);
			
    			Dialogos.mostrarDialogoInformacion("Modificar cliente", "Se ha modificado el cliente correctamente");
    			salir(this.btnModificarCliente);
    		} catch (OperationNotSupportedException e) {
    			Dialogos.mostrarDialogoError("Modificar cliente",e.getMessage());
    		}
    	}else {
    		Dialogos.mostrarDialogoInformacion("Modificar cliente", "Debe de rellenar todos los campos para modificarlos");
    	}
    }
    @FXML
    void insertarCliente(ActionEvent event) {
    	cliente=null;
    	String nombre=tfClienteNombre.getText();
    	String dni=tfClienteDni.getText();
    	String telefono=tfClienteTelefono.getText();
    	
    	
    	if(!nombre.trim().isEmpty() && !dni.trim().isEmpty() && !telefono.trim().isEmpty()) {

    		try {
        		cliente=new Cliente(nombre, dni, telefono);
				controladorMVC.insertar(cliente);
				Dialogos.mostrarDialogoInformacion("Insertar cliente", "Se ha insertado el cliente correctamente");
				salir(this.btnInsertarCliente);
			} catch (OperationNotSupportedException | IllegalArgumentException e) {
				Dialogos.mostrarDialogoError("Insertar cliente", e.getMessage());
			}
    	}else {
    		Dialogos.mostrarDialogoInformacion("Insertar cliente", "Debe de rellenar todos los campos para insertar a un cliente.");
    	}
    	
    	
    }
    
    @FXML
    void InsertarVehiculo(ActionEvent event) {
    	vehiculo=null;
    	String marca=tfVehiculoMarca.getText();
    	String modelo=tfVehiculoModelo.getText();
    	String matricula=tfVehiculoMatricula.getText();
    	int cilindrada=0;
    	int plazas=0;
    	int pma=0;
    	
    	if(!marca.trim().isEmpty() && !modelo.trim().isEmpty() && !matricula.trim().isEmpty()) {
    		
    		try {
    			if(rbTurismo.isSelected()) {
    		    	cilindrada=Integer.parseInt(tfVehiculoCilindrada.getText());
    				vehiculo=new Turismo(marca,modelo,cilindrada,matricula);
    					
    			}else if(rbAutobus.isSelected()) {
    		    	pma=Integer.parseInt(tfVehiculoPma.getText());
    				vehiculo=new Autobus(marca,modelo,plazas,matricula);
    					
    			}else if(rbFurgoneta.isSelected()){
    		    	plazas=Integer.parseInt(tfVehiculoPlazas.getText());
    		    	pma=Integer.parseInt(tfVehiculoPma.getText());
    				vehiculo=new Furgoneta(marca, modelo, plazas, pma, matricula);
    			}
    			controladorMVC.insertar(vehiculo);
				Dialogos.mostrarDialogoInformacion("Insertar vehículo", "Se ha insertado el vehículo correctamente");
				salir(btnInsertarVehiculo);
				} catch (OperationNotSupportedException | IllegalArgumentException  e) {
//				Quiero mandar un mensaje cuando salta la excepción NumberFormatException pero no sé como
				Dialogos.mostrarDialogoError("Insertar vehículo",e.getMessage());
					
				}
    	}else {
    			Dialogos.mostrarDialogoInformacion("Insertar vehículo","Debe seleccionar un tipo de vehiculo y rellenar los campos habilitados");
    	}
    }
    
    
    @FXML
    void InsertarAlquiler(ActionEvent event) {
    	cliente=null;
    	vehiculo=null;
    	alquiler=null;
    	String dniCliente=tfAlquilerCliente.getText();
    	String matriculaVehiculo=tfAlquilerVehiculo.getText();
    	LocalDate fechaAlquiler=dpInsertarAlquiler.getValue();

    	if(!dniCliente.trim().isEmpty() && !matriculaVehiculo.trim().isEmpty() && fechaAlquiler!=null) {

    		try {
    			cliente=Cliente.getClienteConDni(dniCliente);
        		cliente=controladorMVC.buscar(cliente);
        		vehiculo= Vehiculo.getVehiculoConMatricula(matriculaVehiculo);
        		vehiculo=controladorMVC.buscar(vehiculo);
        		alquiler=new Alquiler(cliente,vehiculo,fechaAlquiler);
				controladorMVC.insertar(alquiler);
				Dialogos.mostrarDialogoInformacion("Insertar Alquiler", "Se ha insertado el alquiler correctamente");
				salir(btnInsertarAlquiler);
				
			} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
				Dialogos.mostrarDialogoError("Insertar alquiler",e.getMessage());
			}
    	}else {
    		Dialogos.mostrarDialogoInformacion("Insertar cliente", "Debe de rellenar todos los campos para insertar el alquiler");
    	}
    	
    }
     
    @FXML
    void eleccionAutobus(ActionEvent event) {
    	deshabilidarTiposVehiculo();
    	lVehiculoPlazas.setDisable(false);
    	tfVehiculoPlazas.setDisable(false);   	 
    }

    @FXML
    void eleccionFurgoneta(ActionEvent event) {
    	deshabilidarTiposVehiculo();
    	lVehiculoPlazas.setDisable(false);
    	tfVehiculoPlazas.setDisable(false);
    	lVehiculoPma.setDisable(false);
    	tfVehiculoPma.setDisable(false);
    }

    @FXML
    void eleccionTurismo(ActionEvent event) {
    	deshabilidarTiposVehiculo();
    	lVehiculoCilindrada.setDisable(false);
    	tfVehiculoCilindrada.setDisable(false);
    }

//  MÉTODOS CREADOS PARA REUTILIZAR Y CLARIFICAR CÓDIGO
    
    private void salir(Button btn) {
    	Stage stage=(Stage) btn.getScene().getWindow();
    	stage.close();
    }
    
	private void mostrarEstadisticas(LocalDate fecha) {
		Map<TipoVehiculo, Integer> estadistica=new EnumMap<>(TipoVehiculo.class);
		for(TipoVehiculo tipo:TipoVehiculo.values()) {
			estadistica.put(tipo, 0);
		}
		LocalDate inicioMes=fecha.withDayOfMonth(1);
		LocalDate finMes=inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());
		List<Alquiler> alquileres=new ArrayList<>(controladorMVC.getAlquileres());
		
		for(Alquiler a:alquileres) {
			if((a.getFechaAlquiler().isAfter(inicioMes) || a.getFechaAlquiler().isEqual(inicioMes)) && (a.getFechaAlquiler().isBefore(finMes) || a.getFechaAlquiler().isEqual(finMes))) {
				TipoVehiculo tipoVehiculo=TipoVehiculo.get(a.getVehiculo());
				estadistica.put(tipoVehiculo, estadistica.get(tipoVehiculo)+1);
			}
		}
		labelEstadisticaTurismo.setText(estadistica.get(TipoVehiculo.TURISMO)+"");
		labelEstadisticaFurgoneta.setText(estadistica.get(TipoVehiculo.FURGONETA)+"");
		labelEstadisticaAutobuses.setText(estadistica.get(TipoVehiculo.AUTOBUS)+"");
	}
    
    private void deshabilidarTiposVehiculo() {
    	lVehiculoCilindrada.setDisable(true);
    	tfVehiculoCilindrada.setDisable(true);
    	tfVehiculoCilindrada.clear();
    	
    	lVehiculoPlazas.setDisable(true);
    	tfVehiculoPlazas.setDisable(true);
    	tfVehiculoPlazas.clear();
    	
    	lVehiculoPma.setDisable(true);
    	tfVehiculoPma.setDisable(true);
    	tfVehiculoPma.clear();
    	
    }



}

