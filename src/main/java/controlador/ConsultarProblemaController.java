/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.uv.gimnasio.MainApp;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.Cliente;
import modelo.Problema;
import modelo.Promocion;
import persistencia.ClienteDAO;
import persistencia.ClienteDatos;
import persistencia.ProblemaDAO;
import persistencia.ProblemaDatos;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class ConsultarProblemaController implements Initializable {

  private Stage stagePrincipal = null;
  private MainApp aplicacionPrincipal = null;
  private ConsultarClientesController controller = new ConsultarClientesController();
  @FXML
  private JFXButton bBuscar;
  @FXML
  private TableView<Problema> tProblema;
  private ObservableList<Problema> problemas = null;
  @FXML
  private TableColumn<Problema, String> tcFecha;
  @FXML
  private TableColumn<Problema, String> tcNombre;
  @FXML
  private TableColumn<Problema, String> tcDescipcion;
  @FXML
  private TableColumn<Problema, String> tcEstado;
  @FXML
  private JFXButton bCerrar;
  @FXML
  private TableView<Problema> tClientes;
  private ObservableList<Problema> problemaDetallado = null;
  @FXML
  private TableColumn<Problema, String> tcFecha1;
  @FXML
  private TableColumn<Problema, String> tcCliente1;
  @FXML
  private TableColumn<Problema, String> tcProblema1;
  @FXML
  private TableColumn<Problema, String> tcDescripcion1;
  @FXML
  private TableColumn<Problema, String> tcEstado1;
  @FXML
  private JFXTextField tfBuscar;
  
  @FXML
  private JFXButton bSeleccionar;

  public Stage getStagePrincipal() {
    return stagePrincipal;
  }

  public void setStagePrincipal(Stage stagePrincipal) {
    this.stagePrincipal = stagePrincipal;
  }

  public MainApp getAplicacionPrincipal() {
    return aplicacionPrincipal;
  }

  public void setAplicacionPrincipal(MainApp aplicacionPrincipal) {
    this.aplicacionPrincipal = aplicacionPrincipal;
  }
  
  private void cargarProblemas(String nombre) {
    ProblemaDAO persistenciaProblemas = new ProblemaDatos();
    ClienteDAO persistenciaCliente = new ClienteDatos();
    problemas = FXCollections.observableArrayList();
    List<Problema> problemasRecuperados;
    if(!nombre.equals("")){
      problemasRecuperados = persistenciaProblemas.buscarProblema(nombre);
    }else{
      problemasRecuperados = persistenciaProblemas.recuperarProblemas();
    }
    for (Problema problema : problemasRecuperados) {
      Cliente clienteDelProblema = problema.getCliente();
      Cliente clienteCompleto = persistenciaCliente.recuperarClienteEspecifico(clienteDelProblema);
      problema.setCliente(clienteCompleto);
      problemas.add(problema);
    }
  }

  public void cargarTablaProblemaSencillo() {
    tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    tcDescipcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    
    tcEstado.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Problema, String>, ObservableValue<String>>() {
      public ObservableValue<String> call(TableColumn.CellDataFeatures<Problema, String> p) {
        String estado = "Mal";
        if(p.getValue().getEstado()){
          estado = "Revisado";
        }else{
          estado = "Sin revisar";
        }
        return new SimpleStringProperty(estado);
      }
        });
    
    tProblema.setItems(problemas);
  }

  public void cargarProblemaDetallado(){
    Problema problema =  tProblema.getSelectionModel().getSelectedItem();
    if(problema != null){
      problemaDetallado = FXCollections.observableArrayList();
      problemaDetallado.add(problema);
    }else{
      controller.mostrarAlerta("Advertencia", "Sea cuidadoso", "Debe de seleccionar un problema de la tabla");
      problemaDetallado = FXCollections.observableArrayList();
    }
   
  }
  
  public void cargarTablaProblemaDetallado(){
    tcFecha1.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    tcProblema1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    tcDescripcion1.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    
    tcCliente1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Problema, String>, ObservableValue<String>>() {
      public ObservableValue<String> call(TableColumn.CellDataFeatures<Problema, String> p) {
        String nombre = p.getValue().getCliente().getNombre() + p.getValue().getCliente().getApellidoPaterno();
        return new SimpleStringProperty(nombre);
      }
        });
    
    tcEstado1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Problema, String>, ObservableValue<String>>() {
      public ObservableValue<String> call(TableColumn.CellDataFeatures<Problema, String> p) {
        String estado = "Mal";
        if(p.getValue().getEstado()){
          estado = "Revisado";
        }else{
          estado = "Sin revisar";
        }
        return new SimpleStringProperty(estado);
      }
        });
    
    cargarProblemaDetallado();
    tClientes.setItems(problemaDetallado);
  }

@FXML
public void seleccionar(){
  cargarTablaProblemaDetallado();
}
  
  @FXML
  public void cambiarEstado(){
    Problema problema = tProblema.getSelectionModel().getSelectedItem();
    if(problema !=null){
      ProblemaDAO persistenciaProblema = new ProblemaDatos();
      if(persistenciaProblema.cambiarEstado(problema)){
        controller.mostrarConfirmacion("Mensaje", "Todo de maravilla", "El estado se ha acctualizado correctamente");
      }else{
        controller.mostrarError("Error", "Algo salio mal en la base de datos", "El estado no se cambio correctemente");
      }
      cargarProblemas("");
      cargarTablaProblemaSencillo();
    }
  }
  
  @FXML
  public void buscar(){
    cargarProblemas(tfBuscar.getText());
    cargarTablaProblemaSencillo();
    problemaDetallado = FXCollections.observableArrayList();
  }
  
  @FXML
  public void cerrar(){
    try {
      aplicacionPrincipal.mostrarVentanaPrincipal();
    } catch (Exception ex) {
      Logger.getLogger(ConsultarProblemaController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cargarProblemas("");
    cargarTablaProblemaSencillo();
  }

}
