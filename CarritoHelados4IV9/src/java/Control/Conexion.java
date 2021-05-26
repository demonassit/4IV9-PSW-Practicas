
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

/**
 *
 * @author demon
 */

import java.sql.*;

public class Conexion {
    
    public static Connection getConexion() throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url, userName, password;
            url = "jdbc:mysql://localhost/CarritoHelados4IV9";
            userName = "root";
            password = "n0m3l0";
            return DriverManager.getConnection(url, userName, password);
        
        }catch(SQLException sq){
            System.out.println("Error al conectar a la BD");
            System.out.println(sq.getMessage());
        
        }catch(Exception e){
            System.out.println("Error no encontro la clase");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
