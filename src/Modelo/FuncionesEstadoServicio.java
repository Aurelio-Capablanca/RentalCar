/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Controlador.ConstructorEstadoServicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Administrador
 */
public class FuncionesEstadoServicio {
    private Connection cn;
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
    
    public FuncionesEstadoServicio(){
        Controlador.Conexion con = new Controlador.Conexion();
        cn = con.conectar();
    }
    
    public boolean guardar(ConstructorEstadoServicio obj ){
            boolean resp = false;
            try {
               String sql = "INSERT INTO EstadoServicio(idEstadoServicio, estadoServicio) VALUES (?,?)";
               PreparedStatement cmd = cn.prepareStatement(sql);
               cmd.setInt(1, obj.getIdEstadoS());
               cmd.setString(2, obj.getEsdtadoServicio());

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
    
        public boolean eliminar(ConstructorEstadoServicio obj){
            boolean resp = false;
            try {
                String sql = "DELETE FROM EstadoServicio WHERE idEstadoServicio = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1, obj.getIdEstadoS());
                
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
        
        public boolean actualizar(ConstructorEstadoServicio obj){
            boolean resp = false;
            try {
                String sql = "UPDATE EstadoServicio SET estadoServicio = ? WHERE idEstadoServicio = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setString(1, obj.getEsdtadoServicio());
                cmd.setInt(2, obj.getIdEstadoS());
                
                
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
        
        public boolean mostrar(ConstructorEstadoServicio obj){
          boolean resp = false;
          try {
              String sql = "SELECT idEstadoServicio, estadoServicio FROM EstadoServicio WHERE idEstadoServicio = ? ";
              PreparedStatement cmd = cn.prepareStatement(sql);
               cmd.setInt(1, obj.getIdEstadoS());
               ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                resp = true;
                obj.setIdEstadoS(rs.getInt(1));
                obj.setEsdtadoServicio(rs.getString(2));
            }
            
          } catch (Exception e) {
              System.out.println(e.toString());
          }
          return resp;
      }
    
    public void listar(Connection cn, JTable tabla){
        DefaultTableModel model = new DefaultTableModel();
        String [] columnas = {"ID", "Estado"};
        model = new DefaultTableModel(null, columnas);

        String sql = "Select * From EstadoServicio";
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
        
        public void CargarServicio(JTable tabla){
        listar(cn, tabla);
        }
        
         public int idIncremental(){
            int ids = 1;
            
            try {
                String sql = "SELECT Max(idEstadoServicio) FROM EstadoServicio";
                PreparedStatement cmd = cn.prepareStatement(sql);
                ResultSet rs = cmd.executeQuery();
                while(rs.next()){
                    ids = rs.getInt(1)+1;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return ids;
        }
    
}
