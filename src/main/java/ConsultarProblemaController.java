import com.jfoenix.controls.JFXButton;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Cliente;
import modelo.Problema;
import persistencia.ProblemaDAO;
import persistencia.ProblemaDatos;

public class ConsultarProblemaController {

    @FXML
    private JFXButton bBuscar;

    private ObservableList<Problema> problemas = null;
    private ObservableList<Cliente> cliente = null;
    
    @FXML
    private TableView<Problema> tProblema;

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
    private TableView<Cliente> tClientes1;

    @FXML
    private TableColumn<Cliente, String> tcCliente;

    @FXML
    private TableColumn<Cliente, String> tcProblema;

    @FXML
    private TableColumn<Cliente, String> tcDescripcion;

    @FXML
    private JFXButton bGuardar;

    public void RecuperarProblemas(){
        problemas = FXCollections.observableArrayList();
        ProblemaDAO persistenciaProblemas = new ProblemaDatos();
        List<Problema> problemasRecuperados = persistenciaProblemas.recuperarProblemas();
        for (Problema problema:problemasRecuperados){
            problemas.add(problema);
        }
    }
    
    public void cargarTabla(){
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        RecuperarProblemas();
    }
}
