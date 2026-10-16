package hr_commander.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Secure class for database connection
*/

public class DatabaseConnection{
    
    private static final String URL = "jdbc:mysql://localhost:3306/hr_commander_db?useUnicode=true&characterEncoding=UTF8";
    private static final String USER = "root";
    
    /**
     * Establishes connection using the password from config.properties.
     * 
     * @return Connection objekat (connection object)
     * 
     */
    
    public static Connection getConnection(){
        Properties props = new Properties();
        
        //Traying to read the secret file
        try(InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties")){
            
            if(input == null){
                System.out.println("GRESKA: Fajl config.properties nije pronadjen!");
                return null;
                
            }
            //Lodaing data
            props.load(input);
            String password = props.getProperty("db.password");
            
            //Connecting
            return DriverManager.getConnection(URL, USER, password);
            
        }catch (Exception e){
            System.out.println("GRESKA pri povezivanju: " + e.getMessage());
            return null;
            
            
        }
    }
}