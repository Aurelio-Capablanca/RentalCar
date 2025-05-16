/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author samue
 */
public class FuncionesPreguntas {
    private Connection cn;
    private Integer codigo;
    private String pregunta;

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

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the pregunta
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    public FuncionesPreguntas() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    //Guardar Pregunta
    public boolean guardarPregunta() {
        boolean resp = false;
        try {
        String sql = "INSERT INTO Pregunta (idPregunta, pregunta1) VALUES (?, ?)";
        PreparedStatement cmd = cn.prepareStatement(sql);
        cmd.setInt(1, codigo);
        cmd.setString(2, pregunta);
        if (!cmd.execute()) {
                resp = true;
            }
        cmd.close();
            cn.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    } 
    // Actualizar Pregunta
    public boolean actualizarPregunta() {
        boolean resp = false;
        try {
        String sql = " UPDATE Pregunta SET pregunta1 = ?  WHERE idPregunta = ?";
        PreparedStatement cmd = cn.prepareStatement(sql);
        cmd.setString(1, pregunta);
        cmd.setInt(2, codigo);
        if (!cmd.execute()) {
                resp = true;
            }
        cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
     //Consultar Pregunta
    public boolean consultarPregunta() {
        boolean resp = false;
        try {
            String sql = "SELECT idPregunta, pregunta1 FROM Pregunta WHERE idPregunta = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                resp = true;
                
                codigo = rs.getInt(1);
                pregunta = rs.getString(2);

            }
            cmd.close();
            cn.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
}
