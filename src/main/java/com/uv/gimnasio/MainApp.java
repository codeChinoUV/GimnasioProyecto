package com.uv.gimnasio;

import controlador.AdministrarPromocionesController;
import controlador.AgregarPromocionController;
import controlador.ConsultarClientesController;

import controlador.VentanaPrincipalController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainApp extends Application {
    private Stage stagePrincipal;
    private AnchorPane rootPane;

    @Override
    public void start(Stage stage) throws Exception {
      this.stagePrincipal = stage;
      stage.setResizable(false);
      mostrarVentanaPrincipal();  
    }
    
   public void mostrarVentanaPrincipal() throws Exception{
     FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/VentanaPrincipal.fxml"));
     rootPane = (AnchorPane) loader.load();
     Scene scene = new Scene(rootPane);
     stagePrincipal.setTitle("Ventana Principal");
     stagePrincipal.setScene(scene);
     VentanaPrincipalController controller = loader.getController();
     controller.setProgramaPrincipal(this);
     stagePrincipal.show();
     stagePrincipal.centerOnScreen();
   }
    
   public void mostrarVentanaConsultarClientes() throws IOException{
     FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ConsultarClientes.fxml"));
     rootPane = (AnchorPane) loader.load();
     Scene scene = new Scene(rootPane);
     stagePrincipal.setTitle("Consultar clientes");
     stagePrincipal.setScene(scene);
     ConsultarClientesController controller = loader.getController();
     controller.setProgramaPrincipal(this);
     stagePrincipal.show();
   }
    
   public void mostrarVentanaAdministrarPromocion() throws IOException{
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/administrarPromociones.fxml"));
        rootPane = (AnchorPane) loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Administracion de promociones");
        stagePrincipal.setScene(scene);
        AdministrarPromocionesController controller = loader.getController();
        controller.setProgramaPrincipal(this);
        stagePrincipal.show();
        stagePrincipal.centerOnScreen();
    }
   public void mostrarVentanaRegistroPromocion() throws IOException{
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/agregarPromocion.fxml"));
        rootPane = (AnchorPane) loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Registro de promociones");
        stagePrincipal.setScene(scene);
        AgregarPromocionController controller = loader.getController();
        controller.setProgramaPrincipal(this);
        stagePrincipal.show();
        stagePrincipal.centerOnScreen();
   }

    public static void main(String[] args) {
        launch(args);
        
    }

}
