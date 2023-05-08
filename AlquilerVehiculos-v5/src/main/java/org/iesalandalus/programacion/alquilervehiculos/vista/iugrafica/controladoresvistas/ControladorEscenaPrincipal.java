package org.iesalandalus.programacion.alquilervehiculos.vista.iugrafica.controladoresvistas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.*;
import org.iesalandalus.programacion.alquilervehiculos.vista.TipoVehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.iugrafica.utilidades.Dialogos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorEscenaPrincipal {
	
//	DECLARACIÓN
	
	private IControlador controladorMVC;
	
//	SETTERS
	
    public void setControladorMVC(IControlador controlador) {           
        controladorMVC = controlador;        
    }
    
	public void setListaCliente(List<Cliente> clientes) {
		obsClientes.setAll(clientes);
	}
	
	public void setListaVehiculo(List<Vehiculo> vehiculos) {
		obsVehiculos.setAll(vehiculos);
	}
	
	public void setListaAlquiler(List<Alquiler> alquileres) {
		obsAlquileres.setAll(alquileres);
	}
	
	@FXML
	private void initialize() {
		
//		INICIALIZAR TABLAS
		
		inicializarTClientes();
		
		inicializarTVehiculos();
		
		inicializarTAlquileres();
	}
	
//	BOTONES REINICIAR TABLAS Y GUARDAR
	
    @FXML
    private Button btnGuardar;
    
    @FXML
    private Button btnReiniciarTablas;
    
//    PANEL CLIENTES
    
    @FXML
    private Button btnCBorrar;
    @FXML
    private Button btnCInsertar;
    @FXML
    private Button btnCModificar;
    @FXML
    private TextField tfCDni;
        
//    TABLA CLIENTES
    
    @FXML
    private TableView<Cliente> tvClientes;
    @FXML
    private TableColumn<Cliente, String> tcCDni;
    @FXML
    private TableColumn<Cliente, String> tcCNombre;
    @FXML
    private TableColumn<Cliente, String> tcCTelefono;
    
    private ObservableList<Cliente> obsClientes=FXCollections.observableArrayList();
    private ObservableList<Cliente> auxObsClientes=FXCollections.observableArrayList();

    
//    PANEL VEHICULOS
    
    @FXML
    private Button btnVBorrar;
    @FXML
    private Button btnVInsertar;
    @FXML
    private Button btnVMostrarEstadisticas;   
    @FXML
    private TextField tfVMatricula;
    
//    TABLA VEHICULOS
    
    @FXML
    private TableView<Vehiculo> tvVehiculos;
    @FXML
    private TableColumn<Vehiculo, Integer> tcVCilindrada;
    @FXML
    private TableColumn<Vehiculo, String> tcVMatricula;
    @FXML
    private TableColumn<Vehiculo, String> tcVModelo;
    @FXML
    private TableColumn<Vehiculo, Integer> tcVPlazas;
    @FXML
    private TableColumn<Vehiculo, Integer> tcVPma;
    @FXML
    private TableColumn<Vehiculo, String> tcVMarca;
    
    private ObservableList<Vehiculo> obsVehiculos=FXCollections.observableArrayList();
    private ObservableList<Vehiculo> auxObsVehiculos=FXCollections.observableArrayList();
 
    
//    PANEL ALQUILERES
    
    @FXML
    private Button btnABorrar;
    @FXML
    private Button btnADevolver;
    @FXML
    private Button btnAInsertar;    
    @FXML
    private Button btnABuscar;
    @FXML
    private DatePicker dpAFecha;  
    
//  TABLA ALQUILERES
    
    @FXML
    private TableColumn<Alquiler, String> tcACliente;
    @FXML
    private TableColumn<Alquiler, LocalDate> tcAFechaAlquiler;
    @FXML
    private TableColumn<Alquiler, LocalDate> tcAFechaDevolucion;
    @FXML
    private TableColumn<Alquiler, String> tcAVehiculo;
    @FXML
    private TableColumn<Alquiler, Integer> tcAPrecio;
    @FXML
    private TableView<Alquiler> tvAlquileres;
    
    private ObservableList<Alquiler> obsAlquileres=FXCollections.observableArrayList();
    private ObservableList<Alquiler> auxObsAlquileres=FXCollections.observableArrayList();
    
//    FUNCIÓN BUSCAR
    
    @FXML
    void buscarClientes(KeyEvent event) {
    	String filtroCliente=tfCDni.getText();
    	if(filtroCliente.trim().isEmpty()) {
    		tvClientes.setItems(obsClientes);
    	}else {
    		auxObsClientes.clear();
    		for (Cliente cliente : obsClientes) {
				if(cliente.getDni().toLowerCase().contains(filtroCliente.toLowerCase())) {
					auxObsClientes.add(cliente);
				}
			}
    		tvClientes.setItems(auxObsClientes);
    	}
    }

    @FXML
    void buscarVehiculos(KeyEvent event) {
    	String filtroVehiculo=tfVMatricula.getText();
    	if(filtroVehiculo.trim().isEmpty()) {
    		tvVehiculos.setItems(obsVehiculos);
    	}else {
    		auxObsVehiculos.clear();
    		for (Vehiculo vehiculo : obsVehiculos) {
				if(vehiculo.getMatricula().toLowerCase().contains(filtroVehiculo.toLowerCase())) {
					auxObsVehiculos.add(vehiculo);
				}
			}
    		tvVehiculos.setItems(auxObsVehiculos);
    	}
    }
    
    @FXML
    void BuscarAlquiler(ActionEvent event) {
    	LocalDate filtroAlquiler=dpAFecha.getValue();
		if(filtroAlquiler==null) {
			tvAlquileres.setItems(obsAlquileres);
		}else {
			auxObsAlquileres.clear();
			for (Alquiler alquiler : obsAlquileres) {
				if(alquiler.getFechaAlquiler().equals(filtroAlquiler)) {
					
					auxObsAlquileres.add(alquiler);
				}
			}
			tvAlquileres.setItems(auxObsAlquileres);
			}
    }
    
//    FUNCIÓN INSERTAR
    

    @FXML
    void insertarCliente(ActionEvent event) {
    	lanzadorEscenaSecundaria("Clientes", "Insertar cliente");
        restablecerTablas();
    }
    
    @FXML
    void insertarVehiculo(ActionEvent event) {

    	lanzadorEscenaSecundaria("Vehiculos", "Insertar vehiculo");
    	restablecerTablas();

            
    }
    @FXML
    void insertarAlquiler(ActionEvent event) {
    	lanzadorEscenaSecundaria("Alquileres", "Insertar alquiler");
    	restablecerTablas();

    }
    
//    FUNCIÓN BORRAR
    
    @FXML
    void borrarAlquiler(ActionEvent event) {
    	Alquiler alquilerABorrar=tvAlquileres.getSelectionModel().getSelectedItem();
    	if(alquilerABorrar!=null) {
    		try {
    			if(Dialogos.mostrarDialogoConfirmacion("Borrar alquiler", "Esta seguro de que deséa borrar el alquiler: "+alquilerABorrar, null)) {
    				controladorMVC.borrar(alquilerABorrar);
    				restablecerTablas();
    			}
			} catch (OperationNotSupportedException e) {
				Dialogos.mostrarDialogoError("Borrar alquiler", e.getMessage());
			}
    	}else {
    		Dialogos.mostrarDialogoError("Borrar alquiler", "Debes de seleccionar un alquiler de la tabla para borrarlo");
    	}
    	
    }
    
    @FXML
    void borrarCliente(ActionEvent event) {
    	Cliente clienteABorrar=tvClientes.getSelectionModel().getSelectedItem();
    	if(clienteABorrar!=null) {
    		try {
    			if(Dialogos.mostrarDialogoConfirmacion("Borrar cliente", "Esta seguro de que deséa borrar el Cliente: "+clienteABorrar, null)) {
    				controladorMVC.borrar(clienteABorrar);
    				restablecerTablas();
    			}
			} catch (OperationNotSupportedException e) {
				Dialogos.mostrarDialogoError("Borrar cliente", e.getMessage());
			}
    	}else {
    		Dialogos.mostrarDialogoError("Borrar cliente", "Debes de seleccionar un cliente de la tabla para borrarlo");
    	}
    	
    }

    @FXML
    void borrarVehiculo(ActionEvent event) {
    	Vehiculo vehiculoABorrar=tvVehiculos.getSelectionModel().getSelectedItem();
    	if(vehiculoABorrar!=null) {
    		try {
    			if(Dialogos.mostrarDialogoConfirmacion("Borrar vehículo", "Esta seguro de que deséa borrar el vehículo: "+vehiculoABorrar, null)) {
    				controladorMVC.borrar(vehiculoABorrar);
    				restablecerTablas();
    			}
			} catch (OperationNotSupportedException e) {
				Dialogos.mostrarDialogoError("Borrar vehículo", e.getMessage());
			}
    	}else {
    		Dialogos.mostrarDialogoError("Borrar vehículo", "Debes de seleccionar un vehiculo de la tabla para borrarlo");
    	}
    	
    }
    
//    FUNCIONES SECUNDARIAS
    
//    CLIENTES
    
    @FXML
    void modificarCliente(ActionEvent event) {
    	Cliente cliente=tvClientes.getSelectionModel().getSelectedItem();
    	if(cliente!=null) {
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("../vistas/EscenaSecundaria.fxml"));
            ControladorEscenaSecundaria controlador=null;
    		try {
    	        Parent raiz;
    			raiz = loader.load();     
    	        controlador=loader.getController();
    	        controlador.setControladorMVC(controladorMVC);
    	        controlador.setAccion("Modificar");
    	        controlador.setCliente(cliente);
    	        controlador.setTfClienteMNombre(cliente.getNombre());
    	        controlador.setTfClienteMTelefono(cliente.getTelefono());
    	        Scene escena=new Scene(raiz);
    	        
    	        Stage escenario=new Stage();
    	        escenario.initModality(Modality.APPLICATION_MODAL);
    	        escenario.setScene(escena);
    	        escenario.setTitle("Modificar cliente");
    	        escenario.showAndWait();
            	restablecerTablas();
    		} catch (IOException e) {
    			Dialogos.mostrarDialogoError("Modificar Cliente", e.getMessage());
    		}
    	}else {
    		Dialogos.mostrarDialogoError("Modificar cliente", "Debe seleccionar un cliente de la lista para modificarlo");
    	}

    }
    
