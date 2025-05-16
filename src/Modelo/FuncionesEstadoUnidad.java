/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gdmer
 */
public class FuncionesEstadoUnidad {
 
     private Connection cn;
    private Integer id;
    private String estado;
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public FuncionesEstadoUnidad(){
        Controlador.Conexion con = new Controlador.Conexion();
        cn = con.conectar();
    }
    
    public int idAumento(){
        int ids = 1;
        
        try{
            String sql = "SELECT Max(idEstadoUni) FROM EstadoUnidad";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            
            while(rs.next()){
                ids = rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return ids;
    }
    
    public boolean ingresarEstado(){
        boolean resp = false;
    
        try {
            String sql = "INSERT INTO EstadoUnidad(idEstadoUni, estadoUnidad) VALUES (?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            cmd.setString(2, estado);
            System.out.println(id.toString());
            System.out.println(estado.toString());
               
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
    
    public boolean actualizarEstado(){
        boolean resp = false;

        try {
            String sql = "UPDATE EstadoUnidad SET estadoUnidad = ? WHERE  idEstadoUni = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);              
            cmd.setString(1, estado);
            cmd.setInt(2, id);
            
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
    
    public boolean consultarEstado(){
        boolean resp = false;
        try {
            String sql = "SELECT idEstadoUni, estadoUnidad FROM EstadoUnidad WHERE idEstadoUni = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1,id);
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                resp = true;
                id = rs.getInt(1);
                estado = rs.getString(2);                
            }                
        } catch (Exception e) {
            System.out.println(e.toString());
        }                   
        return resp;
    }
    
    public void listar(Connection cn, JTable tabla){
        DefaultTableModel model = new DefaultTableModel();
        String [] columnas = {"ID", "Estado Unidad"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT idEstadoUni, estadoUnidad from EstadoUnidad s WHERE s.estadoUnidad = s.estadoUnidad ORDER BY idEstadoUni";
        String [] filas = new String[2];
        Statement st = null;
        ResultSet rs = null;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                for (int i = 0; i < 2; i++) {
                    filas[i] = rs.getString(i+1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla");
        }
    }
        
    public void cargar(JTable tabla){
        listar(cn, tabla);
    }
    
}
