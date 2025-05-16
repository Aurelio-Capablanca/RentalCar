/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author alber
 */
public class Validaciones {
    
    public static boolean validarNumeros(String datos) {
        return datos.matches("[0-9]{1,8}");  
        
    }
    
    public static boolean validarLetras(String datos) {
        return datos.matches("[a-zA-Z]{1,50}");
    }

    public boolean valCorreo(String email) {

        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\\\\\+]+(\\.[\\w\\\\]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(email);

        if (mat.find()) {
            return true;

        } else {
            return false;

        }

    }
}
