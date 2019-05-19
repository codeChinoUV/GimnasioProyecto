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
public class Cliente {
  private int id;
  private String nombre;
  private String apellidoPaterno;
  private String apellidoMaterno;
  private String direccion;
  private String correoElectronico;
  private String telefono;
  private List<Pago> pagos;
  private List<Propaganda> propagandasEnviadas;

  public Cliente() {
  }

  public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String correoElectronico, String telefono) {
    this.nombre = nombre;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
    this.direccion = direccion;
    this.correoElectronico = correoElectronico;
    this.telefono = telefono;
  }

  public Cliente(int id, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String correoElectronico, String telefono) {
    this.id = id;
    this.nombre = nombre;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
    this.direccion = direccion;
    this.correoElectronico = correoElectronico;
    this.telefono = telefono;
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

  public String getApellidoPaterno() {
    return apellidoPaterno;
  }

  public void setApellidoPaterno(String apellidoPaterno) {
    this.apellidoPaterno = apellidoPaterno;
  }

  public String getApellidoMaterno() {
    return apellidoMaterno;
  }

  public void setApellidoMaterno(String apellidoMaterno) {
    this.apellidoMaterno = apellidoMaterno;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getCorreoElectronico() {
    return correoElectronico;
  }

  public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public List<Pago> getPagos() {
    return pagos;
  }

  public void setPagos(List<Pago> pagos) {
    this.pagos = pagos;
  }

  public List<Propaganda> getPropagandasEnviadas() {
    return propagandasEnviadas;
  }

  public void setPropagandasEnviadas(List<Propaganda> propagandasEnviadas) {
    this.propagandasEnviadas = propagandasEnviadas;
  }
  
  
  
}
