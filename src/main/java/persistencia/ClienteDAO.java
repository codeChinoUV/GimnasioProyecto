/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import modelo.Cliente;

/**
 *
 * @author Miguel
 */
public interface ClienteDAO {
  List<Cliente> recuperarClientes();
  List<Cliente> buscarClientes(String nombre);
}
