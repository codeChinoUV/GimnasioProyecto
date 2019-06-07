/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;
import java.util.List;
import modelo.Pago;
import modelo.Promocion;

/**
 *
 * <<<<<<< HEAD
 * @a
 *
 * uthor irvin
 */
public interface PromocionDAO {

  public List<Promocion> getPromociones() throws SQLException;

  public boolean eliminarPromocion(Promocion promocion);

  public boolean editarPromociones(Promocion promocion);

  public boolean nuevaPromocion(Promocion promocion);

  List<Promocion> recuperarPromocionesVigentes();

  boolean agregarPromocionAplicada(Pago pago, Promocion promocion);
}
