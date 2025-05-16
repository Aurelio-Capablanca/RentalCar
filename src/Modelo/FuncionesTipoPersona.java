/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.ConstructorTipoPersona;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alber
 */
public class FuncionesTipoPersona {

    public Connection cn;

    //Objetos
    //ConstructorTipoPersona per = new ConstructorTipoPersona();    
    //Costructor para la conexion 
    public FuncionesTipoPersona() {
        //Establecemos la conexion
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Integer idIncremental() {

        int ids = 1;

        try {
            String sql = "SELECT MAX(idTipoPersonal) FROM TipoPersona";
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
    public boolean agregarTipoPersona(ConstructorTipoPersona per) {
        boolean retorno = false;
        //Realizamos la consulta INSERT

        try {
            String sql = "INSERT INTO TipoPersona (idTipoPersonal, tipoPersonal)" + "VALUES (?, ?)";
            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los parametros de la clase
            cmd.setInt(1, per.getId());
            cmd.setString(2, per.getTipo());
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
    public boolean actualizarTipoPersona(ConstructorTipoPersona per) {
        boolean retorno = false;
        //Realizamos la consulta UPDATE

        try {
            String sql = "UPDATE TipoPersona SET tipoPersonal = ? WHERE idTipoPersonal = ?;";

            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los parametros de la clase
            cmd.setString(1, per.getTipo());
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

    //Creamos el metodo para eliminar 
    public boolean bajaTipoPersona(ConstructorTipoPersona per) {
        boolean retorno = false;

        //Realizamos la consulta DELETE
        try {
            String sql = "DELETE FROM TipoPersona WHERE idTipoPersonal = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los parametros
            cmd.setInt(1, per.getId());

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

    //Creamos el metodo para eliminar 
    public boolean mostrarTipoPersona(ConstructorTipoPersona per) {
        boolean retorno = false;

        //Realizamos la consulta SELECT
        try {
            String sql = "SELECT idTipoPersonal, tipoPersonal FROM TipoPersona WHERE idTipoPersonal = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los parametros
            cmd.setInt(1, per.getId());            

            //Ejecutamos la consulta
            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                retorno = true;

                //Asignacion de atributos
                per.setId(rs.getInt(1));
                per.setTipo(rs.getString(2));
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