//    ALQUILERES
    
    @FXML
    void devolverAlquiler(ActionEvent event) {
    	Alquiler alquiler=tvAlquileres.getSelectionModel().getSelectedItem();
    	if(alquiler!=null && alquiler.getFechaDevolucion()==null) {
	    	FXMLLoader loader=new FXMLLoader(getClass().getResource("../vistas/EscenaSecundaria.fxml"));
	        ControladorEscenaSecundaria controlador=null;
			try {
		        Parent raiz;
				raiz = loader.load();     
		        controlador=loader.getController();
		        controlador.setControladorMVC(controladorMVC);
		        controlador.setAlquiler(alquiler);
		        controlador.setAccion("Devolver");
		        Scene escena=new Scene(raiz);
		        
		        Stage escenario=new Stage();
		        escenario.initModality(Modality.APPLICATION_MODAL);
		        escenario.setScene(escena);
		        escenario.setTitle("Devolver alquiler");
		        escenario.showAndWait();
		        
		        restablecerTablas();
			} catch (IOException | IllegalArgumentException e) {
				Dialogos.mostrarDialogoError("Delvolver alquiler", e.getMessage());
			}
    	}else {
    		Dialogos.mostrarDialogoError("Devolver", "Debe seleccionar un alquiler de la tabla para devolver teniendo en cuenta que debe estar sin devolver");
    	}

    }
    
