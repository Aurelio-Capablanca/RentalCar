/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.ConstructorPrimerEmpresa;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author alber
 */
public class FuncionesPrimerEmpresa {

    private Connection cn;
    static ResultSet rs;
    Statement st;

    //Establecemos la conexion
    public FuncionesPrimerEmpresa() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Integer idIncremental() {

        int ids = 1;

        try {
            String sql = "SELECT MAX(idEmpresa) FROM Empresa";
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

    public boolean ingresarEmpresa(ConstructorPrimerEmpresa con) {

        boolean retorno = false;
        
        try {

            String sql2 = "INSERT INTO Empresa (idEmpresa, nombre, representanteLegal, telefono, ubicacion, nit, logo, fechaRegistro)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement cmd2 = cn.prepareStatement(sql2);

            cmd2.setInt(1, con.getIdEmpresa());
            cmd2.setString(2, con.getNombre());
            cmd2.setString(3, con.getRepresentante());
            cmd2.setString(4, con.getTelefono());
            cmd2.setString(5, con.getUbicacion());
            cmd2.setString(6, con.getNit());
            cmd2.setString(7, con.getLogo());
            cmd2.setString(8, con.getRegistro());
            
            if (!cmd2.execute()) {
                retorno = true;
            }
            
            //Cerrando la conexion
            cmd2.close();
            cn.close();

        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        
        return retorno;
    }

}
