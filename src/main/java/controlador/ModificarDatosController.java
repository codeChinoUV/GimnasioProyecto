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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modelo.Cliente;
import persistencia.ClienteDatos;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class ModificarDatosController implements Initializable {

  @FXML
  private JFXTextField tfNombres;
  @FXML
  private JFXTextField tfPaterno;
  @FXML
  private JFXTextField tfMaterno;
  @FXML
  private JFXTextField tfDireccion;
  @FXML
  private JFXTextField tfTelefono;
  @FXML
  private JFXTextField tfCorreo;
  @FXML
  private JFXButton bCancelar;
  @FXML
  private JFXButton bGuardar;

  private Cliente clienteAModificar;
  
  private MainApp aplicacionPrincipal;
  
  private Stage stagePrincipal;

  public ModificarDatosController(Cliente clienteAModificar) {
    this.clienteAModificar = clienteAModificar;
  }

  public ModificarDatosController() {

  }

  public Cliente getClienteAModificar() {
    return clienteAModificar;
  }

  public void setClienteAModificar(Cliente clienteAModificar) {
    this.clienteAModificar = clienteAModificar;
  }

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
  
  /**
   * Coloca la informacion del cliente en los campos
   *
   * @param cliente El cliente el cual se va a modificar
   */
  private void cargarDatosDelClienteEnCampos(Cliente cliente) {
    tfNombres.setText(cliente.getNombre());
    tfPaterno.setText(cliente.getApellidoPaterno());
    tfMaterno.setText(cliente.getApellidoPaterno());
    tfDireccion.setText(cliente.getDireccion());
    tfTelefono.setText(cliente.getTelefono());
    tfCorreo.setText(cliente.getCorreoElectronico());
  }

  /**
   * Varifica que los campos de nombre, apellido paterno, direccion y telefono esten llenos
   *
   * @return Verdadero si los campos importantes contienen informacion o falso si no
   */
  private boolean verificarCamposImportantesLllenos() {
    if (!tfNombres.getText().equals("") && !tfPaterno.getText().equals("")
        && !tfDireccion.getText().equals("") && !tfTelefono.getText().equals("")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Verifica que una cadena de texto solo contenga letras
   *
   * @param texto La cadena de texto a verificar
   * @return Verdadero si solo contiene letras o falso si contiene algun numero.
   */
  private boolean soloTexto(String texto) {
    for (int i = 0; i < texto.length(); i++) {
      char c = texto.charAt(i);
      if (Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Verifica que una cadena de texto solo contenga digitos
   *
   * @param texto La cadena de texto a verificar
   * @return Verdade si solo contiene digitos o falso si contiene alguna letra
   */
  private boolean soloNumeros(String texto) {
    for (int i = 0; i < texto.length(); i++) {
      char c = texto.charAt(i);
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Verifica que los tipo de datos de los campo nombre(texto), paterno(texto), materno(texto) y
   * telefono(digitos) solo contengan lo deseado
   *
   * @return Verdadero si los tipos de datos son correctos o falsi si no
   */
  private boolean verificarTipoDeDatoIngresado() {
    if (!soloTexto(tfNombres.getText())) {
      return false;
    }
    if (!soloTexto(tfPaterno.getText())) {
      return false;
    }
    if (!soloTexto(tfMaterno.getText())) {
      return false;
    }
    if (!soloNumeros(tfTelefono.getText())) {
      return false;
    }
    return true;
  }

  /**
   * Obtiene la informacion de los campos y con ella crea un nuevo cliente
   *
   * @return El cliente creado a partir de la informacion de los campos
   */
  private Cliente recuperarDatosDeCampos() {
    Cliente cliente = new Cliente();
    cliente.setNombre(tfNombres.getText());
    cliente.setApellidoPaterno(tfPaterno.getText());
    cliente.setApellidoMaterno(tfMaterno.getText());
    cliente.setTelefono(tfTelefono.getText());
    cliente.setDireccion(tfDireccion.getText());
    cliente.setCorreoElectronico(tfCorreo.getText());
    return cliente;
  }

  /**
   * Muestra una ventana de dialogo para una alerta
   * @param titulo Un Stirng que es el titulo de la ventana
   * @param cabeceraMensaje Un String que es la cabecera del mensaje
   * @param mensaje Un string que es el mensaje que se muestra 
   */
  private void mostrarAlerta(String titulo, String cabeceraMensaje, String mensaje) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle(titulo);
    alert.setHeaderText(cabeceraMensaje);
    alert.setContentText(mensaje);
    alert.showAndWait();
  }
  
  /**
   * Muestra una ventana de dialogo para una confirmacion
   * @param titulo Un Stirng que es el titulo de la ventana
   * @param cabeceraMensaje Un String que es la cabecera del mensaje
   * @param mensaje Un string que es el mensaje que se muestra 
   */
  private void mostrarConfirmacion(String titulo, String cabeceraMensaje, String mensaje) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(titulo);
    alert.setHeaderText(cabeceraMensaje);
    alert.setContentText(mensaje);
    alert.showAndWait();
  }
  /**
   * Muestra una ventana de dialogo para un error
   * @param titulo Un Stirng que es el titulo de la ventana
   * @param cabeceraMensaje Un String que es la cabecera del mensaje
   * @param mensaje Un string que es el mensaje que se muestra 
   */
  private void mostrarError(String titulo, String cabecera, String mensaje) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(titulo);
    alert.setHeaderText(cabecera);
    alert.setContentText(mensaje);
    alert.showAndWait();
  }
  
  /**
   * Verifica que los datos importantes esten llenos, que sean del tipo correcto y almacena los cambios
   * del cliente
   */
  @FXML
  public void guardar() {
    if (verificarCamposImportantesLllenos()) {
      if (verificarTipoDeDatoIngresado()) {
        Cliente clienteModificado = recuperarDatosDeCampos();
        clienteModificado.setId(clienteAModificar.getId());
        ClienteDatos persistenciaCliente = new ClienteDatos();
        if (persistenciaCliente.actualizarCliente(clienteModificado)) {
          mostrarConfirmacion("Todo bien", "La actualizacion se ha realizado correctamente",
              "La informacion del cliente " + clienteModificado.getNombre() + " se ha actualizado "
              + "correctamente");
        } else {
          mostrarError("Error", "Algo ha salido mal en la base de datos", "No se almacenaron los "
              + "cambios realizados al cliente");
        }
      } else {
        mostrarAlerta("Algunos datos no son como deberian", "Sea cuidadoso con los datos que introduce",
            "Los campos de nombre, apellido paterno y apellido materno no deben de contener digitos y el "
            + "campo de telefono no debe de contener letras ni simbolos");
      } 
    } else {
      mostrarAlerta("Faltan datos importantes", "Sea cuidadoso con los datos ingresados",
          "Por lo menos debe de llenar los campos de nombre, apellido paterno, telefono y direccion");
    }
  }
  
  /**
   * Inicializa la vista
   */
  public void inicializar(){
    cargarDatosDelClienteEnCampos(clienteAModificar);
  }

  /**
   * Cancela la modificacion de los datos y regresa a la venta consultar cliente
   */
  @FXML
  public void cancelar(){
    aplicacionPrincipal.cambiarVistaAConsultarCliente();
  }
  
  /**
   * Initializes the controller class.
   */ 
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    
  }

}
