/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
       // problema.setFecha(resultados.getString("fecha"));
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
            String consultaq = "Select CONCAT(fecha, ' ', nombre, ' ', descripcion, ' ', estado)"
          + " as nombre from problema where nombre rlike + '" + nombre + "';";
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
          //problema.setFecha(resultados.getString("fecha"));
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
}
