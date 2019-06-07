/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;
<<<<<<< HEAD
=======

>>>>>>> Bruno
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Promocion;
/**
 *
 * @author irvin
 */
public class PromocionDatos implements PromocionDAO{
  
  /**
   *
   * @return
   * @throws SQLException
   */
  @Override
  public List<Promocion> getPromociones() throws SQLException {
        List<Promocion> listaPromociones = new ArrayList<Promocion>();
        Statement s;
        Connection conn = null;
        conn = new Conexion().getCon();
        ResultSet rs = null;
        String sQuery = "SELECT * from promocion";
        try {
            s = conn.createStatement();
            rs = s.executeQuery(sQuery);
           System.out.println("Ejecutó la consulta");
            while (rs != null && rs.next()) {
                Promocion prom = new Promocion();
                prom.setNombre(rs.getString("nombre"));
                prom.setMontoDescuento(rs.getInt("monto_descuento"));
                Date fechaInicio = rs.getDate("fecha_Inicio");
                LocalDate fechaInicioL = LocalDate.of(fechaInicio.getYear() + 1900, fechaInicio.getMonth() + 1, fechaInicio.getDate());
                prom.setFecha_inicio(fechaInicioL);
                Date fechaFin = rs.getDate("fecha_Fin");
                LocalDate fechaFinL = LocalDate.of(fechaFin.getYear() + 1900, fechaFin.getMonth() +1, fechaFin.getDate());
                prom.setFecha_fin(fechaFinL);
                prom.setDescripcion(rs.getString("descripcion"));
                prom.setId(rs.getInt("id_promocion"));
               
                listaPromociones.add(prom);

            }
          
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage() + "\n" + e.getErrorCode());
        } finally {
            conn.close();
        }
        if (listaPromociones != null) {
            System.out.println("Lista llena");
        } else {
            System.out.println("Lista vacia");
        }
        return listaPromociones;
    }
    

  @Override
    public boolean editarPromociones(Promocion promocion) {
        Connection conexion;
        conexion = new Conexion().getCon();
        Statement consulta;
        System.out.println("Editar promocion de la BD");
        String query = "UPDATE promocion SET nombre = '" + promocion.getNombre() + "', monto_descuento = '"
                + promocion.getMontoDescuento() +"', fecha_inicio = '" + promocion.getFecha_inicio() + "' , fecha_fin = '" +promocion.getFecha_fin()+ "',descripcion ='" +promocion.getDescripcion()+
                "' WHERE id_promocion = '" + promocion.getId() + "';";
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(query);

        } catch (SQLException errorSQL) {
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromocionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
        
  @Override
    public boolean eliminarPromocion(Promocion promocion) {
        Connection conexion;
        conexion = new Conexion().getCon();
        Statement consulta;
        String query = "DELETE FROM promocion WHERE nombre ='" + promocion.getNombre() + "';";
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(query);
        } catch (SQLException errorSQL) {
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromocionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
  @Override
    public boolean nuevaPromocion(Promocion promocion){
      Connection conexion;
      conexion = new Conexion().getCon();
      Statement consulta;
      String query = "INSERT INTO promocion(nombre,monto_descuento,fecha_inicio,fecha_fin,descripcion,id_promocion) VALUES ('" + promocion.getNombre() + "','" +promocion.getMontoDescuento()+ "','" +promocion.getFecha_inicio()+ "','" +promocion.getFecha_fin()+ "','" +promocion.getDescripcion()+ "','" +promocion.getId()+"');";
      System.out.println(query);
      try{
        consulta = conexion.createStatement();
        System.out.println("conexion");
        consulta.execute(query);
        System.out.println("kakaka");
      }catch(SQLException errorSQL){
        errorSQL.printStackTrace();
        return false;
      }finally{
        try{
          conexion.close();
        }catch(SQLException ex){
          Logger.getLogger(PromocionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return true;
    }
 
=======
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
  
>>>>>>> Bruno
}
