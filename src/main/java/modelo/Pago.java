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
  private int id;
  private LocalDate fecha;
  private double monto;
  private List<Promocion> promocionesAplicadas;
  private Cliente cliente;
  private Periodo periodo;

  public Pago(int id, LocalDate fecha, double monto) {
    this.fecha = fecha;
    this.monto = monto;
    this.id = id;
  }

  public Pago() {
  }

  public Pago(LocalDate fecha, double monto) {
    this.fecha = fecha;
    this.monto = monto;
  }

  public Pago(int id, LocalDate fecha, double monto, List<Promocion> promocionesAplicadas, Cliente cliente, Periodo periodo) {
    this.id = id;
    this.fecha = fecha;
    this.monto = monto;
    this.promocionesAplicadas = promocionesAplicadas;
    this.cliente = cliente;
    this.periodo = periodo;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Periodo getPeriodo() {
    return periodo;
  }

  public void setPeriodo(Periodo periodo) {
    this.periodo = periodo;
  }
  
}
