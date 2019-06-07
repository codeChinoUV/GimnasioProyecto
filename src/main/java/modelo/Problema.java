package modelo;

import java.time.LocalDate;

/**
 *
 * @author Bruno
 */
public class Problema {
    private int idProblema;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private LocalDate fecha;
    private Cliente Cliente;

    public  Problema(){
        
    }
    
    public Problema(int idProblema, String nombre, String descripcion, boolean estado, LocalDate fecha, Cliente Cliente) {
        this.idProblema = idProblema;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.Cliente = Cliente;
    }

    public Problema(int idProblema, String nombre, String descripcion, boolean estado, LocalDate fecha) {
        this.idProblema = idProblema;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Problema(String nombre, String descripcion, boolean estado, LocalDate fecha, Cliente Cliente) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.Cliente = Cliente;
    }
    
    public int getIdProblema() {
        return idProblema;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }
    
}
