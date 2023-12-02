package eni.m1.java.ws.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author shirleyodon
 */
public class Connexion{
    private Connection con;
    private String url="jdbc:mysql://localhost/m1project",
                    login="root",
                    pwd="";
    
    public Connexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url, login, pwd);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Connection getConnexion(){
        return con;
    }
}
