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
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import modelo.Pago;
import modelo.Promocion;

/**
 *
 * @author Miguel
 */
public class PromocionDatos implements PromocionDAO{

  @Override
  public List<Promocion> recuperarPromocionesVigentes() {
    List<Promocion> promociones = new ArrayList<>();
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    ResultSet resultados;
    String query = "SELECT * FROM promocion;"; //Poner para recuperar promociones vigentes
    try {
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados != null && resultados.next()) {
        Promocion promocion = new Promocion();
        promocion.setId(resultados.getInt("id_promocion"));
        promocion.setNombre(resultados.getString("nombre"));
        Date fechaInicio = resultados.getDate("fecha_inicio");
        LocalDate fechaInicioParaGuardar = LocalDate.of(fechaInicio.getYear(), fechaInicio.getMonth(),
            fechaInicio.getDate());
        promocion.setFecha_inicio(fechaInicioParaGuardar);
        Date fechaFin = resultados.getDate("fecha_fin");
        LocalDate fechaFinParaGuardar = LocalDate.of(fechaFin.getYear(), fechaFin.getMonth(),
            fechaFin.getDate());
        promocion.setFecha_fin(fechaFinParaGuardar);
        promocion.setMontoDescuento(resultados.getDouble("monto_descuento"));
        promociones.add(promocion);
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
    return promociones;
  }

   private void consultaGenerica(String query) throws SQLException {
    Connection conexion = new Conexion().getCon();
    Statement consulta;
    consulta = conexion.createStatement();
    consulta.execute(query);
  }
  
  @Override
  public boolean agregarPromocionAplicada(Pago pago, Promocion promocion) {
    String query = "INSERT INTO promociones_aplicadas VALUES("+ pago.getId() + "," + promocion.getId()+");";
    try{
      consultaGenerica(query);
      return true;
    }catch(SQLException e){
      e.printStackTrace();
      System.out.println("SQLE: agregarPromocion-promocionDatos.agregarPromocionAplicada");
      return false;
    }
  }
  
}
