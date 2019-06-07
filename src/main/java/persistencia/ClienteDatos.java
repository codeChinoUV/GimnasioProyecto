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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    String consultaq = "Select * from cliente where CONCAT(nombre, ' ', paterno, ' ', materno) rlike + '" + nombre + "';";
    List<Cliente> clientes = new ArrayList<>();
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(consultaq);
      while (resultados != null && resultados.next()) {
        Cliente cliente = new Cliente();
        cliente.setId(resultados.getInt(1));
        cliente.setNombre(resultados.getString(2));
        cliente.setApellidoPaterno(resultados.getString(3));
        cliente.setApellidoMaterno(resultados.getString(4));
        cliente.setTelefono(resultados.getString(5));
        cliente.setDireccion(resultados.getString(6));
        cliente.setCorreoElectronico(resultados.getString(7));
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
        System.out.println("SQLE: CerrarConexion-persistencia.ClienteDatos.recuperar");
      }
    }
    return clientes;

  }

  private boolean realizarConsultaGenerica(String query) {
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    try {
      consulta = conexion.createStatement();
      consulta.execute(query);
    } catch (SQLException ex) {
      System.out.println("SQLE: persistencia.ClientesDatos.consultaGenrica");
      ex.printStackTrace();
      return false;
    } finally {
      try {
        conexion.close();
      } catch (SQLException ex) {
        System.out.println("SQLE: CerrarConexion-persistencia.ClienteDatos.consultaGenerica");
      }
    }
    return true;
  }

  @Override
  public boolean actualizarCliente(Cliente cliente) {
    if (cliente != null) {
      String query = "UPDATE cliente SET nombre ='" + cliente.getNombre() + "', paterno ='"
          + cliente.getApellidoPaterno() + "', materno = '" + cliente.getApellidoMaterno() + "',"
          + " telefono ='" + cliente.getTelefono() + "', direccion = '" + cliente.getDireccion()
          + "', correo_electronico = '" + cliente.getCorreoElectronico() + "' WHERE id_cliente ="
          + cliente.getId() + ";";
      return realizarConsultaGenerica(query);
    } else {
      return false;
    }
  }

  private void consultaGenerica(String query) throws SQLException {
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    consulta = conexion.createStatement();
    consulta.execute(query);
  }

  @Override
  public boolean almacenarCliente(Cliente cliente) {
    String query = "INSERT INTO cliente(nombre,paterno,materno,telefono,direccion,correo_electronico)"
        + " VALUES ('" + cliente.getNombre() + "','" + cliente.getApellidoPaterno() + "','"
        + cliente.getApellidoMaterno() + "','" + cliente.getTelefono() + "','" + cliente.getDireccion()
        + "','" + cliente.getCorreoElectronico() + "');";
    try {
      consultaGenerica(query);
      return true;
    } catch (SQLException ex) {
      System.out.println("SQLE: Agregar cliente-perisistencia.almacenarCliente");
      return false;
    }
  }

  @Override
  public int obtenerUltimoIdInsertado() {
    String query = "select MAX(id_cliente) as ultimo from cliente;";
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados != null && resultados.next()) {
        return resultados.getInt("ultimo");
      }
    } catch (SQLException ex) {
      return -1;
    }
    return -1;
  }

  public Cliente recuperarClienteEspecifico(Cliente clienteBuscar) {
    Cliente cliente = new Cliente();
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    String query = "SELECT * FROM cliente WHERE id_cliente = " + clienteBuscar.getId() + ";";
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados != null && resultados.next()) {

        cliente.setId(resultados.getInt("id_cliente"));
        cliente.setNombre(resultados.getString("nombre"));
        cliente.setApellidoPaterno(resultados.getString("paterno"));
        cliente.setApellidoMaterno(resultados.getString("materno"));
        cliente.setTelefono(resultados.getString("telefono"));
        cliente.setDireccion(resultados.getString("direccion"));
        cliente.setCorreoElectronico(resultados.getString("correo_electronico"));
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
    return cliente;
  }
  
}
