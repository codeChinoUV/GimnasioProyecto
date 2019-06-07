/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.Pago;
import modelo.Periodo;

/**
 *
 * @author Miguel
 */
public interface PeriodoDAO {
  Periodo recuperar(Pago pago);
  boolean almacenarPeriodo(Periodo perido);
}
