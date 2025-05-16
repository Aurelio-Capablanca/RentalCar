/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author adalb
 */
public class ConstructorCliente {
    
    public Integer id;
    public String nombre;
    public String apellido;
    public String representante;
    public String telefono;
    public String direccion;
    public String nit;
    public String fechas;
    public Integer Tipo; 
    public Integer Estado;
    
     public ConstructorCliente() {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.representante = representante;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
        this.fechas = fechas;
        this.Tipo = Tipo;
        this.Estado=Estado;
    }

    public ConstructorCliente(int id, String nombre, String apellido, String representante, String telefono, String direccion, String nit, String fechas, int Tipo , int Estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.representante = representante;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
        this.fechas = fechas;
        this.Tipo = Tipo;
        this.Estado=Estado;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }

    public Integer getTipo() {
        return Tipo;
    }

    public void setTipo(Integer Tipo) {
        this.Tipo = Tipo;
    }

    public Integer getEstado() {
        return Estado;
    }

    public void setEstado(Integer Estado) {
        this.Estado = Estado;
    }
    
    
}
