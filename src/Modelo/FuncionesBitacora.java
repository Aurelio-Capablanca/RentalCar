/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import Controlador.ConstructorBitacora;
import Controlador.ConstructorCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author adalb
 */
public class FuncionesBitacora {
    
    public Connection cn; 
    
    
     public FuncionesBitacora()
    { 
       
       Conexion sn= new Conexion();
       cn=sn.conectar();
    }
    
    
     public boolean ConsultarBitacora(ConstructorBitacora bc)
    {
      boolean resp = false;
      try
      {
        String sql="SELECT idBitacora,persona,fecha,accion FROM Bitacora WHERE idBitacora=? ";
        PreparedStatement cmd= cn.prepareStatement(sql);
        cmd.setInt(1,bc.getId());
        ResultSet rs= cmd.executeQuery();
        if(rs.next())
        {
          resp=true;
          bc.id=rs.getInt(1);
          bc.persona=rs.getString(2);
          bc.fecha=rs.getString(3);
          bc.accion=rs.getString(4);
          
        }
        
      }
      catch(Exception e)
      {
         System.out.println(e.toString());
      }
        return resp;
    }
    
    
}
