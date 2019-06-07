/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import modelo.Pago;
import modelo.Promocion;

/**
 *
 * @author Miguel
 */
public interface PromocionDAO {
 List<Promocion> recuperarPromocionesVigentes(); 
 boolean agregarPromocionAplicada(Pago pago, Promocion promocion);
}
