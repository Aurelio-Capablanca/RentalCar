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
public class ConstructorBitacora {
    
    public Integer id;
    public String persona;
    public String fecha;
    public String accion;

    public ConstructorBitacora() {
        this.id = id;
        this.persona = persona;
        this.fecha = fecha;
        this.accion = accion;
    }

    public ConstructorBitacora(Integer id, String persona, String fecha, String accion) {
       
     this.id = id;
     this.persona = persona;
     this.fecha = fecha;
     this.accion = accion;
            
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
    
    
    
}


