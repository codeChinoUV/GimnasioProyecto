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
        String query = "UPDATE promociones SET nombre = '" + promocion.getNombre() + "', monto_descuento = '"
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
      String query = "INSERT INTO promociones(nombre,montoDescuento,fecha_inicio,fecha_fin,descripcion,id_promocion) VALUES ('" + promocion.getNombre() + "','" +promocion.getMontoDescuento()+ "','" +promocion.getFecha_inicio()+ "','" +promocion.getFecha_fin()+ "','" +promocion.getDescripcion()+ "','" +promocion.getId()+"');";
     
      try{
        consulta = conexion.createStatement();
        consulta.executeUpdate(query);
      }catch(SQLException errorSQL){
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
 
}
