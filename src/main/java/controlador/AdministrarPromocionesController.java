package controlador;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.uv.gimnasio.MainApp;
import persistencia.Conexion;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import modelo.Promocion;
import persistencia.PromocionDatos;

public class AdministrarPromocionesController implements Initializable {
    MainApp programaPrincipal;
    @FXML ObservableList<Promocion> promocionest;
    @FXML private Label label;
    @FXML private TableView<Promocion> tablaPromociones;
    @FXML private TableColumn<Promocion, String> tcNombre;
    @FXML private TableColumn<Promocion, String> tcDescuento;
    @FXML private TableColumn<Promocion, String> tcFechaInicio;
    @FXML private TableColumn<Promocion, String> tcFechaFin;
    @FXML private TableColumn<Promocion, String> tcDescripcion;
    @FXML private TableColumn<Promocion, String> tcIdPromocion;
    
    @FXML private JFXButton btnModificar;
    @FXML private JFXButton btnGuardar;
    @FXML private JFXButton btnEliminar;
    @FXML private JFXButton btnCancelar;
    @FXML private JFXButton btnRegistrar;
    
    @FXML private JFXTextField tfnombre;
    @FXML private JFXTextField tfDescuento;
    @FXML private JFXTextField tfID;
    
    @FXML private JFXDatePicker dpFechaInicio;
    @FXML private JFXDatePicker dpFechaFin;
    
    @FXML private JFXTextArea taDescripcion;
    PromocionDatos promo = new PromocionDatos();
    //Conexion controlador = new Conexion();
    /**
     * Esta funcion sirve para mostra el contenido en la tabla
     */
    public void cargarTablaPromociones(){
      btnGuardar.setVisible(false);
      btnCancelar.setVisible(false);
      try{
        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tcDescuento.setCellValueFactory(new PropertyValueFactory("montoDescuento"));
        tcFechaInicio.setCellValueFactory(new PropertyValueFactory("fecha_inicio"));
        tcFechaFin.setCellValueFactory(new PropertyValueFactory("fecha_fin"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
       // tcIdPromocion.setCellValueFactory(new PropertyValueFactory("id_promocion"));
        tcIdPromocion.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Promocion, String>, ObservableValue<String>>() {
      public ObservableValue<String> call(TableColumn.CellDataFeatures<Promocion, String> p) {
        String idPromocion = Integer.toString(p.getValue().getId());
        return new SimpleStringProperty(idPromocion);
      }
    }
        );
        List<Promocion> promocionesD = promo.getPromociones();
        promocionest = FXCollections.observableArrayList();
        
        for(Promocion promocion : promocionesD){
          promocionest.add(promocion);
        }
       tablaPromociones.setItems(promocionest);
      }catch (SQLException ex){
        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    @FXML
    private Promocion obtenerPromocionSeleccionada(){
      if(tablaPromociones != null){
        Promocion promocion = tablaPromociones.getSelectionModel().getSelectedItem();
        return promocion;
      }else{
        return null;
      }
    }
    
    @FXML
    private void modificarPromocion(ActionEvent event){
       
       int posicion = tablaPromociones.getSelectionModel().getSelectedIndex();
       if(posicion != -1){
         btnEliminar.setVisible(false);
         btnModificar.setVisible(false);
         btnGuardar.setVisible(true);
        btnCancelar.setVisible(true);
         Promocion promocion = obtenerPromocionSeleccionada();
         tablaPromociones.setDisable(true);
         tfnombre.setText(promocion.getNombre());
         tfDescuento.setText(Double.toString(promocion.getMontoDescuento()));
         dpFechaInicio.setValue(promocion.getFecha_inicio());
         dpFechaFin.setValue(promocion.getFecha_fin());
         taDescripcion.setText(promocion.getDescripcion());
         tfID.setText(Integer.toString(promocion.getId()));
         
         
       }else{
         JOptionPane.showMessageDialog(null, "Selecciona una promocion");
       }
    }
    
    @FXML
    private void guardarCambiosPromocion(ActionEvent event) {
        tablaPromociones.setDisable(false);
        String nombre = tfnombre.getText();
        double montoDescuento = Double.parseDouble(tfDescuento.getText());
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFin = dpFechaFin.getValue();
        String descripcion = taDescripcion.getText();
        int idPromocion = (Integer.parseInt(tfID.getText()));
        Promocion promocion = new Promocion(idPromocion,nombre,fechaInicio,fechaFin,montoDescuento, descripcion);
   
        if (("".equals(idPromocion) != true) && (nombre.equals("") != true)
                && ("".equals(montoDescuento) != true) && (fechaInicio.equals("") != true) && (fechaFin.equals("") != true) &&(descripcion.equals("") !=true)) {
          
            promo.editarPromociones(promocion);
            Promocion promocion2 = new Promocion(idPromocion,nombre,fechaInicio,fechaFin,montoDescuento, descripcion);
            promocion2.setFecha_inicio(dpFechaInicio.getValue());
            promocion2.setFecha_fin(dpFechaFin.getValue());
            int posicionSeleccionado = tablaPromociones.getSelectionModel().getSelectedIndex();
            promocionest.set(posicionSeleccionado, promocion2);
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }
        btnEliminar.setVisible(true);
        btnModificar.setVisible(true);
        btnCancelar.setVisible(false);
        btnGuardar.setVisible(false);
    }
     @FXML
    private void limpiar() {
        tfnombre.setText("");
        tfDescuento.setText("");
        dpFechaInicio.getEditor().clear();
        dpFechaFin.getEditor().clear();
        taDescripcion.setText("");
        tfID.setText("");
    }
    @FXML
    private void cancelarAccion(ActionEvent event){
       tfnombre.setText("");
        tfDescuento.setText("");
        taDescripcion.setText("");
        btnEliminar.setVisible(true);
        btnModificar.setVisible(true);
        btnCancelar.setVisible(false);
        btnGuardar.setVisible(false);
        dpFechaInicio.getEditor().clear();
        dpFechaFin.getEditor().clear();
       
    }
    
    @FXML
    private void eliminarPromocion(ActionEvent event) {
        Promocion promocion = obtenerPromocionSeleccionada();
        int posicionSeleccionado = tablaPromociones.getSelectionModel().getSelectedIndex();
        if (posicionSeleccionado != -1) {
            int respuesta = JOptionPane.showConfirmDialog(null,"¿Desea eliminar la promocion?", "Eliminar promocion",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta == 0){
              promo.eliminarPromocion(promocion);
              promocionest.remove(posicionSeleccionado);
              }
         } else {
            JOptionPane.showMessageDialog(null, "Debe de selecionar primero una promocion de la tabla");
        }
    }
    public void setProgramaPrincipal(MainApp programaPrincipal) {
        this.programaPrincipal = programaPrincipal; 
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTablaPromociones();
        tfID.setDisable(true);     
    }    
    
    @FXML
    public void ventanaRegistro(ActionEvent event){
        try {
            programaPrincipal.mostrarVentanaRegistroPromocion();
        } catch (IOException ex) {
          ex.printStackTrace();
            Logger.getLogger(AgregarPromocionController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
      public void salir(ActionEvent event) throws Exception{
      try {
            this.programaPrincipal.mostrarVentanaPrincipal();
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
  }
}