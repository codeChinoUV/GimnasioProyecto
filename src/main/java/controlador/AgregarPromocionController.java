package controlador;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.uv.gimnasio.MainApp;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import modelo.Promocion;
import persistencia.Conexion;
import persistencia.PromocionDatos;

/**
 * FXML Controller class
 *
 * @author irvin
 */
public class AgregarPromocionController implements Initializable {
    MainApp programaPrincipal;
   // Conexion controlador = new Conexion();
    PromocionDatos promo = new PromocionDatos();
    @FXML private JFXTextField tfRnombre;
    @FXML private JFXDatePicker dpRfechaInicio;
    @FXML private JFXDatePicker dpRfechaFin;
    @FXML private JFXTextArea taRdescripcion;
    @FXML private JFXTextField tfRdescuento;
    @FXML private JFXTextField tfRid;
    @FXML ObservableList<Promocion> promocionest;
    @FXML private JFXButton btnRegistrar;
    @FXML private JFXButton btnCancelar;
    @FXML private JFXButton btnRegresar;
    
  public void setProgramaPrincipal(MainApp programaPrincipal){
    this.programaPrincipal = programaPrincipal;
  }
  @FXML
  public void registrarPromocion(ActionEvent event){
      String nombre = tfRnombre.getText();
      int montoDescuento = Integer.parseInt(tfRdescuento.getText());
      LocalDate fechaInicio = dpRfechaInicio.getValue();
      LocalDate fechaFin = dpRfechaFin.getValue();
      String descripcion = taRdescripcion.getText();
      int idPromocion = Integer.parseInt(tfRid.getText());
      
      if((nombre.equals("") != true) && ("".equals(montoDescuento) != true) && (fechaInicio.equals("") != true) && (fechaFin.equals("") != true) && (descripcion.equals("") != true) && ("".equals(idPromocion) != true)){
          Promocion promocion = new Promocion(idPromocion,nombre,fechaInicio,fechaFin,montoDescuento,descripcion);
          System.out.println(promo.nuevaPromocion(promocion));
          promocionest = FXCollections.observableArrayList();
          promocionest.add(promocion);
          JOptionPane.showMessageDialog(null, "Promocion agregada exitosamente");
          limpiar();
      }else{
        JOptionPane.showMessageDialog(null, "Rellene todos los campos");
      }
  }
  @FXML
  public void cancelarRegistro(ActionEvent event){
    limpiar();
  }
    @FXML
    private void limpiar() {
        tfRnombre.setText("");
        tfRdescuento.setText("");
        dpRfechaInicio.getEditor().clear();
        dpRfechaFin.getEditor().clear();
        taRdescripcion.setText("");
        tfRid.setText("");
    }
   @FXML
   public void regresarAdministracion(ActionEvent event){
      try {
            this.programaPrincipal.mostrarVentanaAdministrarPromocion();
        } catch (IOException ex) {
            Logger.getLogger(AgregarPromocionController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
   }
   public void colocarImagenBoton(){
      URL linkFlecha = getClass().getResource("/images/flecha.png");
      Image imagenFlecha = new Image(linkFlecha.toString(),100,60,false,true);
      
      btnRegresar.setGraphic((new ImageView (imagenFlecha)));
      
    }
   
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
    colocarImagenBoton();
  }  
  
}
