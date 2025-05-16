/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author samue
 */
public class FuncionesViaje {
    private Connection cn;
    private Integer id;
    private String direccion;
    private Double precio;
    private String fSalida;
    private String fRetorno;
    private String fRegistro;
    private Integer idPersona;
    private Integer idUnidad;
    private Integer idCliente;
    private Integer idDepartamento;
    private Integer idTipoViaje;

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
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the fSalida
     */
    public String getfSalida() {
        return fSalida;
    }

    /**
     * @param fSalida the fSalida to set
     */
    public void setfSalida(String fSalida) {
        this.fSalida = fSalida;
    }

    /**
     * @return the fRetorno
     */
    public String getfRetorno() {
        return fRetorno;
    }

    /**
     * @param fRetorno the fRetorno to set
     */
    public void setfRetorno(String fRetorno) {
        this.fRetorno = fRetorno;
    }

    /**
     * @return the fRegistro
     */
    public String getfRegistro() {
        return fRegistro;
    }

    /**
     * @param fRegistro the fRegistro to set
     */
    public void setfRegistro(String fRegistro) {
        this.fRegistro = fRegistro;
    }

    /**
     * @return the idPersona
     */
    public Integer getIdPersona() {
        return idPersona;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @return the idUnidad
     */
    public Integer getIdUnidad() {
        return idUnidad;
    }

    /**
     * @param idUnidad the idUnidad to set
     */
    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    /**
     * @return the idCliente
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the idDepartamento
     */
    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    /**
     * @param idDepartamento the idDepartamento to set
     */
    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    /**
     * @return the idTipoViaje
     */
    public Integer getIdTipoViaje() {
        return idTipoViaje;
    }

    /**
     * @param idTipoViaje the idTipoViaje to set
     */
    public void setIdTipoViaje(Integer idTipoViaje) {
        this.idTipoViaje = idTipoViaje;
    }
    
    public FuncionesViaje() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    //Mantenimientos
    
    //Guardar Viaje
    public boolean guardarViaje() {
        boolean resp = false;
        try {
            String sql = "INSERT INTO Viaje (idViaje, direccion, precio, horaSalida, horaRetorno, fechaRegistro,"
                    + "idPersona,idUnidad,idCliente,idDepartamento,idTipoViaje)" 
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            cmd.setString(2, direccion);
            cmd.setDouble(3, precio);
            cmd.setString(4, fSalida);
            cmd.setString(5, fRetorno);
            cmd.setString(6, fRegistro);
            cmd.setInt(7, idPersona);
            cmd.setInt(8, idUnidad);
            cmd.setInt(9, idCliente);
            cmd.setInt(10, idDepartamento);
            cmd.setInt(11, idTipoViaje);
            
            if (!cmd.execute()) {
                resp = true;
            }
//            cmd.close();
//            cn.close();
        }
        catch (Exception e) {
            System.out.println("Error en insert  "+e.toString());
        }
        return resp;
    }
    //Actualizar Viaje
    public boolean modificarViaje() {
        boolean resp = false;
        try {
            String sql = "UPDATE Viaje SET direccion = ?, precio = ?, horaSalida = ?, horaRetorno = ?, idPersona = ?, idUnidad = ?, idCliente = ?,"
                    + " idDepartamento = ?, idTipoViaje = ? WHERE idViaje = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, direccion);
            cmd.setDouble(2, precio);
            cmd.setString(3, fSalida);
            cmd.setString(4, fRetorno);
            cmd.setInt(5, idPersona);
            cmd.setInt(6, idUnidad);
            cmd.setInt(7, idCliente);
            cmd.setInt(8, idDepartamento);
            cmd.setInt(9, idTipoViaje);
             cmd.setInt(10, id);
            
            if (!cmd.execute()) {
                resp = true;
            }
//            cmd.close();
//            cn.close();
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    //Consultar Viaje
    public boolean consultarViaje() {
        boolean resp = false;
        try {
            String sql = "SELECT idViaje, direccion, precio, horaSalida, horaRetorno, fechaRegistro, idPersona, idUnidad, idCliente , idDepartamento, idTipoViaje FROM Viaje WHERE idViaje = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                resp = true;
                
                id = rs.getInt(1);
                direccion = rs.getString(2);
                precio = rs.getDouble(3);
                fSalida = rs.getString(4);
                fRetorno = rs.getString(5);
                fRegistro = rs.getString(6);
                idPersona = rs.getInt(7);
                idUnidad = rs.getInt(8);
                idCliente = rs.getInt(9);
                idDepartamento = rs.getInt(10);
                idTipoViaje = rs.getInt(11);
            }
////            cmd.close();
////            cn.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    //Llenado de Comboboxes
    public DefaultComboBoxModel getpersona() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.getCn();
            String sql = "SELECT nombres FROM Persona Where idTipoPersonal=5";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
//            rs.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }
    public DefaultComboBoxModel getunidad() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.getCn();
            String sql = "SELECT modelo FROM Unidad";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
//            rs.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }
    public DefaultComboBoxModel getcliente() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.getCn();
            String sql = "SELECT nombre FROM Cliente";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
//            rs.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }
    public DefaultComboBoxModel getdepartamento() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.getCn();
            String sql = "SELECT departamento FROM Departamento";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
//            rs.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }
    public DefaultComboBoxModel gettipoviaje() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            Connection cn = this.getCn();
            String sql = "SELECT tipoViaje FROM TipoViaje";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelo.addElement(rs.getString(1));
            }
//            rs.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        return modelo;
    }
    //ID Incremental
    public Integer idIncremental() {

        int ids = 1;

        try {
            String sql = "SELECT MAX(idViaje) FROM Viaje";
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
    //Formato de Fechas
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

    public String getFecha(JDateChooser jd) {
        if (jd.getDate() != null) {
            return formato.format(jd.getDate());

        } else {
            return null;
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
    
    
    
    
    
    
    //Obtener Id para combobox
    public Integer getIdPersona(String nombre){
        Integer id = 0;
        try {
            String sql = "SELECT idPersona FROM Persona where nombres = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
    public Integer getIdCliente(String nombre){
        Integer id = 0;
        try {
            String sql = "SELECT idCliente FROM Cliente where nombre = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
    public Integer getIdDepartamento(String nombre){
        Integer id = 0;
        try {
            String sql = "SELECT idDepartamento FROM Departamento where departamento = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
    public Integer getIdTipoViaje(String nombre){
        Integer id = 0;
        try {
            String sql = "SELECT idTipoViaje FROM TipoViaje where tipoViaje = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
    
    public String SeleccionarItemPersona()
  {
     String item = "";
            try {
                String sql = "SELECT nombres FROM Persona tc, Viaje cl WHERE tc.idPersona = cl.idPersona AND cl.idViaje = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1,id);
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println("Error Select "+e.toString());
            }
            return item;
  
  }
    
    
    public String seleccionarItemUnidad()
  {
     String item = "";
            try {
                String sql = "SELECT modelo FROM Unidad tc, Viaje cl WHERE tc.idUnidad = cl.idUnidad AND cl.idViaje = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1,id);
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println("Error Select "+e.toString());
            }
            return item;
  
  }
   
    
     public String seleccionarItemCliente()
  {
     String item = "";
            try {
                String sql = "SELECT nombre FROM Cliente tc, Viaje cl WHERE tc.idCliente = cl.idCliente AND cl.idViaje = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1,id);
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println("Error Select "+e.toString());
            }
            return item;
  
  }
     
     
     public String seleccionarItemDepartamento()
  {
     String item = "";
            try {
                String sql = "SELECT departamento FROM Departamento tc, Viaje cl WHERE tc.idDepartamento = cl.idDepartamento AND cl.idViaje = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1,id);
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println("Error Select "+e.toString());
            }
            return item;
  
  }
     
     
     public String seleccionarItemTipoViaje()
  {
     String item = "";
            try {
                String sql = "SELECT tipoViaje FROM TipoViaje tc, Viaje cl WHERE tc.idTipoViaje = cl.idTipoViaje AND cl.idViaje = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1,id);
                ResultSet rs = cmd.executeQuery();
                
                while(rs.next()){
               item = rs.getString(1);
                }
            
            } catch (Exception e) {
                System.out.println("Error Select "+e.toString());
            }
            return item;
  
  }

   
    
}
