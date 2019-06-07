import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Cliente;
import modelo.Problema;
import persistencia.ClienteDAO;
import persistencia.ClienteDatos;
import persistencia.ProblemaDAO;
import persistencia.ProblemaDatos;

public class ConsultarProblemaController implements Initializable{

    @FXML
    private JFXButton bBuscar;
    public List<Cliente> clientes = new ArrayList<>();
    public List<Problema> problemas = new ArrayList<>();
    private ObservableList<Problema> problemasO;
    private ObservableList<Cliente> clientesO;
    
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
    private TableView<Cliente> tClientes;

    @FXML
    private TableColumn<Cliente, String> tcCliente;

    @FXML
    private TableColumn<Cliente, String> tcProblema;

    @FXML
    private TableColumn<Cliente, String> tcDescripcion;

    @FXML
    private JFXButton bGuardar;

    public void recuperarProblemas(){
        problemasO = FXCollections.observableArrayList();
        ProblemaDAO persistenciaProblemas = new ProblemaDatos();
        List<Problema> problemasRecuperados = persistenciaProblemas.recuperarProblemas();
        for (Problema problema:problemasRecuperados){
            problemasO.add(problema);
        }
    }
    private void recuperarClientes() {
        clientesO = FXCollections.observableArrayList();
        ClienteDAO persistenciaClientes = new ClienteDatos();
        List<Cliente> clientesRecuperados = persistenciaClientes.recuperarClientes();
        for (Cliente cliente:clientesRecuperados){
            clientesO.add(cliente);
        }
    }
    
    public void cargarTabla(){
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        recuperarProblemas();
        recuperarClientes();    
        cargarClientes();
        cargarProblemas();
        tProblema.setItems(problemasO);
        
    }
    
    private void cargarClientes() {
      clientesO = FXCollections.observableArrayList();
      if (clientes != null) {
        for (Cliente cliente : clientes) {
          clientesO.add(cliente);
        }
      }
      tClientes.setItems(clientesO);
    }
    
    private void cargarProblemas() {
      ProblemaDAO persistenciaProblema = new ProblemaDatos();
      problemas = persistenciaProblema.recuperarProblemas();
      problemasO = FXCollections.observableArrayList();
      if (problemas != null) {
        for (Problema problema : problemas) {
          problemasO.add(problema);
        }
      }
      tProblema.setItems(problemasO);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Se inicializó");
        cargarTabla();
    }
}