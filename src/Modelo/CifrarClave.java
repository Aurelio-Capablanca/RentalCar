/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author alber
 */
public class CifrarClave {
    
    public String Encriptar(String plaintext) throws NoSuchAlgorithmException {
        
        MessageDigest mds = MessageDigest.getInstance("MD5");
        mds.reset();
        mds.update(plaintext.getBytes());
        byte[] digest = mds.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);
        
        while (hashtext.length() < 32) {            
            
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
    
}
