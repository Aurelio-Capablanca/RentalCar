/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.ConstructorCliente;
import Controlador.ConstructorPersona;
import Controlador.ConstructorTipoPersona;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Alberto Ramírez
 */
public class FuncionesPersona extends Conexion {

    Connection cn;

    //Objetos
    //ConstructorPersona per = new ConstructorPersona();
    //Creamos un constructor para establecer la conexion
    public FuncionesPersona() {
        //Establecemos la conexion
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Integer idIncremental() {

        int ids = 1;

        try {
            String sql = "SELECT MAX(idPersona) FROM Persona";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                ids = rs.getInt(1) + 1;
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return ids;
    }

    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

    public String getFecha(JDateChooser jd) {
        if (jd.getDate() != null) {
            return formato.format(jd.getDate());

        } else {
            return null;
        }
    }

    public java.util.Date StringADate(String fecha) {
        SimpleDateFormat formato_del_texto = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaE = null;
        try {
            fechaE = (Date) formato_del_texto.parse(fecha);
            return fechaE;

        } catch (ParseException e) {
            return null;
        }
    }

    public String getItemTipoPersonas(int codigo) {
        String item = "";
        try {
            String sql = "SELECT ttp.tipoPersonal From TipoPersona ttp, Persona tp WHERE ttp.idTipoPersonal = tp.idPersona AND tp.idTipoPersonal = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);

            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return item;
    }

    public String getItemTipoPersona(int codigo) {
        String item = "";
        try {
            String sql = "SELECT ttp.tipoPersonal From TipoPersona ttp, Persona tp WHERE ttp.idTipoPersonal = tp.idPersona AND tp.idPersona = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);

            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return item;
    }

    public String getItemEstadoPersonas(int codigo) {
        String item = "";
        try {
            String sql = "SELECT tep.estadoPersonal FROM EstadoPersona tep, Persona tp WHERE tep.idEstadoPersonal = tp.idPersona AND tp.idEstadoPersonal = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);

            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return item;
    }

    public String getItemEstadoPersona(int codigo) {
        String item = "";
        try {
            String sql = "SELECT tep.estadoPersonal FROM EstadoPersona tep, Persona tp WHERE tep.idEstadoPersonal = tp.idPersona AND tp.idPersona = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);

            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return item;
    }

    public String getItemEmpresas(int codigo) {
        String item = "";
        try {
            String sql = "SELECT te.nombre FROM Empresa te, Persona tp WHERE te.idEmpresa = tp.idPersona AND tp.idEmpresa = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);

            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return item;
    }

    public String getItemEmpresa(int codigo) {
        String item = "";
        try {
            String sql = "SELECT te.nombre FROM Empresa te, Persona tp WHERE te.idEmpresa = tp.idPersona AND tp.idPersona = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);

            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                item = rs.getString(1);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return item;
    }

    public String getItemLicencia(int codigo) {
        String item = "";
        try {

            String sql = "SELECT li.licencia FROM Licencia li, Persona ps WHERE li.idLicencia = ps.idLicencia AND ps.idPersona = ?;";
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

    public DefaultComboBoxModel obtenerTipoLicenciaNull() {
        DefaultComboBoxModel modelotP = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT licencia FROM Licencia";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modelotP.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return modelotP;
    }

    public DefaultComboBoxModel obtenerTipoLicencia() {
        DefaultComboBoxModel modelotP = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT licencia FROM Licencia where idLicencia>1 ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modelotP.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return modelotP;
    }

    public Integer obtenerIdLicencia(String nombre) {
        Integer id = 0;

        try {

            String sql = "SELECT idLicencia FROM Licencia WHERE licencia = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);

            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return id;
    }

    public DefaultComboBoxModel obtenerTipoPersona() {
        DefaultComboBoxModel modelotP = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT tipoPersonal FROM TipoPersona Where idTipoPersonal>1 ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modelotP.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return modelotP;
    }

    public DefaultComboBoxModel obtenerTipoPersonaRoot() {
        DefaultComboBoxModel modelotP = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT tipoPersonal FROM TipoPersona";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modelotP.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return modelotP;
    }

    public Integer obteneridTipoPersona(String nombre) {
        Integer id = 0;

        try {

            String sql = "SELECT idTipoPersonal FROM TipoPersona WHERE tipoPersonal = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);

            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    public DefaultComboBoxModel obtenerEstadoPersona() {
        DefaultComboBoxModel modeloeP = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT estadoPersonal FROM EstadoPersona";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modeloeP.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return modeloeP;
    }

    public Integer obteneridEstadoPersona(String nombre) {
        Integer id = 0;

        try {

            String sql = "SELECT idEstadoPersonal FROM EstadoPersona WHERE estadoPersonal = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);

            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    public DefaultComboBoxModel obtenerEmpresa() {
        DefaultComboBoxModel modeloE = new DefaultComboBoxModel();
        try {
            Connection cn = this.conectar();
            String sql = "SELECT nombre FROM Empresa";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modeloE.addElement(rs.getString(1));
            }
            cn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return modeloE;
    }

    public Integer obteneridEmpresa(String nombre) {
        Integer id = 0;

        try {

            String sql = "SELECT idEmpresa FROM Empresa WHERE nombre = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);

            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    //Creamos el metodo para ingresar
    public boolean agregarPersona(ConstructorPersona per) {
        boolean retorno = false;

        //Realizamos la consulta INSERT
        try {
            String sql = "INSERT INTO Persona (idPersona, nombres, apellidos, fechaNacimiento, direccion, telefono, dui, correo, usuario, clave, nSeguro, fechaRegistro, codigoConfirmacion, idLicencia, idEstadoPersonal, idTipoPersonal, idEmpresa)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //Se pasan por referencia de seguridad       

            //Se debe importar la claser PreparedStatament
            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los parametros de la clase, se coloca en el orden de la tabla
            cmd.setInt(1, per.getCodigo());//Codigo es como se definio en la clase, aunque en la base se llama codigo_proyecto
            cmd.setString(2, per.getNombres());
            cmd.setString(3, per.getApellidos());
            cmd.setString(4, per.getNacimiento());
            cmd.setString(5, per.getDireccion());
            cmd.setString(6, per.getTelefono());
            cmd.setString(7, per.getDui());
            cmd.setString(8, per.getCorreo());
            cmd.setString(9, per.getUsuario());
            cmd.setString(10, per.getClave());
            cmd.setString(11, per.getnSeguro());
            cmd.setString(12, per.getRegistro());
            cmd.setInt(13, per.getConfirmacion());
            cmd.setInt(14, per.getIdLicencia());
            cmd.setInt(15, per.getIdEstado());
            cmd.setInt(16, per.getIdTipo());
            cmd.setInt(17, per.getIdEmpresa());

            //Si da error devuelce 1, en caso contrario 0
            //Tomar en cuenta el "!" de negacion
            if (!cmd.execute()) {
                retorno = true;
            }

            //Cerrando la conexion
//            cmd.close();
//            cn.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return retorno;
    }

    public boolean agregarPersona2(ConstructorPersona per) {

        boolean retorno = false;

        try {

            String sql = "INSERT INTO Persona (idPersona, nombres, apellidos, fechaNacimiento, direccion, telefono, dui, correo, licencia, nSeguro, fechaRegistro, idEstadoPersonal, idTipoPersonal, idEmpresa)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //Se pasan por referencia de seguridad       

            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los parametros de la clase, se coloca en el orden de la tabla
            cmd.setInt(1, per.getCodigo());//Codigo es como se definio en la clase, aunque en la base se llama codigo_proyecto
            cmd.setString(2, per.getNombres());
            cmd.setString(3, per.getApellidos());
            cmd.setString(4, per.getNacimiento());
            cmd.setString(5, per.getDireccion());
            cmd.setString(6, per.getTelefono());
            cmd.setString(7, per.getDui());
            cmd.setString(8, per.getCorreo());
            cmd.setString(9, per.getLicencia());
            cmd.setString(10, per.getnSeguro());
            cmd.setString(11, per.getRegistro());
            cmd.setInt(12, per.getIdEstado());
            cmd.setInt(13, per.getIdTipo());
            cmd.setInt(14, per.getIdEmpresa());

            if (!cmd.execute()) {
                retorno = true;
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);

        }
        return retorno;
    }

    //Creamos el metodo para Actualizar
    public boolean actualizarPersona(ConstructorPersona per) {
        boolean retorno = false;

        //Realizamos la consulta UPDATE
        try {
            String sql = "UPDATE Persona SET nombres = ?, apellidos = ?, fechaNacimiento = ?, direccion = ?, telefono = ?, dui = ?, correo = ?, usuario = ?, nSeguro = ?, fechaRegistro = ?, idLicencia = ?, idEstadoPersonal = ?, idTipoPersonal = ?, idEmpresa = ? WHERE idPersona = ?;";

            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los paramtros como estan en la clase
            cmd.setString(1, per.getNombres());
            cmd.setString(2, per.getApellidos());
            cmd.setString(3, per.getNacimiento());
            cmd.setString(4, per.getDireccion());
            cmd.setString(5, per.getTelefono());
            cmd.setString(6, per.getDui());
            cmd.setString(7, per.getCorreo());
            cmd.setString(8, per.getUsuario());
            cmd.setString(9, per.getnSeguro());
            cmd.setString(10, per.getRegistro());
            cmd.setInt(11, per.getIdLicencia());
            cmd.setInt(12, per.getIdEstado());
            cmd.setInt(13, per.getIdTipo());
            cmd.setInt(14, per.getIdEmpresa());
            cmd.setInt(15, per.getCodigo());
            //Si da error devuelve 1, en caso contrario 0
            //Tomar en cuenta el "!" de negacion
            if (!cmd.execute()) {
                retorno = true;
            }
//            cmd.close();
//            cn.close();

        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return retorno;
    }

    //Creamos el metodo para Eliminar
    public boolean darBajaPersona1(ConstructorPersona per) {
        boolean retorno = false;

        //Realizamos la consulta DELETE
        try {
            String sql = "DELETE FROM Persona WHERE idPersona = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenamos los parametros
            cmd.setInt(1, per.getCodigo());
            //Si da error decuelve 1, en caso contrario 0
            //Tomar en cuenta el "!" de negacion
            if (!cmd.execute()) {
                retorno = true;
            }
//            cmd.close();
//            cn.close();

        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return retorno;
    }

    public boolean darBaja(ConstructorPersona per) {
        boolean resp = false;
        try {
            String sql = "UPDATE Persona SET idEstadoPersonal=? WHERE idPersona=?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, per.getIdEstado());
            cmd.setInt(2, per.getCodigo());
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

    //Creamos metodo para Mostrar
    public boolean mostrarPersona(ConstructorPersona per) {
        boolean retorno = false;

        //Realizamos la consulta SELECT
        try {
            String sql = "SELECT idPersona, nombres, apellidos, fechaNacimiento, direccion, telefono, dui, correo, usuario, nSeguro, fechaRegistro, idLicencia, idEstadoPersonal, idTipoPersonal, idEmpresa FROM Persona WHERE idPersona = ?;";

            PreparedStatement cmd = cn.prepareStatement(sql);

            //Llenar los parametros
            cmd.setInt(1, per.getCodigo());

            //Ejecutar la consulta
            //Pedira importar la clase ResultSet
            ResultSet rs = cmd.executeQuery();

            //Recorrer la lista de registros
            if (rs.next()) {
                retorno = true;

                //Asignamos los atributos de la clase
                per.setCodigo(rs.getInt(1));
                per.setNombres(rs.getString(2));
                per.setApellidos(rs.getString(3));
                per.setNacimiento(rs.getString(4));
                per.setDireccion(rs.getString(5));
                per.setTelefono(rs.getString(6));
                per.setDui(rs.getString(7));
                per.setCorreo(rs.getString(8));
                per.setUsuario(rs.getString(9));
                per.setnSeguro(rs.getString(10));
                per.setRegistro(rs.getString(11));
                per.setIdLicencia(rs.getInt(14));
                per.setIdTipo(rs.getInt(13));
                per.setIdEstado(rs.getInt(14));
                per.setIdEmpresa(rs.getInt(15));
            }
//            cmd.close();
//            cn.close();

        } catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error, algo malo ha sucedido ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return retorno;
    }

}
