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
public class ConstructorRecuperacion {
    
    public String usuario;
    public String clave;
    public Integer nivel;
    public String res1;
    public String res2;

    public ConstructorRecuperacion() {
        this.usuario = usuario;
        this.clave = clave;
        this.nivel = nivel;
        this.res1 = res1;
        this.res2 = res2;
    }

  

    public ConstructorRecuperacion(String usuario, String clave, Integer nivel, String res1, String res2) {
        
        this.usuario = usuario;
        this.clave = clave;
        this.nivel = nivel;
        this.res1 = res1;
        this.res2 = res2;
        
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getRes1() {
        return res1;
    }

    public void setRes1(String res1) {
        this.res1 = res1;
    }

    public String getRes2() {
        return res2;
    }

    public void setRes2(String res2) {
        this.res2 = res2;
    }
    
    
    
}
