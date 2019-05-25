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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.Cliente;
import modelo.Membresia;
import modelo.Pago;
import modelo.Periodo;
import persistencia.ClienteDatos;
import persistencia.MembresiaDatos;
import persistencia.PagoDatos;
import persistencia.PeriodoDatos;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class ConsultarClientesController implements Initializable {

  public List<Cliente> clientes = new ArrayList<>();
  private ObservableList<Cliente> clientesO = null;
  @FXML
  private TableView<Cliente> tClientes;
  @FXML
  private TableColumn<Cliente, String> tcNombre;
  @FXML
  private TableColumn<Cliente, String> tcPaterno;
  @FXML
  private TableColumn<Cliente, String> tcMaterno;
  @FXML
  private TableColumn<Cliente, String> tcDireccion;
  @FXML
  private TableColumn<Cliente, String> tcTelefono;
  @FXML
  private TableColumn<Cliente, String> tcCorreo;
  @FXML
  private TableColumn<Cliente, String> tcMembresias;
  @FXML
  private JFXTextField tfBuscar;
  @FXML
  private JFXButton bBuscar;
  @FXML
  private JFXButton bModificarDatos;
  @FXML
  private JFXButton bSalir;

  private MainApp aplicacionPrincipal;

  private Stage stagePrincipal;

  public MainApp getAplicacionPrincipal() {
    return aplicacionPrincipal;
  }

  public void setAplicacionPrincipal(MainApp aplicacionPrincipal) {
    this.aplicacionPrincipal = aplicacionPrincipal;
  }

  public Stage getStagePrincipal() {
    return stagePrincipal;
  }

  public void setStagePrincipal(Stage stagePrincipal) {
    this.stagePrincipal = stagePrincipal;
  }

  private void buscarCliente() {
    String clienteABuscar = tfBuscar.getText();
    ClienteDatos clientesPersistencia = new ClienteDatos();
    if (!clienteABuscar.equals("")) {
      clientes = clientesPersistencia.buscarClientes(clienteABuscar);
    } else {
      clientes = clientesPersistencia.recuperarClientes();
    }
    PagoDatos pagosPersistencia = new PagoDatos();
    PeriodoDatos periodoPersistencia = new PeriodoDatos();
    MembresiaDatos membresiaPersistencia = new MembresiaDatos();
    for (Cliente cliente : clientes) {
      List<Pago> pagos = pagosPersistencia.recuperarPagosSinVencer(cliente);
      if (pagos.isEmpty()) {
        System.out.println("Vacia");
      } else {
        System.out.println("Contiene algo");
      }
      cliente.setPagos(pagos);
      for (Pago pago : cliente.getPagos()) {
        Periodo periodo = periodoPersistencia.recuperar(pago);
        Membresia membresia = membresiaPersistencia.recuperar(periodo);
        periodo.setMembresia(membresia);
        pago.setPeriodo(periodo);
      }
    }
  }

  @FXML
  public void buscarBoton() {
    buscarCliente();
    cargarTabla();
  }

  private void cargarClientes() {
    clientesO = FXCollections.observableArrayList();
    if (clientes != null) {
      for (Cliente cliente : clientes) {
        System.out.println("Nombre:" + cliente.getNombre());
        clientesO.add(cliente);
      }
    }
    tClientes.setItems(clientesO);
  }

  public void cargarTabla() {
    tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    tcPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
    tcMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
    tcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    tcCorreo.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));

    tcMembresias.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
      public ObservableValue<String> call(TableColumn.CellDataFeatures<Cliente, String> p) {
        String membresias = "";
        for (Pago pago : p.getValue().getPagos()) {
          membresias += pago.getPeriodo().getMembresia().getNombre() + ", ";
        }
        return new SimpleStringProperty(membresias);
      }
    });
    buscarCliente();
    cargarClientes();
  }

  /**
   * Muestra una ventana de dialogo para una alerta
   * @param titulo Un Stirng que es el titulo de la ventana
   * @param cabeceraMensaje Un String que es la cabecera del mensaje
   * @param mensaje Un string que es el mensaje que se muestra 
   */
  private void mostrarAlerta(String titulo, String cabeceraMensaje, String mensaje) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(titulo);
    alert.setHeaderText(cabeceraMensaje);
    alert.setContentText(mensaje);
    alert.showAndWait();
  }
  
  /**
   * Cambia a la ventana modificar datos y le pasa el cliente que va a modificar
   */
  @FXML
  public void cambiarAModificarDatos(){
    if(tClientes.getSelectionModel().getSelectedItem() != null){
      aplicacionPrincipal.cambiarVistaAModificarCliente(tClientes.getSelectionModel().getSelectedItem());
    }else{
      mostrarAlerta("Advertencia", "¿Que desea modificar?", "Debe de seleccionar un cliente de la tabla");
    }
  }
  
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    System.out.println("Se inicializo");
    cargarTabla();
  }
}
