import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Cliente;
import modelo.Problema;
import persistencia.ClienteDAO;
import persistencia.ClienteDatos;
import persistencia.ProblemaDAO;
import persistencia.ProblemaDatos;

public class ConsultarProblemaController {

    @FXML
    private JFXButton bBuscar;
    public List<Cliente> clientesO = new ArrayList<>();
    public List<Problema> problemasO = new ArrayList<>();
    private ObservableList<Problema> problemas = null;
    private ObservableList<Cliente> clientes = null;
    
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
    private void RecuperarClientes() {
        clientes = FXCollections.observableArrayList();
        ClienteDAO persistenciaClientes = new ClienteDatos();
        List<Cliente> clientesRecuperados = persistenciaClientes.recuperarClientes();
        for (Cliente cliente:clientesRecuperados){
            clientes.add(cliente);
        }
    }
    
    public void cargarTabla(){
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        RecuperarProblemas();
        RecuperarClientes();    
        cargarClientes();
        cargarProblemas();
    }
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Se inicializo");
        cargarTabla();
  }
    private void cargarClientes() {
      clientes = FXCollections.observableArrayList();
      if (clientesO != null) {
        for (Cliente cliente : clientesO) {
          System.out.println("Nombre:" + cliente.getNombre());
          clientes.add(cliente);
        }
      }
      tClientes1.setItems(clientes);
    }
    
    private void cargarProblemas() {
      problemas = FXCollections.observableArrayList();
      if (problemasO != null) {
        for (Problema problema : problemasO) {
          problemas.add(problema);
        }
      }
      tProblema.setItems(problemas);
    }
  private void buscarProblema() {
    String problemaABuscar = bBuscar.getText();
    ProblemaDatos problemaPersistencia = new ProblemaDatos();
    if (!problemaABuscar.equals("")) {
      clientes = problemaPersistencia.buscarProblemas(problemaABuscar);
    } else {
      clientes = clientesPersistencia.recuperarClientes();
    }
    PagoDatos pagosPersistencia = new PagoDatos();
    PeriodoDatos periodoPersistencia = new PeriodoDatos();
    MembresiaDatos membresiaPersistencia = new MembresiaDatos();
    for (Cliente cliente : clientes) {
      List<Pago> pagos = pagosPersistencia.recuperarPagosSinVencer(cliente);
      if(pagos.isEmpty()){
        System.out.println("Vacia");
      }else{
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
  public void buscarBoton(){
    buscarCliente();
    cargarTabla();
  }
}