/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import modelo.Problema;

/**
 *
 * @author Bruno
 */
public interface ProblemaDAO {
    List<Problema> recuperarProblemas();
    List<Problema> buscarProblema(String nombre);
        
}
