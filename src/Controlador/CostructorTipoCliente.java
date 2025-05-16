/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;

/**
 *
 * @author adalb
 */
public class CostructorTipoCliente {
    
    public Integer id;
    public String tipo; 
    private Connection cn;
    
    
    public CostructorTipoCliente() {
        this.id = id;
        this.tipo = tipo;
    }

    public CostructorTipoCliente(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    
    
    
}
