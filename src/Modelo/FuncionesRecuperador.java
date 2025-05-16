/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.ConstructorRecuperacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;





/**
 *
 * @author adalb
 */
public class FuncionesRecuperador extends Conexion {
    
    public Connection cn;  
    
     public FuncionesRecuperador()
    { 
       
       Conexion sn= new Conexion();
       cn=sn.conectar();
    }
    
     public boolean CapturarPersona(ConstructorRecuperacion cr) {
        boolean retorno = false;
        try {
            String sql = "SELECT usuario FROM Persona WHERE usuario =?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs ;
            cmd.setString(1,cr.getUsuario());
            rs=cmd.executeQuery();
            if (rs.next()) {
                return true;
                
               } 
            else 
            {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error algo malo ha sucedido ", "Error Critico", JOptionPane.ERROR_MESSAGE);
        }
        return retorno;
    }
 
   
      public boolean compararRespuestas(ConstructorRecuperacion cr) {
        boolean retorno = false;
        try {
            String sql = "SELECT respuesta1, respuesta2 FROM Persona WHERE respuesta1 =? AND respuesta2=?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs ;
            cmd.setString(1,cr.getRes1());
            cmd.setString(2,cr.getRes2());
            rs=cmd.executeQuery();
            if (rs.next()) {
                return true;
                
               } 
            else 
            {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error algo malo ha sucedido ", "Error Critico", JOptionPane.ERROR_MESSAGE);
        }
        return retorno;
    }
     
    
   public boolean actualizarContraseña(ConstructorRecuperacion cr)
    {
      boolean resp = false;
      try
      {
       String sql="UPDATE Persona SET clave=? WHERE usuario=? ";
       PreparedStatement cmd = cn.prepareStatement(sql);
      cmd.setString(1,cr.getClave());
      cmd.setString(2,cr.getUsuario());
    
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
    
   
   
  public DefaultComboBoxModel obtenerPreguntas (ConstructorRecuperacion cr)
  { 
      DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
          
            String sql = "SELECT pregunta1 FROM Pregunta pr , Persona ps  WHERE ps.idPregunta = pr.idPregunta  AND usuario = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs ;
            cmd.setString(1, cr.getUsuario());
            rs=cmd.executeQuery();
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
           
        } catch (Exception e) {
            System.out.println("Error combo"+e.toString());
        }
        return modelo;
  }   

  
   
  public DefaultComboBoxModel obtenerPreguntas2 (ConstructorRecuperacion cr)
  { 
      DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
          
            String sql = "SELECT pregunta1 FROM Pregunta pr , Persona ps  WHERE ps.idPregunta2 = pr.idPregunta  AND usuario = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs ;
            cmd.setString(1, cr.getUsuario());
            rs=cmd.executeQuery();
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
           
        } catch (Exception e) {
            System.out.println("Error combo"+e.toString());
        }
        return modelo;
  }   
  
   
     
}
