package com.uv.gimnasio;

import controlador.ConsultarClientesController;
import controlador.ModificarDatosController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Cliente;

public class MainApp extends Application {

  private Stage stagePrincipal;
  private AnchorPane rootPane;

  public void cambiarVistaAModificarCliente(Cliente clienteAModificar){
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ModificarDatos.fxml"));
    try {
      rootPane = (AnchorPane) loader.load();
    } catch (IOException ex) {
      System.out.println("Algo salio mal al cambiar de ventana");
    }
    Scene scene = new Scene(rootPane);
    scene.getStylesheets().add("/styles/ModificarDatos.css");
    stagePrincipal.setTitle("Modificar clientes");
    stagePrincipal.setScene(scene);
    ModificarDatosController controlador = loader.getController();
    controlador.setStagePrincipal(stagePrincipal);
    controlador.setAplicacionPrincipal(this);
    controlador.setClienteAModificar(clienteAModificar);
    controlador.inicializar();
    stagePrincipal.show();
  }

  public void cambiarVistaAConsultarCliente() {
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ConsultarClientes.fxml"));
    try {
      rootPane = (AnchorPane) loader.load();
    } catch (IOException ex) {
      System.out.println("Algo salio mal al cambiar de ventana");
    }
    Scene scene = new Scene(rootPane);
    scene.getStylesheets().add("/styles/ConsultarClientes.css");
    stagePrincipal.setTitle("Consultar clientes");
    stagePrincipal.setScene(scene);
    ConsultarClientesController controlador = loader.getController();
    controlador.setStagePrincipal(stagePrincipal);
    controlador.setAplicacionPrincipal(this);
    stagePrincipal.show();
  }

  @Override
  public void start(Stage stage) throws Exception {
    stagePrincipal = stage;
    cambiarVistaAConsultarCliente();
  }

  /**
   * The main() method is ignored in correctly deployed JavaFX application. main() serves only as
   * fallback in case the application can not be launched through deployment artifacts, e.g., in
   * IDEs with limited FX support. NetBeans ignores main().
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
