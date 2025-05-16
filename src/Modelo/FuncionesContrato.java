/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author samue
 */
public class FuncionesContrato {
    private Connection cn;
        private Integer Id;
        private Double interes;
        private Double monto;
        private String inicio;
        private String fin;
        private String fregisto;
        private Integer idServicio;
        private Integer idClinte;
        private Integer idPago;

    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     * @return the interes
     */
    public Double getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(Double interes) {
        this.interes = interes;
    }

    /**
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * @return the inicio
     */
    public String getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fin
     */
    public String getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(String fin) {
        this.fin = fin;
    }

    /**
     * @return the fregisto
     */
    public String getFregisto() {
        return fregisto;
    }

    /**
     * @param fregisto the fregisto to set
     */
    public void setFregisto(String fregisto) {
        this.fregisto = fregisto;
    }

    /**
     * @return the idServicio
     */
    public Integer getIdServicio() {
        return idServicio;
    }

    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * @return the idClinte
     */
    public Integer getIdClinte() {
        return idClinte;
    }

    /**
     * @param idClinte the idClinte to set
     */
    public void setIdClinte(Integer idClinte) {
        this.idClinte = idClinte;
    }

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
    
    public FuncionesContrato() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     //ID incremental
     public Integer idIncremental() {

        int ids = 1;

        try {
            String sql = "SELECT MAX(idContrato) FROM Contrato";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                ids = rs.getInt(1) + 1;
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return ids;
    }
     //Formato de Fechas
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

    public String getFecha(JDateChooser jd) {
        if (jd.getDate() != null) {
            return formato.format(jd.getDate());

        } else {
            return null;
        }
    }

    public java.util.Date StringADate(String fecha) {
        SimpleDateFormat formato_del_texto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaE = null;
        try {
            fechaE = (Date) formato_del_texto.parse(fecha);
            return fechaE;

        } catch (ParseException e) {
            return null;
        }
    }
   
    
    // Mantenimientos
    // Guardar Contrato
    public boolean guardarContrato() {
        boolean resp = false;
        try {
            String sql = "INSERT INTO Contrato (idContrato, intereses, monto, fecha_inicio, fecha_fin, fechaRegistro,idServicio,idCliente,idPago)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, Id);
            cmd.setDouble(2, interes);
            cmd.setDouble(3, monto);
            cmd.setString(4, inicio);
            cmd.setString(5, fin);
            cmd.setString(6, fregisto);
            cmd.setInt(7, idServicio);
            cmd.setInt(8, idClinte);
            cmd.setInt(9, idPago);
            
            if (!cmd.execute()) {
                resp = true;
            }
            
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    //Actualizar Viaje
    public boolean modificarContrato() {
        boolean resp = false;
        try {
            String sql = "UPDATE Contrato SET intereses = ?, monto = ?, fecha_inicio = ?, fecha_fin = ?, idServicio = ?, idCliente = ?, idPago = ? WHERE idContrato = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            
            cmd.setDouble(1, interes);
            cmd.setDouble(2, monto);
            cmd.setString(3, inicio);
            cmd.setString(4, fin);
            cmd.setInt(5, idServicio);
            cmd.setInt(6, idClinte);
            cmd.setInt(7, idPago);
            cmd.setInt(8, Id);
            
            if (!cmd.execute()) {
                resp = true;
            }
            
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    //Consultar Viaje
    public boolean consultarContrato() {
        boolean resp = false;
        try {
            String sql = "SELECT idContrato, intereses, monto, fecha_inicio, fecha_fin, idServicio, idCliente , idPago FROM Contrato WHERE idContrato = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, Id);
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                resp = true;
                
                Id = rs.getInt(1);
                interes = rs.getDouble(2);
                monto = rs.getDouble(3);
                inicio = rs.getString(4);
                fin = rs.getString(5);
                idServicio = rs.getInt(6);
                idClinte = rs.getInt(7);
                idPago = rs.getInt(8);
            }
           
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    
    //Llenado de comboboxes
    public DefaultComboBoxModel getservicio() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.getCn();
            String sql = "SELECT nombreServicio FROM Servicio";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
            rs.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }
    public DefaultComboBoxModel getcliente() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.getCn();
            String sql = "SELECT nombre FROM Cliente";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
            rs.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }
    public DefaultComboBoxModel getpago() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.getCn();
            String sql = "SELECT tipoPago FROM TipoPago";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
            rs.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }
    //Llenado de Texto en combo
    public Integer getIdCliente(String nombre){
        Integer id = 0;
        try {
            String sql = "SELECT idCliente FROM Cliente where nombre = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
    public Integer getIdServicio(String nombre){
        Integer id = 0;
        try {
            String sql = "SELECT idServicio FROM Servicio where nombreServicio = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
    public Integer getIdPago(String nombre){
        Integer id = 0;
        try {
            String sql = "SELECT idPago FROM TipoPago where tipoPago = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
    //Obtener Item
    public String seleccionarItemServicio(){
     String item = "";
            try {
                String sql = "SELECT monto FROM Contrato tc, Servicio cl WHERE tc.idServicio = cl.idServicio AND cl.idContrato = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1,Id);
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println("Error Select "+e.toString());
            }
            return item;
  }
    public String seleccionarItemCliente(){
     String item = "";
            try {
                String sql = "SELECT nombre FROM Cliente tc, Contrato cl WHERE tc.idCliente = cl.idCliente AND cl.idContrato = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1,Id);
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println("Error Select "+e.toString());
            }
            return item;
  }
    public String seleccionarItemPago(){
     String item = "";
            try {
                String sql = "SELECT tipoPago FROM Pago tc, Contrato cl WHERE tc.idPago = cl.idPago AND cl.idContrato = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1,Id);
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println("Error Select "+e.toString());
            }
            return item;
  }
}
