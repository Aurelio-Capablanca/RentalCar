/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alber
 */
public class Conexion {

//    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    private String url = "jdbc:sqlserver://2.tcp.ngrok.io:15415;databaseName=Sistema_transporte;";
//    private String user = "arwen";
//    private String pwrd = "123";
//    Connection cn = null;
//
//    public Connection conectar() {
//        try {
//            Class.forName(driver);
//            cn = DriverManager.getConnection(url, user, pwrd);
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return cn;
//    }
    
    
    
    //Constructor para la conexion

//    public Connection conectar() {
//        Connection cn = null;
//
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//            cn = DriverManager.getConnection("jdbc:sqlserver://SUN\\MSSQLSERVER;"
//                 +"databaseName=Sistema_transporte;IntegratedSecurity=true;");
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return cn;
//
//    }
    
    public Connection conectar() {
        Connection cn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            cn = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/Sistema_transporte","sa", "gHjkl5678");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cn;

    }
    
    
//public class conectar {
//    // Connect to your database.
//    // Replace server name, username, and password with your credentials
//    {
//        String connectionUrl =
//                "jdbc:sqlserver://localhost:1433;"
//                        + "database=Sistema_transporte;"
//                        + "user=sa;"
//                        + "password=gHjkl5678;"
//                        + "encrypt=true;"
//                        + "trustServerCertificate=false;"
//                        + "loginTimeout=30;";
//
//        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
//            // Code here.
//        }
//        // Handle any errors that may have occurred.
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
    
     public Connection conectar1() {
        Connection cn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            cn = DriverManager.getConnection("\"jdbc:sqlserver://localhost:1433;\"database=Sistema_transporte;\"user=sa;\"password=gHjkl5678;\"encrypt=true;\"trustServerCertificate=false;\"loginTimeout=30;\"");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cn;

    }
    
}




