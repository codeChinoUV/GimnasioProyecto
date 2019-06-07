/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.Membresia;
import modelo.Promocion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import persistencia.PromocionDAO;
import persistencia.PromocionDatos;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class RealizarPromocionController implements Initializable {

  @FXML
  private TableView<Promocion> tPromocion;
  @FXML
  private TableColumn<Promocion, String> tcNombre;
  @FXML
  private TableColumn<Promocion, String> tcDescuento;
  @FXML
  private JFXButton bAceptar;
  @FXML
  private JFXButton bCancelar;

  private Stage stagePrincipal;
  private ObservableList<Promocion> promocionesSeleccionadas = null;
  private ObservableList<Promocion> promocionesDisponibles = null;

  public ObservableList<Promocion> getPromocionesSeleccionadas() {
    return promocionesSeleccionadas;
  }

  public void setPromocionesSeleccionadas(ObservableList<Promocion> promocionesSeleccionadas) {
    this.promocionesSeleccionadas = promocionesSeleccionadas;
  }

  public Stage getStagePrincipal() {
    return stagePrincipal;
  }

  public void setStagePrincipal(Stage stagePrincipal) {
    this.stagePrincipal = stagePrincipal;
  }
  
  private void recuperarPromocionesActivas() {
    promocionesDisponibles = FXCollections.observableArrayList();
    PromocionDAO persistenciaPromociones = new PromocionDatos();
    List<Promocion> promocionesRecuperadas = persistenciaPromociones.recuperarPromocionesVigentes();
    for (Promocion promocion : promocionesRecuperadas) {
      promocionesDisponibles.add(promocion);
    }
  }

  private void cargarTabla() {
    tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    //tcDescuento.setCellValueFactory(new PropertyValueFactory<>("montoDescuento"));
    tcDescuento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Promocion, String>, ObservableValue<String>>() {
      public ObservableValue<String> call(TableColumn.CellDataFeatures<Promocion, String> p) {
        double descuento = p.getValue().getMontoDescuento() * 100;
        String descuentoString = "" + descuento+ "%"; 
        return new SimpleStringProperty(descuentoString);
      }
    }
        );
    recuperarPromocionesActivas();
    tPromocion.setItems(promocionesDisponibles);
  }

  @FXML
  public void seleccionar(){
    promocionesSeleccionadas = tPromocion.getSelectionModel().getSelectedItems();
    if(promocionesSeleccionadas.size() > 1){
      //Mensaje solo una
    }else{
      stagePrincipal.close();
    }
  }
  
  @FXML 
  public void cancelar(){
    stagePrincipal.close();
  }
  
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cargarTabla();
  }

}
