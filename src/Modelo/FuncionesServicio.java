/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ConstructorServicio;
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
public class FuncionesServicio {

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

    public FuncionesServicio() {
        Controlador.Conexion con = new Controlador.Conexion();
        cn = con.conectar();
    }

    public boolean guardarServicio(ConstructorServicio obj) {
        boolean resp = false;
        try {
            String sql = "INSERT INTO Servicio(idServicio, codigo, nombreServicio, descripcion, fechaRegistro, idTipoServicio, idEstadoServicio) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, obj.getId());
            cmd.setString(2, obj.getCodigo());
            cmd.setString(3, obj.getNombreSer());
            cmd.setString(4, obj.getDescripcion());
            cmd.setString(5, obj.getFecha());
            cmd.setInt(6, obj.getIdTipo());
            cmd.setInt(7, obj.getIdEstado());

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

    public boolean actualizarServicio(ConstructorServicio obj) {
        boolean resp = false;
        try {
            String sql = "UPDATE Servicio SET codigo = ?, nombreServicio = ?, descripcion = ?,  idTipoServicio = ?, idEstadoServicio = ? WHERE  idServicio = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);

            cmd.setString(1, obj.getCodigo());
            cmd.setString(2, obj.getNombreSer());
            cmd.setString(3, obj.getDescripcion());
            //cmd.setString(, obj.getFecha());
            cmd.setInt(4, obj.getIdTipo());
            cmd.setInt(5, obj.getIdEstado());
            cmd.setInt(6, obj.getId());

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

    public boolean darBaja(ConstructorServicio obj) {
        boolean resp = false;
        try {
            String sql = "UPDATE Servicio SET idEstadoServicio = ? WHERE idServicio = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, obj.getIdEstado());
            cmd.setInt(2, obj.getId());
            if (!cmd.execute()) {
                resp = true;
            }
//            cmd.close();
//            cn.close();
        } catch (Exception e) {
            System.out.println("Error al inhabilitar " + e);
        }
        return resp;
    }

    public boolean consultarServicio(ConstructorServicio obj) {
        boolean resp = false;
        try {
            String sql = "SELECT idServicio, codigo, nombreServicio, descripcion, idTipoServicio, idEstadoServicio FROM Servicio WHERE idServicio = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, obj.getId());
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                resp = true;

//                obj.setCodigo(rs.getString(1));
//                obj.setNombreSer(rs.getString(2));
//                obj.setDescripcion(rs.getString(3));
//                obj.setIdTipo(rs.getInt(4));
//                obj.setIdEstado(rs.getInt(5));
//                obj.setId(rs.getInt(6));
                obj.Id = rs.getInt(1);
                obj.codigo = rs.getString(2);
                obj.NombreSer = rs.getString(3);
                obj.Descripcion = rs.getString(4);
                obj.idEstado = rs.getInt(5);
                obj.idTipo = rs.getInt(6);

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return resp;
    }

    public void listar(Connection cn, JTable tabla) {
        DefaultTableModel model = new DefaultTableModel();
        String[] columnas = {"ID", "Codigo", "Nombre", "Descripcion", "Fecha", "TipoServicio", "Estado"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT idServicio, codigo, nombreServicio, descripcion, fechaRegistro, tipoServicio, estadoServicio from Servicio s, TipoServicio ts, EstadoServicio es WHERE s.idTipoServicio = ts.idTipoServicio and s.idEstadoServicio = es.idEstadoServicio ORDER BY idServicio";
        String[] filas = new String[7];
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < 7; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla");
        }
    }

    public void CargarServicio(JTable tabla) {
        listar(cn, tabla);
    }

    public Integer getIdTipo(String nombre) {
        Integer id = 0;
        try {
            String sql = "SELECT idTipoServicio FROM TipoServicio where tipoServicio = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);

            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public Integer getIdEstado(String nombre) {
        Integer id = 0;
        try {
            String sql = "SELECT idEstadoServicio FROM EstadoServicio where estadoServicio = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);

            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public String seleccionarItemTipo(ConstructorServicio obj) {
        String item = "";
        try {
            String sql = "SELECT tipoServicio FROM TipoServicio ts, Servicio s WHERE ts.idTipoServicio = s.idTipoServicio AND s.idServicio =  ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, obj.getId());
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return item;
    } 

    public String seleccionarItemEstado(ConstructorServicio obj) {
        String item = "";
        try {
            String sql = "SELECT estadoServicio FROM EstadoServicio ts, Servicio s WHERE ts.idEstadoServicio = s.idEstadoServicio AND s.idServicio = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, obj.getId());
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return item;
    }

    public int idIncremental() {
        int ids = 1;

        try {
            String sql = "SELECT Max(idServicio) FROM Servicio";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                ids = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ids;
    }

}
