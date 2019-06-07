/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import modelo.Promocion;

/**
 *
 * @author irvin
 */
public interface PromocionDAO {
  public List<Promocion> getPromociones() throws SQLException;
   public boolean eliminarPromocion(Promocion promocion);
   public boolean editarPromociones(Promocion promocion);
   public boolean nuevaPromocion(Promocion promocion);
   
}
