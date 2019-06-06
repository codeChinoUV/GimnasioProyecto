import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InscribirCliente2Controller {

    @FXML
    private TableView<?> tPromocion;

    @FXML
    private TableColumn<?, ?> tcNombre;

    @FXML
    private TableColumn<?, ?> tcDescuento;

    @FXML
    private JFXButton bAceptar;

    @FXML
    private JFXButton bCancelar;

}
