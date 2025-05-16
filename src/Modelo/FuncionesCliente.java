/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.ConstructorCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author adalb
 */
public class FuncionesCliente extends Conexion {

    public Connection cn;

    public FuncionesCliente() {

        Conexion sn = new Conexion();
        cn = sn.conectar();
    }

    public boolean guardarCliente(ConstructorCliente mc) {
        boolean resp = false;
        try {
            String sql = "INSERT INTO Cliente(idCliente,nombre,apellido,representanteLegal,telefono,direccion,nit,fechaRegistro,idTipoCliente,idEstadoCliente) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, mc.getId());
            cmd.setString(2, mc.getNombre());
            cmd.setString(3, mc.getApellido());
            cmd.setString(4, mc.getRepresentante());
            cmd.setString(5, mc.getTelefono());
            cmd.setString(6, mc.getDireccion());
            cmd.setString(7, mc.getNit());
            cmd.setString(8, mc.getFechas());
            cmd.setInt(9, mc.getTipo());
            cmd.setInt(10, mc.getEstado());

            if (!cmd.execute()) {
                resp = true;
            }
//       cmd.close();
//       cn.close();
        } catch (Exception e) {

            System.out.println("Error de inserción" + e.toString());
        }
        return resp;
    }

    public Integer obtenerIdTipo(String tipo) {
        Integer ids = 0;

        try {
            ConstructorCliente mc = new ConstructorCliente();
            String sql = "SELECT idTipoCliente FROM TipoCliente WHERE tipoCliente = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, tipo);

            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                ids = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error combo" + e.toString());
        }
        return ids;
    }

    public String seleccionarItemTipo(ConstructorCliente mc) {
        String item = "";
        try {
            String sql = "SELECT tipoCliente FROM TipoCliente tc, Cliente cl WHERE tc.idTipoCliente = cl.idTipoCliente AND cl.idCliente = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, mc.getId());
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println("Error Select " + e.toString());
        }
        return item;

    }

    public DefaultComboBoxModel getValues() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT tipoCliente FROM TipoCliente";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("error de obtención de combo\n" + e);
        }
        return modelo;
    }

    public Integer obtenerIdEstado(String estado) {
        Integer ids = 0;

        try {
            ConstructorCliente mc = new ConstructorCliente();
            String sql = "SELECT idEstadoCliente FROM EstadoCliente WHERE estadoCliente = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, estado);

            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                ids = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error combo" + e.toString());
        }
        return ids;
    }

    public String seleccionarItemEstado(ConstructorCliente mc) {
        String item = "";
        try {
            String sql = "SELECT estadoCliente FROM EstadoCliente tc, Cliente cl WHERE tc.idEstadoCliente = cl.idEstadoCliente AND cl.idCliente = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, mc.getId());
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println("Error Select " + e.toString());
        }
        return item;

    }

    public DefaultComboBoxModel getEstado() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT estadoCliente FROM EstadoCliente";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("error de obtención de combo\n" + e);
        }
        return modelo;
    }

    public boolean consultarCliente(ConstructorCliente mc) {
        boolean resp = false;
        try {
            String sql = "SELECT idCliente,nombre,apellido,representanteLegal,telefono,direccion,nit,fechaRegistro,idTipoCliente,idEstadoCliente FROM Cliente WHERE idCliente=? ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, mc.getId());
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                resp = true;
                mc.id = rs.getInt(1);
                mc.nombre = rs.getString(2);
                mc.apellido = rs.getString(3);
                mc.representante = rs.getString(4);
                mc.telefono = rs.getString(5);
                mc.direccion = rs.getString(6);
                mc.nit = rs.getString(7);
                mc.fechas = rs.getString(8);
                mc.Tipo = rs.getInt(9);
                mc.Estado = rs.getInt(10);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    public boolean actualizarCliente(ConstructorCliente mc) {
        boolean resp = false;
        try {
            String sql = "UPDATE Cliente SET nombre=?, apellido=?, representanteLegal=?, telefono=?,direccion=?,nit=?,idTipoCliente=?,idEstadoCliente=? WHERE idCliente=? ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, mc.getNombre());
            cmd.setString(2, mc.getApellido());
            cmd.setString(3, mc.getRepresentante());
            cmd.setString(4, mc.getTelefono());
            cmd.setString(5, mc.getDireccion());
            cmd.setString(6, mc.getNit());
            cmd.setInt(7, mc.getTipo());
            cmd.setInt(8, mc.getEstado());
            cmd.setInt(9, mc.getId());
            if (!cmd.execute()) {
                resp = true;
            }

//      cmd.close();
//      cn.close();  
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    public boolean darBaja(ConstructorCliente mc) {
        boolean resp = false;
        try {
            String sql = "UPDATE Cliente SET idEstadoCliente=? WHERE idCliente=?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, mc.getEstado());
            cmd.setInt(2, mc.getId());
            if (!cmd.execute()) {
                resp = true;
            }
//      cmd.close();
//       cn.close();
        } catch (Exception e) {
            System.out.println("Error al inhabilitar" + e);
        }
        return resp;
    }

    public boolean eliminarCliente(ConstructorCliente mc) {
        boolean resp = false;
        try {
            String sql = "DELETE FROM Cliente WHERE idCliente = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, mc.getId());
            if (!cmd.execute()) {
                resp = true;
            }
//            cmd.close();
//            cn.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    public boolean obtenerId(ConstructorCliente mc) {
        boolean resp = false;
        try {
            String sql = "SELECT Max(idCliente) as  FROM Cliente";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, mc.getId());
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                resp = true;
                mc.setId(rs.getInt(sql));
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    public int idIncremental() {
        int ids = 1;
        try {
            String sql = "SELECT Max(idCliente) FROM Cliente";
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
