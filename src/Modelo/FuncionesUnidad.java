/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.*;

/**
 *
 * @author gdmer
 */
public class FuncionesUnidad extends Conexion {

    private Connection cn;
    private Integer id;
    private String modelo;
    private Integer capacidad;
    private Integer codigo;
    private String placas;
    private String fechaLanzamiento;
    private String fechaRegistro;
    private Integer idMarca;
    private Integer idTipo;
    private Integer idEstado;

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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public FuncionesUnidad() {
        Controlador.Conexion con = new Controlador.Conexion();
        cn = con.conectar();
    }

    public int idAumento() {
        int ids = 1;

        try {
            String sql = "SELECT Max(idUnidad) FROM Unidad";
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

    public DefaultComboBoxModel getMarca() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();

        try {
            Connection cn = this.conectar();
            String sql = "SELECT nombreMarca FROM Marca";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return modelo;
    }

    public DefaultComboBoxModel getTipo() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();

        try {
            Connection cn = this.conectar();
            String sql = "SELECT tipoUnidad FROM TipoUnidad";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }

    public DefaultComboBoxModel getEstado() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();

        try {
            Connection cn = this.conectar();
            String sql = "SELECT estadoUnidad FROM EstadoUnidad";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }

    public boolean ingresarUnidad() {
        boolean resp = false;

        try {
            String sql = "INSERT INTO Unidad(idUnidad, modelo, capacidad, codigo, placas, fechaLanzamiento, fechaRegistro, idMarca, idTipoUni, idEstadoUni) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            cmd.setString(2, modelo);
            cmd.setInt(3, capacidad);
            cmd.setInt(4, codigo);
            cmd.setString(5, placas);
            cmd.setString(6, fechaLanzamiento);
            cmd.setString(7, fechaRegistro);
            cmd.setInt(8, idMarca);
            cmd.setInt(9, idTipo);
            cmd.setInt(10, idEstado);

            if (!cmd.execute()) {
                resp = true;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    public boolean actualizarUnidad() {
        boolean resp = false;
        try {
            String sql = "UPDATE Unidad SET modelo = ?, capacidad = ?, codigo = ?, placas = ?, fechaLanzamiento = ?, idMarca = ?, idTipoUni = ?, idEstadoUni = ? WHERE idUnidad = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, modelo);
            cmd.setInt(2, capacidad);
            cmd.setInt(3, codigo);
            cmd.setString(4, placas);
            cmd.setString(5, fechaLanzamiento);
            cmd.setInt(6, idMarca);
            cmd.setInt(7, idTipo);
            cmd.setInt(8, idEstado);
            cmd.setInt(9, id);

            if (!cmd.execute()) {
                resp = true;
            }
        } catch (SQLException e) {
            System.out.println(e.toString()+"Error de Update");
        }
        return resp;
    }

    public boolean eliminarUnidad() {
        boolean resp = false;

        try {
            String sql = "UPDATE Unidad SET idEstadoUni = ? WHERE idUnidad = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, getIdEstado());
            cmd.setInt(2, getId());
            if (!cmd.execute()) {
                resp = true;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    public boolean consultarUnidad() {
        boolean resp = false;

        try {
            String sql = "SELECT idUnidad, modelo, capacidad, codigo, placas, fechaLanzamiento, fechaRegistro, idMarca, idTipoUni, idEstadoUni FROM Unidad WHERE idUnidad = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                resp = true;

                id = rs.getInt(1);
                modelo = rs.getString(2);
                capacidad = rs.getInt(3);
                codigo = rs.getInt(4);
                placas = rs.getString(5);
                fechaLanzamiento = rs.getString(6);
                fechaRegistro = rs.getString(7);
                idMarca = rs.getInt(8);
                idTipo = rs.getInt(9);
                idEstado = rs.getInt(10);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    public void listar(Connection cn, JTable tabla) {
        DefaultTableModel model = new DefaultTableModel();
        String[] columnas = {"ID", "Modelo", "Capacidad", "Codigo", "Placas", "Fecha Lanzamiento", "Fech Registro", "Marca", "Tipo", "Estado"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT dr.idUnidad, modelo, capacidad, codigo, placas, fechaLanzamiento, fechaRegistro, r.nombreMarca, u.tipoUnidad, c.estadoUnidad FROM Unidad dr, Marca r, TipoUnidad u, EstadoUnidad c WHERE dr.idMarca = r.idMarca and dr.idUnidad = u.idTipoUni and dr.idUnidad = c.idEstadoUni ORDER BY idUnidad";
        String[] filas = new String[10];
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < 10; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla");
        }
    }

    public java.util.Date StringADate(String fecha) {
        SimpleDateFormat formato_del_texto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaE = null;
        try {
            fechaE = (Date) formato_del_texto.parse(fecha);
            return fechaE;

        } catch (ParseException e) {
            return null;
        }
    }

    public void cargarUnidad(JTable tabla) {
        listar(cn, tabla);
    }

    public Integer getIdMarca(String nombre) {
        Integer id = 0;

        try {
            String sql = "SELECT idMarca FROM Marca where nombreMarca = ?;";
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

    public Integer getIdTipo(String nombre) {
        Integer id = 0;

        try {
            String sql = "SELECT idTipoUni FROM TipoUnidad where tipoUnidad = ?;";
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
            String sql = "SELECT idEstadoUni FROM EstadoUnidad where estadoUnidad = ?;";
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

    public String itemMarca() {
        String item = "";

        try {
            String sql = "SELECT nombreMarca FROM Marca ts, Unidad s WHERE ts.idMarca = s.idMarca AND s.idUnidad = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return item;
    }

    public String itemTipo(int codigo) {
        String item = "";

        try {
            String sql = "SELECT tipoUnidad FROM TipoUnidad ts, Unidad s WHERE ts.idTipoUni = s.idTipoUni AND s.idUnidad =  ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return item;
    }

    public String itemEstado(int codigo) {
        String item = "";

        try {
            String sql = "SELECT estadoUnidad FROM EstadoUnidad ts, Unidad s WHERE ts.idEstadoUni = s.idEstadoUni AND s.idUnidad =  ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return item;
    }

    public boolean darBaja() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
