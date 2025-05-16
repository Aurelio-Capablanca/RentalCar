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
public class FuncionesTipoViaje {
    private Connection cn;
    private Integer codigo;
    private String nombre;

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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public FuncionesTipoViaje() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    //Guardar TipoViaje
    public boolean guardarTipoViaje() {
        boolean resp = false;
        try {
        String sql = "INSERT INTO TipoViaje (idTipoViaje, tipoViaje) VALUES (?, ?)";
        PreparedStatement cmd = cn.prepareStatement(sql);
        cmd.setInt(1, codigo);
        cmd.setString(2, nombre);
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
    // Actualizar TipoViaje
    public boolean actualizarTipoViaje() {
        boolean resp = false;
        try {
        String sql = " UPDATE TipoViaje SET tipoViaje = ?  WHERE idTipoViaje = ?";
        PreparedStatement cmd = cn.prepareStatement(sql);
        cmd.setString(1, nombre);
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
    //Consultar TipoViaje
    public boolean consultarTipoViaje() {
        boolean resp = false;
        try {
            String sql = "SELECT idTipoViaje, tipoViaje FROM TipoViaje WHERE idTipoViaje = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                resp = true;
                
                codigo = rs.getInt(1);
                nombre = rs.getString(2);

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
