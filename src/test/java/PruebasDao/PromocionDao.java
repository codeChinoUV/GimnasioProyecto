/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Promocion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import persistencia.PromocionDAO;
import persistencia.PromocionDatos;

/**
 *
 * @author irvin
 */
public class PromocionDao {
  
@Test
  public void probarGetPromociones() throws SQLException{
    PromocionDAO promoDatos = new PromocionDatos();
    List<Promocion> resultadosNormales = promoDatos.getPromociones();
    List <Promocion> resultadosEsperados = new ArrayList<Promocion>();
    Promocion promocion = new Promocion(1212,"hola",LocalDate.of(2019, 12, 14), LocalDate.of(2019, 01, 21), 12, "dsadsadsd");
    resultadosEsperados.add(promocion);
    assertEquals(promocion, resultadosNormales);
    
 }
}
