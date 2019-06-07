package com.uv.gimnasio;

import controlador.InscribirClienteController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
  public static void main(String[] args) {
    launch(args);
  }

}
