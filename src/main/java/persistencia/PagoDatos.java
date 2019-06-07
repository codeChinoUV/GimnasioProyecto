/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.Cliente;
import modelo.Pago;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Miguel
 */
public class PagoDatos implements PagoDAO{

  @Override
  public List<Pago> recuperar(Cliente cliente) {
    List<Pago> pagos = new ArrayList<>();
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    String query = "SELECT * FROM pago WHERE id_cliente =" + cliente.getId() + ";";
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados != null && resultados.next()){
        Pago pago = new Pago();
        pago.setId(resultados.getInt("id_pago"));
        Date fechaPago = resultados.getDate("fecha_pago");
        LocalDate fechaPagoL = LocalDate.of(fechaPago.getYear() + 1900, fechaPago.getMonth() + 1, fechaPago.getDate());
        pago.setFecha(fechaPagoL);
        pago.setMonto(resultados.getFloat("monto"));
        pago.setCliente(cliente);
        pagos.add(pago);
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
    return pagos;
  }  
  
  public List<Pago> recuperarPagosSinVencer(Cliente cliente){
    List<Pago> pagos = new ArrayList<>();
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    LocalDate fechaMenosDosDias = LocalDate.now();
    Date fechaAtrasada = Date.valueOf(fechaMenosDosDias.minusDays(2));
    String query = "select * from pago left join periodo ON pago.id_pago = periodo.id_pago WHERE id_cliente =" +
        cliente.getId() + " AND (SELECT CURDATE()) BETWEEN fecha_inicio AND fecha_fin;";
    System.out.println(query);
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados != null && resultados.next()){
        Pago pago = new Pago();
        pago.setId(resultados.getInt("id_pago"));
        Date fechaPago = resultados.getDate("fecha_pago");
        LocalDate fechaPagoL = LocalDate.of(fechaPago.getYear() + 1900, fechaPago.getMonth() + 1, fechaPago.getDate());
        pago.setFecha(fechaPagoL);
        pago.setMonto(resultados.getFloat("monto"));
        pago.setCliente(cliente);
        pagos.add(pago);
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
    return pagos;
  }

    private void consultaGenerica(String query) throws SQLException {
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    consulta = conexion.createStatement();
    consulta.execute(query);
  }
  
  @Override
  public boolean almacenar(Pago pago) {
    System.out.println("BD:" + pago.getFecha());
    String query = "INSERT INTO pago (monto,fecha_pago,id_cliente) VALUES(" + pago.getMonto() + ",'" 
        + pago.getFecha() + "'," + pago.getCliente().getId() + ");";
    try{
      consultaGenerica(query);
      return true;
    }catch(SQLException e){
      e.printStackTrace();
      System.out.println("SQLE: almacenarPago-PagoDatos.almacenar");
      return false;
    }
  }

  @Override
  public int obtenerUltimoIdInsertado() {
   String query = "select MAX(id_pago) as ultimo from pago;";
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    try{
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while(resultados != null && resultados.next()){
        return resultados.getInt("ultimo");
      }
    }catch(SQLException ex){
      return -1;
    }
    return -1;
  }
}
