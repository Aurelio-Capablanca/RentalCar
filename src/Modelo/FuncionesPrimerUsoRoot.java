/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.*;
import Modelo.*;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;

/**
 *
 * @author Alberto Ramirez
 */
public class FuncionesPrimerUsoRoot extends Conexion {

    Connection cn;
    ResultSet rs;

    //Creamos un constructor para establecer la conexion
    public FuncionesPrimerUsoRoot() {
        //Establecemos la conexion
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Integer idIncremental() {

        int ids = 1;

        try {
            String sql = "SELECT MAX(idPersona) FROM Persona";
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

    public Integer obtenerIdTipoPersonaRoot(String tipo) {
        Integer id = 0;

        try {
            String sql = "SELECT idTipoPersonal FROM TipoPersona WHERE tipoPersonal = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, tipo);

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

    public DefaultComboBoxModel obtenerTipoPersonaRoot() {
        DefaultComboBoxModel modelotP = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT tipoPersonal FROM TipoPersona WHERE idTipoPersonal = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modelotP.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return modelotP;
    }

    public Integer obtenerIdEmpresa(String tipo) {
        Integer id = 0;

        try {
            String sql = "SELECT idEmpresa FROM Empresa WHERE nombre = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, tipo);

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

    public DefaultComboBoxModel obtenerEmpresa() {
        DefaultComboBoxModel modeloE = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT nombre FROM Empresa";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modeloE.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return modeloE;
    }

    public boolean agregarRoot(ConstructorPrimerUso per) {
        boolean retorno = false;

        try {
            String sql = "INSERT INTO Persona (idPersona, nombres, apellidos, fechaNacimiento, direccion, telefono, dui, correo, usuario, clave, nSeguro, fechaRegistro, respuesta1, respuesta2, idPregunta, idPregunta2, idEstadoPersonal, idTipoPersonal, idEmpresa)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //Se pasan por referencia de seguridad       

            //Importamos la clase PreparedStatament
            PreparedStatement cmd = cn.prepareStatement(sql);

            //Pasamos los parametros necesarios para nuestros atributos
            cmd.setInt(1, per.getCodigo());
            cmd.setString(2, per.getNombres());
            cmd.setString(3, per.getApellidos());
            cmd.setString(4, per.getNacimiento());
            cmd.setString(5, per.getDireccion());
            cmd.setString(6, per.getTelefono());
            cmd.setString(7, per.getDui());
            cmd.setString(8, per.getCorreo());            
            cmd.setString(9, per.getUsuario());
            cmd.setString(10, per.getClave());
            cmd.setString(11, per.getnSeguro());
            cmd.setString(12, per.getRegistro());
            cmd.setString(13, per.getRespuesta());
            cmd.setString(14, per.getRespuesta2());
            cmd.setInt(15, per.getIdPregunta());
            cmd.setInt(16, per.getIdPregunta2());
            cmd.setInt(17, per.getIdEstado());
            cmd.setInt(18, per.getIdTipo());
            cmd.setInt(19, per.getIdEmpresa());

            if (!cmd.execute()) {
                retorno = true;

                JOptionPane.showMessageDialog(null, "Se han guardado sus datos exitosamente", "Usuario Root agregado", JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar guardar los datos", "Error al registrarse", JOptionPane.WARNING_MESSAGE);
            }

            //Cerrando la conexion
//            cmd.close();
//            cn.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return retorno;
    }

}
