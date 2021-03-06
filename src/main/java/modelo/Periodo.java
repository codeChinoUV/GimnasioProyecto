/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Miguel
 */
public class Periodo {
  
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private Membresia membresia;
  private Pago pago;

  public Periodo(LocalDate fechaInicio, LocalDate fechaFin) {
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
  }

  public Periodo(LocalDate fechaInicio, LocalDate fechaFin, Membresia membresia, Pago pago) {
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.membresia = membresia;
    this.pago = pago;
  }

  public Periodo() {
  }

  public LocalDate getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(LocalDate fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public LocalDate getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(LocalDate fechaFin) {
    this.fechaFin = fechaFin;
  }

  public Membresia getMembresia() {
    return membresia;
  }

  public void setMembresia(Membresia membresia) {
    this.membresia = membresia;
  }

  public Pago getPago() {
    return pago;
  }

  public void setPago(Pago pago) {
    this.pago = pago;
  }
  
}
