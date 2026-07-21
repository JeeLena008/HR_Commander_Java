
package hr_commander.models;

import hr_commander.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


/**
 * DAO - Class responsible for reading and writing employees in the database.
 */

public class EmployeeDAO {
    
    /**
     * Reads all employees from the database and prints them to the console.
     */
    public void printAllEmployees(){
      
 // SQL query with JOIN to get the position NAME instead of just the ID
 String query = "SELECT e.full_name, j.position_name, e.individual_salary " + 
        "FROM employees e " +
        "JOIN job_positions j ON e.job_id = j.id";
 
try (Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query)){
    
    System.out.println("\n--- LISTA RADNIKA IZ SQL BAZE ---");
    
    while (rs.next()){
        String name = rs.getString("full_name");
        String position = rs.getString("position_name");
        double salary = rs.getDouble("individual_salary");
        
        System.out.println("Radnik: " + name + " | Pozicija: " + position + " | Plata: " + salary);
    }
}catch (SQLException e){
    System.out.println("GRESKA pri citanju podataka: " + e.getMessage());
    
}
    
    }
}