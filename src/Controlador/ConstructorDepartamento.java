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
public class ConstructorDepartamento {
    
    public Integer id;
    public String departamento;
    
     public ConstructorDepartamento() {
        this.id = id;
        this.departamento = departamento;
    }

    public ConstructorDepartamento(Integer id, String departamento) {
        this.id = id;
        this.departamento = departamento;
    }

    
    
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
