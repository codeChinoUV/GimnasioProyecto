/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

<<<<<<< HEAD
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
=======
import java.util.List;
import modelo.Pago;
>>>>>>> Bruno
import modelo.Promocion;

/**
 *
<<<<<<< HEAD
 * @author irvin
 */
public interface PromocionDAO {
  public List<Promocion> getPromociones() throws SQLException;
   public boolean eliminarPromocion(Promocion promocion);
   public boolean editarPromociones(Promocion promocion);
   public boolean nuevaPromocion(Promocion promocion);
   
=======
 * @author Miguel
 */
public interface PromocionDAO {
 List<Promocion> recuperarPromocionesVigentes(); 
 boolean agregarPromocionAplicada(Pago pago, Promocion promocion);
>>>>>>> Bruno
}
