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
public class FuncionesRecurso {
    private Connection cn;
    private Integer id;
    private String nombre;
    private Integer cantidad;
    private String fecha;
    
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
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public FuncionesRecurso(){
        Controlador.Conexion con = new Controlador.Conexion();
        cn = con.conectar();
    }
    
    public int idAumento(){
        int ids = 1;
        
        try{
            String sql = "SELECT Max(idRecurso) FROM Recurso";
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
    
    public boolean ingresarRecurso(){
        boolean resp = false;
    
        try {
            String sql = "INSERT INTO Recurso(idRecurso, nombreRecurso, cantidadRecurso, fechaRecurso) VALUES (?,?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            cmd.setString(2, nombre);
            cmd.setInt(3, cantidad);
            cmd.setString(4, fecha);

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
    
    public boolean actualizarRecurso(){
        boolean resp = false;

        try {
            String sql = "UPDATE Recurso SET nombreRecurso = ?, cantidadRecurso = ? WHERE  idRecurso = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);              
            cmd.setString(1, nombre);
            cmd.setInt(2, cantidad);
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
    
    public boolean consultarEstado(){
        boolean resp = false;
        try {
            String sql = "SELECT idRecurso, nombreRecurso, cantidadRecurso FROM Recurso WHERE idRecurso = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1,id);
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                resp = true;
                id = rs.getInt(1);
                nombre = rs.getString(2);    
                cantidad = rs.getInt(3);
            }                
        } catch (Exception e) {
            System.out.println(e.toString());
        }                   
        return resp;
    }
    
    public void listar(Connection cn, JTable tabla){
        DefaultTableModel model = new DefaultTableModel();
        String [] columnas = {"ID", "Nombre", "Cantidad", "Fecha Recurso"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT idRecurso, nombreRecurso, cantidadRecurso, fechaRecurso from Recurso";
        String [] filas = new String[4];
        Statement st = null;
        ResultSet rs = null;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                for (int i = 0; i < 4; i++) {
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
