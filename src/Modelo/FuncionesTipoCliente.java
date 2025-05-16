/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.CostructorTipoCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author adalb
 */
public class FuncionesTipoCliente {
    
     public Connection cn; 

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
     
     public FuncionesTipoCliente()
     {
      Conexion sn= new Conexion();
       cn=sn.conectar();
     }
    
     
     
     public boolean guardarTipoCliente(CostructorTipoCliente tc)
     {
       boolean resp = false;
       try
       {
          String sql="INSERT INTO TipoCliente (idTipoCliente,tipoCliente)VALUES(?,?)";
           PreparedStatement cmd = cn.prepareStatement(sql); 
           cmd.setInt(1,tc.getId());
           cmd.setString(2,tc.getTipo());
         if(!cmd.execute())
         { 
         resp = true;
         }  
//         cmd.close();
//         cn.close();
       }
       catch(Exception e)
       {
        System.out.println("Error de inserción"+e.toString());
       }
       return resp;
     }
    
     
     public boolean consultarTipo(CostructorTipoCliente tc)
     {
       boolean resp = false;
      try
      {
        String sql="SELECT idTipoCliente,tipoCliente FROM TipoCliente WHERE idTipoCliente=? ";
        PreparedStatement cmd= cn.prepareStatement(sql);
        cmd.setInt(1,tc.getId());
        ResultSet rs= cmd.executeQuery();
        if(rs.next())
        {
          resp=true;
          tc.id=rs.getInt(1);
          tc.tipo=rs.getString(2);
         
        }
        
      }
      catch(Exception e)
      {
         System.out.println("Fallo de consulta"+e.toString());
      }
        return resp;
     }
     
     
     public boolean actualizarTipo(CostructorTipoCliente tc)
     {
       boolean resp = false;
      try
      {
       String sql="UPDATE TipoCliente SET tipoCliente=? WHERE idTipoCliente=? ";
       PreparedStatement cmd = cn.prepareStatement(sql);
      cmd.setString(1,tc.getTipo());
      cmd.setInt(2,tc.getId());
      
       if (!cmd.execute()) 
       {
        resp = true;
       }
                
//      cmd.close();
//      cn.close();  
      
      }
      catch(Exception e)
      {
        System.out.println(e.toString());
      }
      return resp;
     }
     
    
     public boolean eliminarTipo(CostructorTipoCliente tc)
     {
       boolean resp = false;
        try{
            String sql = "DELETE FROM TipoCliente WHERE idTipoCliente = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1,tc.getId() );
            if (!cmd.execute()) {
                resp = true;
            }
//            cmd.close();
//            cn.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return resp;
     }  
     
     
      public int idIncremental()
    {
       int ids=1;
      try
      {
        String sql="SELECT Max(idTipoCliente) FROM TipoCliente";
        PreparedStatement cmd= cn.prepareStatement(sql); 
        ResultSet rs= cmd.executeQuery();
        while(rs.next())
        {
          ids = rs.getInt(1)+1;
        }
        
      }
      catch(Exception e)
      {
         System.out.println(e.toString());
      }
     return ids;
    }
     
}
