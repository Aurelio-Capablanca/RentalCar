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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gdmer
 */
public class FuncionesDetalleRecurso {
    
    private Connection cn;
    private Integer id;
    private Integer idRecurso;
    private Integer idUnidad;
    
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
    
    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }
    
    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }
    
    public FuncionesDetalleRecurso(){
        Controlador.Conexion con = new Controlador.Conexion();
        cn = con.conectar();
    }
    
    public int idAumento(){
        int ids = 1;
        
        try{
            String sql = "SELECT Max(idDetalle) FROM DetalleRecurso";
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
    
    public DefaultComboBoxModel getRecurso(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        try {
            String sql = "SELECT nombreRecurso FROM Recurso";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return modelo;        
    }
    
    public DefaultComboBoxModel getUnidad(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        try {
            String sql = "SELECT modelo FROM Unidad";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }
    
    public boolean ingresarDetalle(){
        boolean resp = false;
        
        try {
            String sql = "INSERT INTO DetalleRecurso(idDetalle, idRecurso, idUnidad) VALUES (?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            cmd.setInt(2, idRecurso);
            cmd.setInt(3, idUnidad);
               
            if (!cmd.execute()) {
            resp = true;
            }           
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    
    public boolean actualizarDetalle(){
        boolean resp = false;
        try {
            String sql = "UPDATE DetalleRecurso SET idRecurso = ?, idUnidad = ? WHERE  idDetalle = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, idRecurso);
            cmd.setInt(2, idUnidad);
            cmd.setInt(3, id);
               
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
    
    public boolean eliminarDetalle(){
        boolean resp = false;
   
        try{
            String sql = "DELETE FROM DetalleRecurso WHERE idDetalle = ?";
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
    
    public boolean consultarDetalle(){
        boolean resp = false;
    
        try {
            String sql = "SELECT idDetalle, idRecurso, idUnidad FROM DetalleRecurso WHERE idDetalle = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1,id);
            ResultSet rs = cmd.executeQuery();
        
            if (rs.next()) {
                resp = true;
                id = rs.getInt(1);
                idRecurso = rs.getInt(2);
                idUnidad = rs.getInt(3);   
            }                
        } catch (Exception e) {
            System.out.println(e.toString());
        }                  
        return resp;
    }
    
    public void listar(Connection cn, JTable tabla){
        DefaultTableModel model = new DefaultTableModel();
        String [] columnas = {"ID", "Recurso", "Unidad"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT dr.idDetalle, r.nombreRecurso, u.modelo FROM  DetalleRecurso dr, Recurso r, Unidad u WHERE dr.idUnidad=u.idUnidad and dr.idRecurso = r.idRecurso  and u.idUnidad = u.idUnidad ";
        String [] filas = new String[3];
        Statement st = null;
        ResultSet rs = null;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                for (int i = 0; i < 3; i++) {
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
    
    public void cargarDetalle(JTable tabla){
        listar(cn, tabla);
    }
    
    public Integer getIdRecurso(String nombre){
        Integer id = 0;
        
        try {
            String sql = "SELECT idRecurso FROM Recurso where nombreRecurso = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
    
    public Integer getIdUnidad(String nombre){
        Integer id = 0;
        
        try {
            String sql = "SELECT idUnidad FROM Unidad where modelo = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
    
    public String itemRecurso(){
        String item = "";
    
        try {
            String sql = "SELECT nombreRecurso FROM Recurso ts, DetalleRecurso s WHERE ts.idRecurso = s.idRecurso AND s.idDetalle =  ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
               
            while(rs.next()){
            item = rs.getString(1);
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return item;
    }
    
    public String itemUnidad(){
            String item = "";
            try {
                String sql = "SELECT modelo FROM Unidad ts, DetalleRecurso s WHERE ts.idUnidad = s.idUnidad AND s.idDetalle =  ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1, id);
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return item;
        }

    public void setModelo(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
