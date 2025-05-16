/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.ConstructorDepartamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author adalb
 */
public class FuncionesDepartamento {
    
    public Connection cn; 

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
     
     public FuncionesDepartamento()
     {
       Conexion sn=new Conexion();
       cn=sn.conectar();
     } 
     
     public boolean guardarDepartamento (ConstructorDepartamento md) 
    {
     boolean resp = false;
     try{
     String sql= "INSERT INTO Departamento (idDepartamento,departamento) VALUES(?,?)";
     PreparedStatement cmd = cn.prepareStatement(sql);
      cmd.setInt(1,md.getId());
      cmd.setString(2,md.getDepartamento());
           
     if(!cmd.execute()){ 
         resp = true;
     }  
//       cmd.close();
//       cn.close();
     }  
   catch(Exception e)
     {
       
       System.out.println("Error de inserción"+e.toString());
     }
     return resp;
    }
     
  public boolean consultarDepartamento(ConstructorDepartamento md)
    {
      boolean resp = false;
      try
      {
        String sql="SELECT idDepartamento,departamento FROM Departamento WHERE idDepartamento=? ";
        PreparedStatement cmd= cn.prepareStatement(sql);
        cmd.setInt(1,md.getId());
        ResultSet rs= cmd.executeQuery();
        if(rs.next())
        {
          resp=true;
          md.setId(rs.getInt(1));
          md.setDepartamento(rs.getString(2));
         
        }
        
        
      }
      catch(Exception e)
      {
         System.out.println(e.toString());
      }
        return resp;
    }  
     
   
    public boolean actualizarDepartamento(ConstructorDepartamento md)
    {
      boolean resp = false;
      try
      {
       String sql="UPDATE Departamento SET departamento=?  WHERE idDepartamento=? ";
       PreparedStatement cmd = cn.prepareStatement(sql);
       cmd.setString(1,md.getDepartamento());
       cmd.setInt(2,md.getId());
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
     
     public boolean eliminarDepartamento(ConstructorDepartamento md){
       boolean resp = false;
        try{
            String sql = "DELETE FROM Departamento WHERE idDepartamento= ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1,md.getId() );
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
        String sql="SELECT Max(idDepartamento) FROM Departamento";
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
