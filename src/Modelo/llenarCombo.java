/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import Controlador.Conexion;
/**
 *
 * @author Administrador
 */
public class llenarCombo  extends Conexion{
    
     public DefaultComboBoxModel getValues(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT estadoServicio FROM EstadoServicio";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        return modelo;
        
    }
    
    public DefaultComboBoxModel getValues2(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT tipoServicio FROM TipoServicio";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        return modelo;
    
    }   
    
    
    public DefaultComboBoxModel getModd(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT tipoFactura FROM TipoFactura";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        return modelo;
    
    }  
    
    public DefaultComboBoxModel getValues4(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT idContrato FROM Contrato";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        return modelo;
    
    }  
}
