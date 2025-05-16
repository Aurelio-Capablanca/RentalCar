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
public class ConstructorFactura {

    
    private Integer idFactura;
    private Double monto;
    private String fechaRegistro;
    private String numeroTarjeta;
    private Double cantidadEfectivo;
    private String giro;
    private String razonSocial;
    private Integer idTipoFactura;
    private Integer idContrato;
    private String Cliente;
    private String Servicio;
    private Integer Contrato;
    private Integer EstadoServicio;
    private Double Pago;
    private Double TotalContrato;
    private String Cambio;

    public ConstructorFactura(Integer idFactura, Double monto, String fechaRegistro, String numeroTarjeta, Double cantidadEfectivo, String giro, String razonSocial, Integer idTipoFactura, Integer idContrato, String Cliente, String Servicio, Integer Contrato, Integer EstadoServicio, Double Pago, Double TotalContrato, String Cambio) {
        this.idFactura = idFactura;
        this.monto = monto;
        this.fechaRegistro = fechaRegistro;
        this.numeroTarjeta = numeroTarjeta;
        this.cantidadEfectivo = cantidadEfectivo;
        this.giro = giro;
        this.razonSocial = razonSocial;
        this.idTipoFactura = idTipoFactura;
        this.idContrato = idContrato;
        this.Cliente = Cliente;
        this.Servicio = Servicio;
        this.Contrato = Contrato;
        this.EstadoServicio = EstadoServicio;
        this.Pago = Pago;
        this.TotalContrato = TotalContrato;
        this.Cambio = Cambio;
    }
   
    public ConstructorFactura() {
       this.idFactura = idFactura;
        this.monto = monto;
        this.fechaRegistro = fechaRegistro;
        this.numeroTarjeta = numeroTarjeta;
        this.cantidadEfectivo = cantidadEfectivo;
        this.giro = giro;
        this.razonSocial = razonSocial;
        this.idTipoFactura = idTipoFactura;
        this.idContrato = idContrato;
        this.Cliente = Cliente;
        this.Servicio = Servicio;
        this.Contrato = Contrato;
        this.EstadoServicio = EstadoServicio;
        this.Pago = Pago;
        this.TotalContrato = TotalContrato;
        this.Cambio = Cambio;
    }

    public String getCambio() {
        return Cambio;
    }

    public void setCambio(String Cambio) {
        this.Cambio = Cambio;
    }
    
    
     public Double getTotalContrato() {
        return TotalContrato;
    }

    public void setTotalContrato(Double TotalContrato) {
        this.TotalContrato = TotalContrato;
    }
    
    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Double getCantidadEfectivo() {
        return cantidadEfectivo;
    }

    public void setCantidadEfectivo(Double cantidadEfectivo) {
        this.cantidadEfectivo = cantidadEfectivo;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Integer getIdTipoFactura() {
        return idTipoFactura;
    }

    public void setIdTipoFactura(Integer idTipoFactura) {
        this.idTipoFactura = idTipoFactura;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }
   
    
    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }

    public Integer getContrato() {
        return Contrato;
    }

    public void setContrato(Integer Contrato) {
        this.Contrato = Contrato;
    }

    public Integer getEstadoServicio() {
        return EstadoServicio;
    }

    public void setEstadoServicio(Integer EstadoServicio) {
        this.EstadoServicio = EstadoServicio;
    }

    public Double getPago() {
        return Pago;
    }

    public void setPago(Double Pago) {
        this.Pago = Pago;
    }

    
   
    
}
