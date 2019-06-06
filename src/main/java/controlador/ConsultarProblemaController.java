import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ConsultarProblemaController{

    @FXML
    private JFXButton bBuscar;

    @FXML
    private TableView<?> tProblema;

    @FXML
    private TableColumn<?, ?> tcFecha;

    @FXML
    private TableColumn<?, ?> tcNombre;

    @FXML
    private TableColumn<?, ?> tcDescipcion;

    @FXML
    private TableColumn<?, ?> tcEstado;

    @FXML
    private JFXButton bCerrar;

    @FXML
    private TableView<?> tClientes1;

    @FXML
    private TableColumn<?, ?> tcNombre1;

    @FXML
    private TableColumn<?, ?> tcMaterno1;

    @FXML
    private TableColumn<?, ?> tcDireccion1;

    @FXML
    private TableColumn<?, ?> tcPaterno1;

    @FXML
    private TableColumn<?, ?> tcPaterno11;

    @FXML
    private JFXButton bGuardar;

}
