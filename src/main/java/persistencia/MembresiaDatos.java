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
import modelo.Membresia;
import modelo.Periodo;

/**
 *
 * @author Miguel
 */
public class MembresiaDatos implements MembresiaDAO {

  @Override
  public Membresia recuperar(Periodo periodo) {
    Membresia membresia = new Membresia();
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    String query = "SELECT * FROM membresia WHERE id_membresia = " + periodo.getMembresia().getId() + ";";
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados != null && resultados.next()) {
        membresia.setId(resultados.getInt("id_membresia"));
        membresia.setNombre(resultados.getString("nombre"));
        membresia.setDescripcion(resultados.getString("descripcion"));
        membresia.setPrecio(resultados.getFloat("precio"));
      }
    } catch (SQLException errorSQL) {
      JOptionPane.showMessageDialog(null, "Error obteniendo datos");
      errorSQL.printStackTrace();
      return null;
    } finally {
      try {
        conexion.close();
      } catch (SQLException ex) {
        System.out.println("Error en de la base de datos");
      }
    }
    return membresia;
  }
}
