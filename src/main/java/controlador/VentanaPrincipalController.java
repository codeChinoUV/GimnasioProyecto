/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXButton;
import com.uv.gimnasio.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author irvin
 */
public class VentanaPrincipalController implements Initializable {

  @FXML
  private JFXButton btConsultarCliente;

  @FXML
  private JFXButton btnAdministrarProm;

  @FXML
  private JFXButton bInscribirCliente;

  @FXML
  private JFXButton bConsultarCliente;

  MainApp programaPrincipal;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  public void ventanaConsultaClientes(ActionEvent event) {
    try {
      programaPrincipal.mostrarVentanaConsultarClientes();
    } catch (IOException ex) {
      ex.printStackTrace();
      Logger.getLogger(ConsultarClientesController.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  @FXML
  public void ventanaPromociones(ActionEvent event) {
    try {

      programaPrincipal.mostrarVentanaAdministrarPromocion();
    } catch (IOException ex) {
      ex.printStackTrace();
      Logger.getLogger(AdministrarPromocionesController.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
  
  @FXML
  public void ventanaInscribirCliente(){
    try {
      programaPrincipal.mostrarVentanaInscribirCliente();
    } catch (IOException ex) {
      ex.printStackTrace();
      Logger.getLogger(AdministrarPromocionesController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  public void ventanaConsultarProblema(){
    try {

      programaPrincipal.mostrarVentanaConsultarProblema();
    } catch (IOException ex) {
      ex.printStackTrace();
      Logger.getLogger(AdministrarPromocionesController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void setProgramaPrincipal(MainApp programaPrincipal) {
    this.programaPrincipal = programaPrincipal;
  }
}
