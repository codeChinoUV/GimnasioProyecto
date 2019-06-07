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
import modelo.Cliente;
import modelo.Problema;

/**
 *
 * @author Bruno
 */
public class ProblemaDatos implements ProblemaDAO {

  @Override
  public List<Problema> recuperarProblemas() {
    List<Problema> problemas = new ArrayList<>();
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    String query = "SELECT * FROM problema";

    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados != null && resultados.next()) {
        Problema problema = new Problema();
        Cliente cliente = new Cliente();
        problema.setIdProblema(resultados.getInt("id_problema"));
        problema.setNombre(resultados.getString("nombre"));
        problema.setDescripcion(resultados.getString("descripcion"));
        problema.setEstado(resultados.getBoolean("estado"));
        Date fecha = resultados.getDate("fecha");
        LocalDate fechaProblema = LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDate());
        problema.setFecha(fechaProblema);
        cliente.setId(resultados.getInt("id_cliente"));
        problema.setCliente(cliente);
        problemas.add(problema);
      }
    } catch (SQLException errorSQL) {
      System.out.println("SQLE: Error obteniendo datos-persistencia.ProblemaDatos");
      errorSQL.printStackTrace();
      return null;
    } finally {
      try {
        conexion.close();
      } catch (SQLException ex) {
        System.out.println("SQLE: CerrarConexion-persistencia.ProblemaDatos");
      }
    }
    return problemas;
  }

  public List<Problema> buscarProblema(String nombre) {
    String consultaq = "Select *"
        + "  from problema where nombre rlike + '" + nombre + "';";
    List<Problema> problemas = new ArrayList<>();
    Connection conexion = new Conexion().getCon();
    Cliente cliente = new Cliente();
    Statement consulta;
    ResultSet resultados;
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(consultaq);
      while (resultados != null && resultados.next()) {
        Problema problema = new Problema();
        problema.setIdProblema(resultados.getInt("id_problema"));
        problema.setNombre(resultados.getString("nombre"));
        problema.setDescripcion(resultados.getString("descripcion"));
        problema.setEstado(resultados.getBoolean("estado"));
        Date fecha = resultados.getDate("fecha");
        LocalDate fechaProblema = LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDate());
        problema.setFecha(fechaProblema);
        cliente.setId(resultados.getInt("id_cliente"));
        problema.setCliente(cliente);
        problemas.add(problema);
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
    return problemas;
  }

  private void consultaGenerica(String query) throws SQLException{
    Connection conexion = new Conexion().getCon();
    Statement consulta = conexion.createStatement();
    consulta.execute(query);
  }
  
  @Override
  public boolean cambiarEstado(Problema problema) {
    String query = "UPDATE problema set estado = 1 WHERE id_problema = " + problema.getIdProblema()
        + ";";
    try{
      consultaGenerica(query);
      return true;
    }catch(SQLException ex){
      System.out.println("SQLE: cambiarestado-ProblemaDatos.cambiarEstado");
      ex.printStackTrace();
      return false;
    }
  }
}