//    VEHICULOS
    
    @FXML
    void mostrarEstadisticas(ActionEvent event) {
    	lanzadorEscenaSecundaria("MostrarEstadisticas", "Mostrar estadisticas");
    }

   

//    LISTAR
    
    @FXML
    void seleccionarAlquiler(MouseEvent event) {

    	try {
        	Alquiler alquilerSeleccionado=new Alquiler(tvAlquileres.getSelectionModel().getSelectedItem());
	    	if(alquilerSeleccionado!=null) {
	        	restablecerTablas();
	    		obsClientes.clear();
	    		obsClientes.add(alquilerSeleccionado.getCliente());
	    		tvClientes.setItems(obsClientes);
	    		tvClientes.refresh();
	    		
	    		obsVehiculos.clear();
	    		obsVehiculos.add(alquilerSeleccionado.getVehiculo());
	    		tvVehiculos.setItems(obsVehiculos);
	    		tvVehiculos.refresh();
	    	}
		} catch (NullPointerException e) {
			
		}
    }

    @FXML
    void seleccionarCliente(MouseEvent event) {
		try {
		  	Cliente clienteSeleccionado=new Cliente(tvClientes.getSelectionModel().getSelectedItem());
	    	System.out.println(clienteSeleccionado);
	    	if(clienteSeleccionado!=null) {
	        	restablecerTablas();
	    		List<Alquiler> alquileresCliente=new ArrayList<>(controladorMVC.getAlquileres(clienteSeleccionado));
	    		actualizaTablaAlquileres(alquileresCliente);
    	}
		} catch (NullPointerException e) {
		}

    }


	@FXML
    void seleccionarVehiculo(MouseEvent event) {
		try {
			Vehiculo vehiculoSeleccionado=tvVehiculos.getSelectionModel().getSelectedItem();
	    	if(vehiculoSeleccionado!=null) {
	    		restablecerTablas();
		    	if(TipoVehiculo.get(vehiculoSeleccionado).equals(TipoVehiculo.TURISMO)) {
		    		vehiculoSeleccionado=new Turismo((Turismo) vehiculoSeleccionado);
		    	}else if(TipoVehiculo.get(vehiculoSeleccionado).equals(TipoVehiculo.FURGONETA)) {
		    		vehiculoSeleccionado=new Furgoneta((Furgoneta) vehiculoSeleccionado);
		    	}else if(TipoVehiculo.get(vehiculoSeleccionado).equals(TipoVehiculo.AUTOBUS)) {
		    		vehiculoSeleccionado=new Autobus((Autobus) vehiculoSeleccionado);
		    	}
				List<Alquiler> alquileresVehiculo=new ArrayList<>(controladorMVC.getAlquileres(vehiculoSeleccionado));
				actualizaTablaAlquileres(alquileresVehiculo);
				
	    	}
		} catch (NullPointerException e) {
		}
    }

