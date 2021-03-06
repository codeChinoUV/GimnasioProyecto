import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InscribirClienteController {

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
    private TableView<?> tMembresia;

    @FXML
    private TableColumn<?, ?> tcNombre;

    @FXML
    private TableColumn<?, ?> tcDescripcion;

    @FXML
    private TableColumn<?, ?> tcPrecio;

    @FXML
    private JFXButton bAceptar;

    @FXML
    private JFXButton bPromocion;

    @FXML
    private JFXButton bCancelar;

    @FXML
    private JFXButton bInscribir; 
}
