/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Administrador
 */
public class ConstructorTipoPago {

    /**
     * @return the idPago
     */
    public Integer getIdPago() {
        return idPago;
    }

    /**
     * @param idPago the idPago to set
     */
    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    /**
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    private Integer idPago;
    private String tipoPago;
    
}