//	GUARDAR Y REINICIAR TABLAS
	
    @FXML
    void guardar(ActionEvent event) {
    	controladorMVC.guardar();
    }
    @FXML
    void reiniciarTablas(ActionEvent event) {
    	restablecerTablas();
    }
    
//    MÉTODOS CREADOS PARA REUTILIZAR Y CLARIFICAR CÓDIGO
    
	private void inicializarTAlquileres() {
		tcAVehiculo.setCellValueFactory(alquiler -> new SimpleObjectProperty<String>(alquiler.getValue().getVehiculo().getMatricula()));
		tcACliente.setCellValueFactory(alquiler -> new SimpleObjectProperty<String>(alquiler.getValue().getCliente().getDni()));
		tcAFechaAlquiler.setCellValueFactory(alquiler-> new SimpleObjectProperty<>(alquiler.getValue().getFechaAlquiler()));
		tcAFechaDevolucion.setCellValueFactory(alquiler-> new SimpleObjectProperty<>(alquiler.getValue().getFechaDevolucion()));
		tcAPrecio.setCellValueFactory(alquiler->new SimpleObjectProperty<Integer>(alquiler.getValue().getPrecio()));
		tvAlquileres.setItems(obsAlquileres);
	}

	private void inicializarTVehiculos() {
		tcVMarca.setCellValueFactory(vehiculo ->new SimpleStringProperty(vehiculo.getValue().getMarca()));
		tcVModelo.setCellValueFactory(vehiculo ->new SimpleStringProperty(vehiculo.getValue().getModelo()));
		tcVMatricula.setCellValueFactory(vehiculo ->new SimpleStringProperty(vehiculo.getValue().getMatricula()));
		tcVCilindrada.setCellValueFactory(vehiculo -> {
		    if (vehiculo.getValue() instanceof Turismo) {
		        return new SimpleIntegerProperty(((Turismo) vehiculo.getValue()).getCilindrada()).asObject();
		    } else {
		        return null;
		    }
		});
		tcVPlazas.setCellValueFactory(vehiculo -> {
		    if (vehiculo.getValue() instanceof Autobus) {
		        return new SimpleIntegerProperty(((Autobus) vehiculo.getValue()).getPlazas()).asObject();
		    }else if(vehiculo.getValue() instanceof Furgoneta) {
		        return new SimpleIntegerProperty(((Furgoneta) vehiculo.getValue()).getPlazas()).asObject();
		    }else {
		        return null;
		    }
		});
		tcVPma.setCellValueFactory(vehiculo -> {
		    if (vehiculo.getValue() instanceof Furgoneta) {
		        return new SimpleIntegerProperty(((Furgoneta) vehiculo.getValue()).getPma()).asObject();
		    } else {
		        return null;
		    }
		});

		tvVehiculos.setItems(obsVehiculos);
	}

	private void inicializarTClientes() {
		tcCNombre.setCellValueFactory(cliente ->new SimpleStringProperty(cliente.getValue().getNombre()));
		tcCDni.setCellValueFactory(cliente->new SimpleStringProperty(cliente.getValue().getDni()));
		tcCTelefono.setCellValueFactory(cliente->new SimpleStringProperty(cliente.getValue().getTelefono()));
		tvClientes.setItems(obsClientes);
	}
	
    private ControladorEscenaSecundaria lanzadorEscenaSecundaria(String accion,String titulo){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../vistas/EscenaSecundaria.fxml"));
        ControladorEscenaSecundaria controlador=null;
		try {
	        Parent raiz;
			raiz = loader.load();     
	        controlador=loader.getController();
	        controlador.setControladorMVC(controladorMVC);
	        controlador.setAccion(accion);
	        Scene escena=new Scene(raiz);
	        
	        Stage escenario=new Stage();
	        escenario.initModality(Modality.APPLICATION_MODAL);
	        escenario.setScene(escena);
	        escenario.setTitle(titulo);
	        escenario.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return controlador;
    }
    
    private void restablecerTablas() {
    	tfCDni.setText("");
    	obsAlquileres.setAll(controladorMVC.getAlquileres());
    	tvAlquileres.setItems(obsAlquileres);
    	tvAlquileres.refresh();
    	
    	tfVMatricula.setText("");
    	obsVehiculos.setAll(controladorMVC.getVehiculo());
    	tvVehiculos.setItems(obsVehiculos);
    	tvVehiculos.refresh();
    	
    	dpAFecha.setValue(null);
    	obsClientes.setAll(controladorMVC.getClientes());
    	tvClientes.setItems(obsClientes);
    	tvClientes.refresh();
    }
    
    
	private void actualizaTablaAlquileres(List<Alquiler> tablaAlquiler) {
		obsAlquileres.setAll(tablaAlquiler);
		tvAlquileres.setItems(obsAlquileres);
	}
}
