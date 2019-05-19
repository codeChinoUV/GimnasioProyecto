/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Miguel
 */
public class Pago {
  private LocalDate fecha;
  private double monto;
  private List<Promocion> promocionesAplicadas;
  private Periodo periodoDeVencimientoMembresia;

  public Pago(LocalDate fecha, double monto) {
    this.fecha = fecha;
    this.monto = monto;
  }

  public Pago() {
  }

  public Pago(LocalDate fecha, double monto, Periodo periodoDeVencimientoMembresia) {
    this.fecha = fecha;
    this.monto = monto;
    this.periodoDeVencimientoMembresia = periodoDeVencimientoMembresia;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public double getMonto() {
    return monto;
  }

  public void setMonto(double monto) {
    this.monto = monto;
  }

  public List<Promocion> getPromocionesAplicadas() {
    return promocionesAplicadas;
  }

  public void setPromocionesAplicadas(List<Promocion> promocionesAplicadas) {
    this.promocionesAplicadas = promocionesAplicadas;
  }

  public Periodo getPeriodoDeVencimientoMembresia() {
    return periodoDeVencimientoMembresia;
  }

  public void setPeriodoDeVencimientoMembresia(Periodo periodoDeVencimientoMembresia) {
    this.periodoDeVencimientoMembresia = periodoDeVencimientoMembresia;
  }
  
  
}
