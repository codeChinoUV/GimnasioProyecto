package com.uv.gimnasio;

import controlador.AdministrarPromocionesController;
import controlador.AgregarPromocionController;
import controlador.ConsultarClientesController;
import controlador.ConsultarProblemaController;
import controlador.VentanaPrincipalController;
import controlador.ModificarDatosController;
import controlador.InscribirClienteController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

  public void mostrarVentanaInscribirCliente() throws IOException {
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/InscribirCliente.fxml"));
    rootPane = (AnchorPane) loader.load();
    Scene scene = new Scene(rootPane);
    //scene.getStylesheets().add("/styles/Styles.css");
    stagePrincipal.setTitle("Inscribir cliente");
    stagePrincipal.setScene(scene);
    InscribirClienteController controlador = loader.getController();
    controlador.setStagePrincipal(stagePrincipal);
    controlador.setAplicacionPrincipal(this);
    stagePrincipal.show();
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
  
  public void mostrarModificarCliente(Cliente cleinte) throws Exception {
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ModificarDatos.fxml"));
    rootPane = (AnchorPane) loader.load();
    Scene scene = new Scene(rootPane);
    stagePrincipal.setTitle("Ventana Principal");
    stagePrincipal.setScene(scene);
    ModificarDatosController controller = loader.getController();
    controller.setAplicacionPrincipal(this);
    controller.setStagePrincipal(stagePrincipal);
    controller.setClienteAModificar(cleinte);
    controller.inicializar();
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
    controller.setAplicacionPrincipal(this);
    controller.setStagePrincipal(stagePrincipal);
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

  public void mostrarVentanaConsultarProblema() throws IOException{
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ConsultarProblema.fxml"));
    rootPane = (AnchorPane) loader.load();
    Scene scene = new Scene(rootPane);
    stagePrincipal.setTitle("Registro de promociones");
    stagePrincipal.setScene(scene);
    ConsultarProblemaController controller = loader.getController();
    controller.setAplicacionPrincipal(this);
    stagePrincipal.show();
    stagePrincipal.centerOnScreen();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
  
  

}
