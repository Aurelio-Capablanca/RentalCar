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
public class FuncionesTipoUnidad {
  
    private Connection cn;
    private Integer id;
    private String tipo;
    
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
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public FuncionesTipoUnidad(){
        Controlador.Conexion con = new Controlador.Conexion();
        cn = con.conectar();
    }
    
    public int idAumento(){
        int ids = 1;
        
        try{
            String sql = "SELECT Max(idTipoUni) FROM TipoUnidad";
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
    
    public boolean ingresarTipo(){
        boolean resp = false;
    
        try {
            String sql = "INSERT INTO TipoUnidad(idTipoUni, tipoUnidad ) VALUES (?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            cmd.setString(2, tipo);
               
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
    
    public boolean actualizarTipo(){
        boolean resp = false;

        try {
            String sql = "UPDATE TipoUnidad SET tipoUnidad = ? WHERE  idTipoUni = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);              
            cmd.setString(1, tipo);
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
    
    public boolean eliminarTipo(){
        boolean resp = false;
             
        try{
            String sql = "DELETE FROM TipoUnidad WHERE idTipoUni = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            if (!cmd.execute()) {
                resp = true;
            }
            cmd.close();
            cn.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return resp;
    }
    
    public boolean consultarTipo(){
        boolean resp = false;
        try {
            String sql = "SELECT idTipoUni, tipoUnidad FROM TipoUnidad WHERE idTipoUni = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1,id);
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                resp = true;
                id = rs.getInt(1);
                tipo = rs.getString(2);                
            }                
        } catch (Exception e) {
            System.out.println(e.toString());
        }                   
        return resp;
    }
    
    public void listar(Connection cn, JTable tabla){
        DefaultTableModel model = new DefaultTableModel();
        String [] columnas = {"ID", "Tipo Unidad"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT idTipoUni, tipoUnidad from TipoUnidad s WHERE s.tipoUnidad = s.tipoUnidad ORDER BY idTipoUni";
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
        
    public void cargarServicio(JTable tabla){
        listar(cn, tabla);
    }
}
