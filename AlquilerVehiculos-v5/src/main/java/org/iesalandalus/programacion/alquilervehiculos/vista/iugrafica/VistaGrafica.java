package org.iesalandalus.programacion.alquilervehiculos.vista.iugrafica;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.iugrafica.controladoresvistas.ControladorEscenaPrincipal;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VistaGrafica extends Vista {

	private static VistaGrafica instancia=null;
	
    public VistaGrafica()
    {
        if (instancia != null) 
        {
            controladorMVC = instancia.controladorMVC;
        } 
        else 
        {
            instancia = this;
        }
    }
	

	@Override
	public void start(Stage primaryStage) {
		try {
//			Escenario Principal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("vistas/EscenaPrincipal.fxml"));
			Parent raiz=loader.load();
			ControladorEscenaPrincipal controlador=loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setListaCliente(controladorMVC.getClientes());
			controlador.setListaVehiculo(controladorMVC.getVehiculo());
			controlador.setListaAlquiler(controladorMVC.getAlquileres());
			
			Scene escena =new Scene(raiz);
			primaryStage.setOnCloseRequest(e -> confirmarSalida(primaryStage, e));
			primaryStage.setTitle("Administración de alquiler de coches");
			primaryStage.setScene(escena);
			primaryStage.setResizable(false);
			primaryStage.show();
					
			
//			ventana vacía
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("iugVentanas.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void confirmarSalida(Stage primaryStage, WindowEvent e) {
		if (org.iesalandalus.programacion.alquilervehiculos.vista.iugrafica.utilidades.Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", primaryStage)) {
			this.controladorMVC.terminar();
			primaryStage.close();
		}
		else
			e.consume();	
	}
	@Override
	public void comenzar() {
		launch(this.getClass());
		
	}
	@Override
	public void setControlador(IControlador controlador) {
		if(controlador==null) {
			throw new NullPointerException("No puedes pasar un controlador nulo.");
		}
		this.controladorMVC=controlador;
	}

	@Override
	public void terminar() {
		controladorMVC.terminar();
		
	}


	
}
