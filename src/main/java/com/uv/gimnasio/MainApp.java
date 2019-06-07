package com.uv.gimnasio;

<<<<<<< HEAD
import controlador.AdministrarPromocionesController;
import controlador.AgregarPromocionController;
import controlador.ConsultarClientesController;

import controlador.VentanaPrincipalController;
import controlador.ModificarDatosController;
=======
import controlador.InscribirClienteController;
>>>>>>> Bruno
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
<<<<<<< HEAD
import modelo.Cliente;

public class MainApp extends Application {

  private Stage stagePrincipal;
  private AnchorPane rootPane;

  @Override
  public void start(Stage stage) throws Exception {
    this.stagePrincipal = stage;
    stage.setResizable(false);
    mostrarVentanaPrincipal();
  }

  public void mostrarVentanaPrincipal() throws Exception {
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

  public void mostrarVentanaConsultarClientes() throws IOException {
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ConsultarClientes.fxml"));
    rootPane = (AnchorPane) loader.load();
    Scene scene = new Scene(rootPane);
    stagePrincipal.setTitle("Consultar clientes");
    stagePrincipal.setScene(scene);
    ConsultarClientesController controller = loader.getController();
    controller.setProgramaPrincipal(this);
    stagePrincipal.show();
  }

  public void mostrarVentanaAdministrarPromocion() throws IOException {
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

  public void mostrarVentanaRegistroPromocion() throws IOException {
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

=======

public class MainApp extends Application {

   private AnchorPane rootPane;
    private Stage stagePrincipal;
    
  @Override
  public void start(Stage stage) throws Exception {
    stagePrincipal = stage;
    try{
      cargarInscribirCliente();
    }catch(IOException e){
      e.printStackTrace();
      System.out.println("MainApp: IOEX: start");
    }
  }

  public void cargarInscribirCliente() throws IOException {
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/InscribirCliente.fxml"));
    rootPane = (AnchorPane) loader.load();
    Scene scene = new Scene(rootPane);
    //scene.getStylesheets().add("/styles/Styles.css");
    stagePrincipal.setTitle("Agenda");
    stagePrincipal.setScene(scene);
    InscribirClienteController controlador = loader.getController();
    controlador.setStagePrincipal(stagePrincipal);
    controlador.setAplicacionPrincipal(this);
    stagePrincipal.show();
  }

  /**
   * The main() method is ignored in correctly deployed JavaFX application. main() serves only as
   * fallback in case the application can not be launched through deployment artifacts, e.g., in
   * IDEs with limited FX support. NetBeans ignores main().
   *
   * @param args the command line arguments
   */
>>>>>>> Bruno
  public static void main(String[] args) {
    launch(args);
  }

}
