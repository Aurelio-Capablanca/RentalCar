/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Controlador.ConstructorFactura;
import Controlador.ConstructorRecuperacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Administrador
 */
public class FuncionesFactura {
    
    private Connection cn;
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

    
      public FuncionesFactura(){
        Controlador.Conexion con = new Controlador.Conexion();
        cn = con.conectar();
    }
      
      public boolean guardarFactura (ConstructorFactura obj){
         boolean resp = false;
          try {
              String sql = "INSERT INTO Factura(idFactura, monto, fechaRegistro, numeroTarjeta, cantidadEfectivo,cambio ,giro, idTipoFactura, idContrato) VALUES (?,?,?,?,?,?,?,?,?) ";
              PreparedStatement cmd = cn.prepareStatement(sql);
              cmd.setInt(1, obj.getIdFactura());
              cmd.setDouble(2, obj.getMonto());
              cmd.setString(3, obj.getFechaRegistro());
              cmd.setString(4, obj.getNumeroTarjeta());
              cmd.setDouble(5, obj.getCantidadEfectivo());
              cmd.setString(6, obj.getCambio());
              cmd.setString(7, obj.getGiro());
              cmd.setInt(8, obj.getIdTipoFactura());
              cmd.setInt(9, obj.getIdContrato());
                if (!cmd.execute()) {
                resp = true;
                }
                
              
                
          } catch (Exception e) {
               System.out.println(e.toString()+"Error de INSERT");
          }
         return resp;
      }
      
        public Integer getIdTipoFactura(String nombre){
        Integer id = 0;
        try {
            String sql = "SELECT idTipoFactura FROM TipoFactura where tipoFactura = ?";
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
        public Integer getIdContrato(String nombre){
        Integer id = 0;
        try {
            String sql = "SELECT idContrato FROM Contrato where idContrato = ?";
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

        
        
        public String SeleccionarItemTipo(ConstructorFactura obj){
            String item = "";
            try {
                String sql = "SELECT tipoFactura FROM Factura ts, TipoFactura s WHERE ts.idTipoFactura = s.idTipoFactura AND ts.idFactura =  ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1, obj.getIdFactura());
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return item;
        }
        
        
        public String SeleccionarItemContrato(ConstructorFactura obj){
            String item = "";
            try {
                String sql = "SELECT idContrato FROM Factura ts, Contrato s WHERE ts.idContrato = s.idContrato AND ts.idFactura =  ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1, obj.getIdFactura());
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return item;
        }
        
          
        
    public boolean consultarCliente(ConstructorFactura cf)
    {
      boolean resp = false;
      try
      {
        String sql="Select sum(precio)from Viaje vj ,Cliente cl Where vj.idCliente=cl.idCliente  and cl.nombre=? ";
        PreparedStatement cmd= cn.prepareStatement(sql);
        cmd.setString(1,cf.getCliente());
        ResultSet rs= cmd.executeQuery();
        if(rs.next())
        {
          resp=true;
          
          cf.setMonto(rs.getDouble(1));
         
        }
        
      }
      catch(Exception e)
      {
         System.out.println(e.toString());
      }
        return resp;
    }
    
    
     public boolean BuscarContrato(ConstructorFactura cf)
    {
      boolean resp = false;
      try
      {
        String sql="Select monto from Contrato cr, Cliente cl  Where cr.idCliente=cl.idCliente and cl.nombre=? ";
        PreparedStatement cmd= cn.prepareStatement(sql);
        cmd.setString(1,cf.getCliente());
        ResultSet rs= cmd.executeQuery();
        if(rs.next())
        {
          resp=true;
          
          cf.setTotalContrato(rs.getDouble(1));
         
        }
        
      }
      catch(Exception e)
      {
         System.out.println(e.toString());
      }
        return resp;
    }
    
    
     
    
      
    public boolean actualizarContrato(ConstructorFactura cf)
    {
      boolean resp = false;
      try
      {
       String sql="update Contrato set monto=? where idContrato=?";
       PreparedStatement cmd = cn.prepareStatement(sql);
      cmd.setDouble(1,cf.getMonto());
      cmd.setInt(2,cf.getIdContrato());
     
       if (!cmd.execute()) 
       {
        resp = true;
       }
      }
      catch(Exception e)
      {
        System.out.println(e.toString());
      }
      return resp;
    } 
      
     
     public boolean actualizarServicio(ConstructorFactura cf)
    {
      boolean resp = false;
      try
      {
       String sql="update Servicio set idEstadoServicio=? where nombreServicio=?";
       PreparedStatement cmd = cn.prepareStatement(sql);
      cmd.setInt(1,cf.getEstadoServicio());
      cmd.setString(2,cf.getServicio());
     
       if (!cmd.execute()) 
       {
        resp = true;
       } 
      }
      catch(Exception e)
      {
        System.out.println(e.toString());
      }
      return resp;
    }  
    
       
// Id Incremental         
        

        public int idIncremental(){
            int ids = 1;
            
            try {
                String sql = "SELECT Max(idfactura) FROM Factura";
                PreparedStatement cmd = cn.prepareStatement(sql);
                ResultSet rs = cmd.executeQuery();
                while(rs.next()){
                    ids = rs.getInt(1)+1;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return ids;
        }

        
}
