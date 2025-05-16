/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ConstructorReiniciarClave;
import Controlador.Conexion;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author alber
 */
public class FuncionesReinicioClave {

    public static Connection cn;
    static ResultSet rs;

    //Establecemos la conexion
    public FuncionesReinicioClave() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public boolean obtenerCodigo(ConstructorReiniciarClave con) {
        boolean conf = false;

        try {
            String sql = "SELECT * FROM Persona WHERE codigoConfirmacion = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);

            cmd.setString(1, con.getConfirmacion());

            rs = cmd.executeQuery();

            if (rs.next()) {
                return true;

            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error Critico" + e);
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en la obtención de datos", "Error en la obtención de datos", JOptionPane.ERROR_MESSAGE);
        }
        return conf;
    }

    public boolean obtenerClaveGenerica(ConstructorReiniciarClave con) {
        boolean data = false;

        try {

            String sql = "SELECT * FROM Persona WHERE usuario = ? AND clave = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);

            cmd.setString(1, con.getUsuario());
            cmd.setString(2, con.getClave());

            rs = cmd.executeQuery();

            if (rs.next()) {
                return true;

            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error Critico" + e);
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en la obtención de datos", "Error en la obtención de datos", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }

}
