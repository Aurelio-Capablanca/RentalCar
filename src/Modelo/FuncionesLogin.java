/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ConstructorLogin;
import Controlador.Conexion;
import Controlador.ConstructorReiniciarClave;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author alber
 */
public class FuncionesLogin {

    public static Connection cn;
    static ResultSet rs;

    //Establecemos la conexion
    public FuncionesLogin() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public boolean capturarPersona() {
        boolean retorno = false;
        try {
            String sql = "SELECT * FROM Persona";
            PreparedStatement cmd = cn.prepareStatement(sql);
            rs = cmd.executeQuery();

            if (rs.next()) {
                return true;

            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error algo malo ha sucedido ", "Error Critico", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean capturarEmpresa() {
        boolean retorno = false;
        try {
            String sql = "SELECT * FROM Empresa";
            PreparedStatement cmd = cn.prepareStatement(sql);
            rs = cmd.executeQuery();

            if (rs.next()) {
                return true;

            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error algo malo ha sucedido ", "Error Critico", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean Login(ConstructorLogin login) {
        boolean retorno = false;
        try {

            String sql = "SELECT * FROM Persona WHERE usuario = ? AND clave = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);

            cmd.setString(1, login.getUsuario());
            cmd.setString(2, login.getClave());
            //cmd.setInt(3, login.getEstado());
            rs = cmd.executeQuery();

            if (rs.next()) {

                retorno = true;
                String sqlSelect2 = "SELECT * FROM Persona WHERE usuario = ? AND clave = ?";
                PreparedStatement cmd2 = cn.prepareStatement(sqlSelect2);

                cmd2.setString(1, login.getUsuario());
                cmd2.setString(2, login.getClave());
                //cmd2.setInt(3, login.nivel);
                rs = cmd.executeQuery();

                if (rs.next()) {
                    retorno = true;
                    login.setNivel(rs.getInt(20));

                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Credenciales incorrectas", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            System.err.println("Error en el boton Ingresar " + e);
            retorno = false;
        }
        return retorno;
    }

//    public boolean ObtenerClaveGen(ConstructorReiniciarClave contra) {
//        
//        boolean data = false;
//        try {
//            String sqlGenerar = "SELECT * FROM Persona WHERE contraseña = ? AND usuario = ?";
//            PreparedStatement cmdGen = cn.prepareStatement(sqlGenerar);
//            ResultSet rs2 = cmdGen.executeQuery();
//                       
//            cmdGen.setString(1, contra.clave);
//            cmdGen.setString(2, contra.usuario);
//            rs2 = cmdGen.executeQuery();
//            if (rs2.next()) {
//                data = true;
//                
//            }
//        } catch (Exception e) {
//            System.err.println("Error al obtener la clave " + e);
//            JOptionPane.showMessageDialog(null, "¡¡Error al iniciar sesión!!, contacte al administrador.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
}
