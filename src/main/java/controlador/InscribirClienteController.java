/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.uv.gimnasio.MainApp;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Membresia;
import modelo.Pago;
import modelo.Periodo;
import modelo.Promocion;
import persistencia.ClienteDAO;
import persistencia.ClienteDatos;
import persistencia.MembresiaDAO;
import persistencia.MembresiaDatos;
import persistencia.PagoDatos;
import persistencia.PagoDAO;
import persistencia.PeriodoDAO;
import persistencia.PeriodoDatos;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class InscribirClienteController implements Initializable {

  private Stage stagePrincipal = null;
  private MainApp aplicacionPrincipal = null;
  private Promocion promocionSeleccionadas = null;
  ConsultarClientesController controller = new ConsultarClientesController();
  @FXML
  private JFXTextField tfNombre;
  @FXML
  private JFXTextField tfPaterno;
  @FXML
  private JFXTextField tfMaterno;
  @FXML
  private JFXTextField tfDireccion;
  @FXML
  private JFXTextField tfTelefono;
  @FXML
  private JFXTextField tfEmail;
  @FXML
  private TableView<Membresia> tMembresia;
  private ObservableList<Membresia> membresiasDisponibles = null;

  @FXML
  private JFXTextArea tDescripcion;
  @FXML
  private TableColumn<Membresia, String> tcNombre;
  @FXML
  private TableColumn<Membresia, String> tcDescripcion;
  @FXML
  private TableColumn<Membresia, String> tcPrecio;
  @FXML
  private JFXButton bAceptar;
  @FXML
  private JFXButton bPromocion;
  @FXML
  private JFXButton bCancelar;
  @FXML
  private JFXButton bInscribir;

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

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cargarTabla();
    tDescripcion.setEditable(false);
    establecerCamposNormales();
  }

  /**
   * Guarda las membresias recuperadas de la base de datos en la lista ObservableList
   */
  private void recuperarMembresias() {
    membresiasDisponibles = FXCollections.observableArrayList();
    MembresiaDAO persistenciaMembresias = new MembresiaDatos();
    List<Membresia> membresiasRecuperadas = persistenciaMembresias.recuperarMembresiasDisponibles();
    for (Membresia membresia : membresiasRecuperadas) {
      membresiasDisponibles.add(membresia);
    }
  }

  /**
   * Carga la tabla de membresias
   */
  private void cargarTabla() {
    tMembresia.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    recuperarMembresias();
    tMembresia.setItems(membresiasDisponibles);
  }

  private List<Membresia> recuperarMembresiasSeleccionada() {
    ObservableList<Membresia> membresiaSeleccionadas = tMembresia.getSelectionModel().getSelectedItems();
    if (membresiaSeleccionadas.isEmpty()) {
      return null;
    } else {
      List<Membresia> membresias = new ArrayList<>();
      for (Membresia membresia : membresiaSeleccionadas) {
        membresias.add(membresia);
      }
      return membresias;
    }
  }

  private Cliente recuperarDatosCliente() {
    String nombre = tfMaterno.getText();
    String paterno = tfPaterno.getText();
    String materno = tfMaterno.getText();
    String telefono = tfTelefono.getText();
    String direccion = tfDireccion.getText();
    String correoElectronico = tfEmail.getText();
    if (nombre.equals("") && paterno.equals("") && direccion.equals("")) {
      return null;
    } else {
      Cliente cliente = new Cliente(nombre, paterno, materno, direccion, correoElectronico, telefono);
      return cliente;
    }
  }

  @FXML
  public void seleccionarPromociones() throws IOException {
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/RealizarPromocion.fxml"));
    AnchorPane rootPane = (AnchorPane) loader.load();
    Scene scene = new Scene(rootPane);
    Stage nuevaVentana = new Stage();
    //scene.getStylesheets().add("/styles/Styles.css");
    nuevaVentana.setTitle("Promociones");
    nuevaVentana.setScene(scene);
    nuevaVentana.initOwner(stagePrincipal);
    RealizarPromocionController controlador = loader.getController();
    controlador.setStagePrincipal(nuevaVentana);
    nuevaVentana.showAndWait();
    if (controlador.getPromocionesSeleccionadas() != null
        && !controlador.getPromocionesSeleccionadas().isEmpty()) {
      promocionSeleccionadas = controlador.getPromocionesSeleccionadas().get(0);
    }
  }

  private String resumenDescuento() {
    String promociones = "\nPromociones:\n";
    if (promocionSeleccionadas != null) {
      double descuento = promocionSeleccionadas.getMontoDescuento() * 100;
      promociones += "\t" + promocionSeleccionadas.getNombre() + ":\t"
          + descuento + "%\n";
    }
    return promociones;
  }

  private double calcularDescento(double totalAPagar) {
    double montoDescuento = 0;
    if (promocionSeleccionadas != null) {
      montoDescuento = promocionSeleccionadas.getMontoDescuento() * totalAPagar;
    }
    return montoDescuento;
  }

  private double calcularTotalMembresias(List<Membresia> membresiasSeleccionadas) {
    double totalAPagar = 0;
    for (Membresia membresia : membresiasSeleccionadas) {
      totalAPagar += membresia.getPrecio();
    }
    return totalAPagar;
  }

  private String resumenMembresias(List<Membresia> membresiasSeleccionadas) {
    String resumenDeInscripcion = "";
    for (Membresia membresia : membresiasSeleccionadas) {
      resumenDeInscripcion += "\t" + membresia.getNombre() + ":\t" + membresia.getPrecio() + "\n";
    }
    return resumenDeInscripcion;
  }

  private void mostrarInformacionDescripcion(String descripcion) {
    tDescripcion.setText(descripcion);
  }

  private void bloquearCamposYTabla() {
    tMembresia.setEditable(false);
    tfNombre.setEditable(false);
    tfPaterno.setEditable(false);
    tfMaterno.setEditable(false);
    tfTelefono.setEditable(false);
    tfDireccion.setEditable(false);
    tfEmail.setEditable(false);
    bAceptar.setDisable(true);
    bPromocion.setDisable(true);
    bCancelar.setDisable(false);
    bInscribir.setDisable(false);
  }

  private void establecerCamposNormales() {
    tMembresia.setEditable(true);
    tfNombre.setEditable(true);
    tfPaterno.setEditable(true);
    tfMaterno.setEditable(true);
    tfTelefono.setEditable(true);
    tfDireccion.setEditable(true);
    tfEmail.setEditable(true);
    bAceptar.setDisable(false);
    bPromocion.setDisable(false);
    bCancelar.setDisable(false);
    bInscribir.setDisable(true);
    tDescripcion.setText("");
  }

  @FXML
  public void cerrar() {
    try {
      getAplicacionPrincipal().mostrarVentanaPrincipal();
    } catch (Exception ex) {
      Logger.getLogger(InscribirClienteController.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  @FXML
  public void establecerVacio() {
    establecerCamposNormales();
    tfNombre.setText("");
    tfPaterno.setText("");
    tfMaterno.setText("");
    tfTelefono.setText("");
    tfDireccion.setText("");
    tfEmail.setText("");
    promocionSeleccionadas = null;
    tDescripcion.setText("");
  }

  @FXML
  public void aceptar() {
    Cliente cliente = recuperarDatosCliente();
    if (cliente != null) {
      List<Membresia> membresiasSeleccionadas = recuperarMembresiasSeleccionada();
      if (membresiasSeleccionadas != null) {
        String resumenDeInscripcion = "Membresias: \n";
        double totalAPagar = 0;
        double montoDescuento = 0;
        totalAPagar = calcularTotalMembresias(membresiasSeleccionadas);
        resumenDeInscripcion = resumenMembresias(membresiasSeleccionadas);
        resumenDeInscripcion += resumenDescuento();
        montoDescuento = calcularDescento(totalAPagar);
        resumenDeInscripcion += "Monto total:\n\t" + (totalAPagar - montoDescuento) + "\n";
        mostrarInformacionDescripcion(resumenDeInscripcion);
        bloquearCamposYTabla();
      } else {
        controller.mostrarAlerta("Advertencia", "Sea ciudadoso", "Debe de seleccionar una membresia por lo menos");
      }
    } else {
      controller.mostrarAlerta("Advertencia", "Sea ciudadoso", "Debe de llenar por lo menos los datos del cliente"
          + " nombre, paterno y direccion");
    }
  }

  @FXML
  public void inscribir() {
    ClienteDAO persistenciaCliente = new ClienteDatos();
    Cliente cliente = recuperarDatosCliente();
    persistenciaCliente.almacenarCliente(cliente);
    int idClienteAgregado = persistenciaCliente.obtenerUltimoIdInsertado();
    cliente.setId(idClienteAgregado);
    System.out.println("Id: " + cliente.getId());
    for (Membresia membresia : membresiasDisponibles) {
      Pago pago = new Pago();
      pago.setCliente(cliente);
      double monto = 0;
      if (promocionSeleccionadas != null) {
        monto = membresia.getPrecio() * promocionSeleccionadas.getMontoDescuento();
      } else {
        monto = membresia.getPrecio();
      }
      pago.setMonto(monto);
      LocalDate fechaPago = LocalDate.now();
      System.out.println(fechaPago);
      pago.setFecha(fechaPago);
      PagoDAO persistenciaPago = new PagoDatos();
      persistenciaPago.almacenar(pago);
      int idPagoAgregado = persistenciaPago.obtenerUltimoIdInsertado();
      pago.setId(idPagoAgregado);
      Periodo periodo = new Periodo();
      periodo.setFechaInicio(LocalDate.now());
      LocalDate fechaFin = LocalDate.now();
      periodo.setFechaFin(fechaFin.plusMonths(1));
      periodo.setPago(pago);
      periodo.setMembresia(membresia);
      PeriodoDAO persistenciaPeriodo = new PeriodoDatos();
      persistenciaPeriodo.almacenarPeriodo(periodo);

    }
    controller.mostrarConfirmacion("Mensaje", "Todo ha salido de manera correcta", "Se ha guardado"
        + " el cliente correctamente");
    establecerVacio();
  }
}
