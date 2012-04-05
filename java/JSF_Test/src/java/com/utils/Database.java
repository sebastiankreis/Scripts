/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dan
 */
public class Database {
    private Database() {};  // We don't need multiple instances

    private static Connection conn = null;
    private static String connection_string = "jdbc:derby://localhost/JSF_TestDB;create=true";

    private static void initConnection(){
        if( conn != null )
            return;

        try{
            conn = DriverManager.getConnection(connection_string);
            initDatabase();
        }catch(SQLException e){

        }
    }

    private static void initDatabase() throws SQLException{
        DatabaseMetaData data = conn.getMetaData();
        ResultSet res = data.getTables(null, null, null, new String[] {"TABLE"});


        if(!res.next()){
            // Database doesn't exist

            Statement create = conn.createStatement();
            create.execute("CREATE TABLE users("
                    + "id INTEGER PRIMARY KEY NOT NULL, "
                    + "passhash STRING NOT NULL,"
                    + "username STRING NOT NULL"
                    + ");");

        }
    }

    private static String toHex(byte[] buf){
        char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };

        StringBuilder strBuf = new StringBuilder(buf.length * 2);

        for (int i = 0; i < buf.length; i++) {
            strBuf.append(hexChar[(buf[i] & 0xf0) >>> 4]); // fill left with
            // zero bits
            strBuf.append(':');
            strBuf.append(hexChar[buf[i] & 0x0f]);
        }
        return strBuf.toString();

    }

    private static String saltPassword(String pass){
        return pass;
    }

    public static boolean validUser(String username, String password){
        try {
            initConnection();
            String query = "SELECT username, passhash FROM users WHERE username=?";
            PreparedStatement s = conn.prepareStatement(query);
            s.setString(1, username);
            ResultSet r = s.executeQuery();

            String user = r.getString("username");
            String pass = r.getString("passhash");

            if(username == null || pass == null){
                return false;
            }

            MessageDigest d = MessageDigest.getInstance("SHA1");
            d.update(password.getBytes(), 0, password.length());

            byte[] crypt_pass = d.digest();

            String computed_pass = toHex(crypt_pass);
            return computed_pass.equals(pass);

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NoSuchAlgorithmException ex){
            return false;
        }
    }

}
