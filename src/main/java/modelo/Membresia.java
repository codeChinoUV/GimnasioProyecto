/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author Miguel
 */
public class Membresia {
  private int id;
  private String nombre;
  private String descripcion;
  private double precio;
  private List<Area> areasConAcceso;

  public Membresia(int id, String nombre, String descripcion, double precio, List<Area> areasConAcceso) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.areasConAcceso = areasConAcceso;
  }

  public Membresia(String nombre, String descripcion, double precio, List<Area> areasConAcceso) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.areasConAcceso = areasConAcceso;
  }

  public Membresia(String nombre, String descripcion, double precio) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
  }

  public Membresia() {
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

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public List<Area> getAreasConAcceso() {
    return areasConAcceso;
  }

  public void setAreasConAcceso(List<Area> areasConAcceso) {
    this.areasConAcceso = areasConAcceso;
  }
  
  
}
