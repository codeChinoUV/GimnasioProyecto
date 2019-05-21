/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;

/**
 *
 * @author Miguel
 */
public class ClienteDatos implements ClienteDAO {

  @Override
  public List<Cliente> recuperarClientes() {
    List<Cliente> clientes = new ArrayList<>();
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    String query = "SELECT * FROM cliente";
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados != null && resultados.next()) {
        Cliente cliente = new Cliente();
        cliente.setId(resultados.getInt("id_cliente"));
        cliente.setNombre(resultados.getString("nombre"));
        cliente.setApellidoPaterno(resultados.getString("paterno"));
        cliente.setApellidoMaterno(resultados.getString("materno"));
        cliente.setTelefono(resultados.getString("telefono"));
        cliente.setDireccion(resultados.getString("direccion"));
        cliente.setCorreoElectronico(resultados.getString("correo_electronico"));
        clientes.add(cliente);
      }
    } catch (SQLException errorSQL) {
      System.out.println("SQLE: Error obteniendo datos-persistencia.ClienteDatos");
      errorSQL.printStackTrace();
      return null;
    } finally {
      try {
        conexion.close();
      } catch (SQLException ex) {
        System.out.println("SQLE: CerrarConexion-persistencia.ClienteDatos");
      }
    }
    return clientes;
  }

  @Override
  public List<Cliente> buscarClientes(String nombre) {
    //String nombreRegular = "*"+nombre+"*"; 
    String consultaq = "Select CONCAT(nombre, ' ', paterno, ' ', materno)"
        + " as nombre from cliente where nombre rlike + '" + nombre + "';";
    List<Cliente> clientes = new ArrayList<>();
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(consultaq);
      while (resultados != null && resultados.next()) {
        Cliente cliente = new Cliente();
        cliente.setId(resultados.getInt("id_cliente"));
        cliente.setNombre(resultados.getString("nombre"));
        cliente.setApellidoPaterno(resultados.getString("paterno"));
        cliente.setApellidoMaterno(resultados.getString("materno"));
        cliente.setTelefono(resultados.getString("telefono"));
        cliente.setDireccion(resultados.getString("direccion"));
        cliente.setCorreoElectronico(resultados.getString("correo_electronico"));
        clientes.add(cliente);
      }
    } catch (SQLException errorSQL) {
      System.out.println("SQLE: Error obteniendo datos-persistencia.ClienteDatos");
      errorSQL.printStackTrace();
      return null;
    } finally {
      try {
        conexion.close();
      } catch (SQLException ex) {
        System.out.println("SQLE: CerrarConexion-persistencia.ClienteDatos");
      }
    }
    return clientes;

  }

}
