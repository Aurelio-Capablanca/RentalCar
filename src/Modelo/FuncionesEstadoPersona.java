/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.*;
import Modelo.*;
import Controlador.ConstructorTipoPersona;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alber
 */
public class FuncionesEstadoPersona {

    public Connection cn;

    //Costructor para la conexion 
    public FuncionesEstadoPersona() {
        //Establecemos la conexion
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Integer idIncremental() {

        int ids = 1;

        try {
            String sql = "SELECT MAX(idEstadoPersonal) FROM EstadoPersona";
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

    //Creamos el metodo para Ingresar
    public boolean agregarEstadoPersona(ConstructorEstadoPersona per) {
        boolean retorno = false;
        //Realizamos la consulta INSERT

        try {
            String sql = "INSERT INTO EstadoPersona (idEstadoPersonal, estadoPersonal)" + "VALUES (?, ?)";
            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los parametros de la clase
            cmd.setInt(1, per.getId());
            cmd.setString(2, per.getEstado());
            if (!cmd.execute()) {
                retorno = true;
                return retorno;
            }

            //Cerramos la conexion
//            cmd.close();
//            cn.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return retorno;
    }

    //Creamos el metodo para Actualizar
    public boolean actualizarEstadoPersona(ConstructorEstadoPersona per) {
        boolean retorno = false;
        //Realizamos la consulta UPDATE

        try {
            String sql = "UPDATE EstadoPersona SET estadoPersonal = ? WHERE idEstadoPersonal = ?;";

            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los parametros de la clase
            cmd.setString(1, per.getEstado());
            cmd.setInt(2, per.getId());

            if (!cmd.execute()) {
                retorno = true;
            }

            //Cerramos la conexion
//            cmd.close();
//            cn.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error algo malo ha sucedido " + e);
        }
        return retorno;
    }
    
    public boolean mostrarEstadoPersona(ConstructorEstadoPersona per) {
        boolean retorno = false;

        //Realizamos la consulta SELECT
        try {
            String sql = "SELECT idEstadoPersonal, estadoPersonal FROM EstadoPersona WHERE idEstadoPersonal = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los parametros
            cmd.setInt(1, per.getId());            

            //Ejecutamos la consulta
            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                retorno = true;

                //Asignacion de atributos
                per.setId(rs.getInt(1));
                per.setEstado(rs.getString(2));
            }

            //Cerramos la conexion
//            cmd.close();
//            cn.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error algo malo ha sucedido " + e);
        }
        return retorno;
    }

}
