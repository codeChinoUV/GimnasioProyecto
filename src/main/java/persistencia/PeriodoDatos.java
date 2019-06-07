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
import modelo.Pago;
import modelo.Periodo;

/**
 *
 * @author Miguel
 */
public class PeriodoDatos implements PeriodoDAO{

  @Override
  public Periodo recuperar(Pago pago) {
    Periodo periodo = new Periodo();
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    String query = "SELECT * FROM periodo WHERE id_pago =" + pago.getId() + ";";
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados != null && resultados.next()){
        Date fechaInicio = resultados.getDate("fecha_inicio");
        LocalDate fechaInicioL = LocalDate.of(fechaInicio.getYear() + 1900, fechaInicio.getMonth() + 1, fechaInicio.getDate());
        periodo.setFechaInicio(fechaInicioL);
        Date fechaFin = resultados.getDate("fecha_fin");
        LocalDate fechaFinL = LocalDate.of(fechaFin.getYear() + 1900, fechaFin.getMonth() + 1, fechaFin.getDate());
        periodo.setFechaFin(fechaFinL);
        int idMembresia = resultados.getInt("id_membresia");
        Membresia membresia = new Membresia();
        membresia.setId(idMembresia);
        periodo.setMembresia(membresia);
        periodo.setPago(pago);
      }
    } catch (SQLException errorSQL) {
      //JOptionPane.showMessageDialog(null, "Error obteniendo datos");
      errorSQL.printStackTrace();
      return null;
    } finally {
      try {
        conexion.close();
      } catch (SQLException ex) {
        System.out.println("Error en de la base de datos");
      }
    }
    return periodo;
  }

  private void consultaGenerica(String query) throws SQLException {
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    consulta = conexion.createStatement();
    consulta.execute(query);
  }
  
  @Override
  public boolean almacenarPeriodo(Periodo periodo) {
    String query = "INSERT INTO periodo VALUES('" + periodo.getFechaInicio() + "','" 
        + periodo.getFechaFin() + "'," + periodo.getMembresia().getId() + ","
        + periodo.getPago().getId() + ");";
    try{
      consultaGenerica(query);
      return true;
    }catch(SQLException e){
      e.printStackTrace();
      System.out.println("SQLE: almacenarPeriodo-PeriodoDatos.almacenarPeriodo");
      return false;
    }
  }
}
