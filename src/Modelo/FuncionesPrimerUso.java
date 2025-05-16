/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.ConstructorPrimerUso;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;

/**
 *
 * @author alber
 */
public class FuncionesPrimerUso extends Conexion{

    Connection cn;
    ResultSet rs;

    //Creamos un constructor para establecer la conexion
    public FuncionesPrimerUso() {
        //Establecemos la conexion
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public DefaultComboBoxModel obtenerPregunta1() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        try {
            Connection cn = this.conectar();
            String sql = "SELECT pregunta1 FROM Pregunta";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modelo.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Eror Critico", JOptionPane.ERROR_MESSAGE);
        }
        return modelo;
    }
    
    public Integer obtenerIdPregunta1(String pregunta) {
        Integer id = 0;
        
        try {
            
            String sql = "SELECT idPregunta FROM Pregunta WHERE pregunta1 = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, pregunta);
            
            ResultSet rs = cmd.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt(1);
            }
            
        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Error Critico", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
    
    public DefaultComboBoxModel obtenerPregunta2() {
        DefaultComboBoxModel modelo2 = new DefaultComboBoxModel();
        
        try {
            Connection cn = this.conectar();
            String sql = "SELECT pregunta1 FROM Pregunta";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modelo2.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Eror Critico", JOptionPane.ERROR_MESSAGE);
        }
        return modelo2;
    }
    
    public Integer obtenerIdPregunta2(String pregunta2) {
        Integer id = 0;
        
        try {
            
            String sql = "SELECT idPregunta FROM Pregunta WHERE pregunta1 = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, pregunta2);
            
            ResultSet rs = cmd.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt(1);
            }
            
        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Error Critico", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
    
    public boolean actualizarPersona(ConstructorPrimerUso per) {
        boolean retorno = false;
        
        try {
            
            String sql = "UPDATE Persona SET clave = ?, idPregunta = ?, idPregunta2 = ?, respuesta1 = ?, respuesta2 = ?, idEstadoPersonal = ? WHERE codigoConfirmacion = ?;";
            
            PreparedStatement cmd = cn.prepareStatement(sql);
            
            //Llenado de parametros
            
            cmd.setString(1, per.getClave());
            cmd.setInt(2, per.getIdPregunta());
            cmd.setInt(3, per.getIdPregunta2());
            cmd.setString(4, per.getRespuesta());
            cmd.setString(5, per.getRespuesta2());
            cmd.setInt(6, per.getIdEstado());
            cmd.setString(7, per.getConfirmacion());            
            
            if (!cmd.execute()) {
                retorno = true;
                
                JOptionPane.showMessageDialog(null, "Se han guardado sus datos correctamente", "Proceso de registro completado", JOptionPane.INFORMATION_MESSAGE);
           
            } else {
                
                 JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar guardar los datos", "Error al registrarse", JOptionPane.WARNING_MESSAGE);
            }                        
            
            
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return retorno;
    }
}
