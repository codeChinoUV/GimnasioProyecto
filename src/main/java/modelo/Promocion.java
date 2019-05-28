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
public class Promocion {
  
  private int id;
  private String nombre;
  private LocalDate fecha_inicio;
  private LocalDate fecha_fin;
  private double montoDescuento;
  private String descripcion;

  public Promocion(int id, String nombre, LocalDate fecha_inicio, LocalDate fecha_fin, double montoDescuento, String descripcion) {
    this.id = id;
    this.nombre = nombre;
    this.fecha_inicio = fecha_inicio;
    this.fecha_fin = fecha_fin;
    this.montoDescuento = montoDescuento;
    this.descripcion = descripcion;
  } 

  public Promocion(String nombre, LocalDate fecha_inicio, LocalDate fecha_fin, double montoDescuento, String descripcion) {
    this.nombre = nombre;
    this.fecha_inicio = fecha_inicio;
    this.fecha_fin = fecha_fin;
    this.montoDescuento = montoDescuento;
    this.descripcion = descripcion;
  }
  
  public Promocion() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public LocalDate getFecha_inicio() {
    return fecha_inicio;
  }

  public void setFecha_inicio(LocalDate fecha_inicio) {
    this.fecha_inicio = fecha_inicio;
  }

  public LocalDate getFecha_fin() {
    return fecha_fin;
  }

  public void setFecha_fin(LocalDate fecha_fin) {
    this.fecha_fin = fecha_fin;
  }

  public double getMontoDescuento() {
    return montoDescuento;
  }

  public void setMontoDescuento(double montoDescuento) {
    this.montoDescuento = montoDescuento;
  }
  
  public void setDescripcion(String descripcion){
    this.descripcion = descripcion;
  }
  public String getDescripcion(){
    return descripcion;
  }
  
}
