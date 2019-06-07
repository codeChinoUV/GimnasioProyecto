/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import modelo.Membresia;
import modelo.Periodo;

/**
 *
 * @author Miguel
 */
public interface MembresiaDAO {
  Membresia recuperar(Periodo periodo);
<<<<<<< HEAD
  List<Membresia> recuperarMembresias();
=======
  List<Membresia> recuperarMembresiasDisponibles();
>>>>>>> consultarProblema
}
