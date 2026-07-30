package hr_commander.models;

import hr_commander.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;//Added for lists
import java.time.LocalDate;//Added for dates

/**
 * DAO - Class responsible for reading and writing employees in the database.
 */
public class EmployeeDAO {

    /**
     * Reads all employees from the database and prints them to the console.
     */
    public void printAllEmployees() {

        // SQL query with JOIN to get the position NAME instead of just the ID
        String query = "SELECT e.full_name, j.position_name, e.individual_salary "
                + "FROM employees e "
                + "JOIN job_positions j ON e.job_id = j.id";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- LISTA RADNIKA IZ SQL BAZE ---");

            while (rs.next()) {
                String name = rs.getString("full_name");
                String position = rs.getString("position_name");
                double salary = rs.getDouble("individual_salary");

                System.out.println("Radnik: " + name + " | Pozicija: " + position + " | Plata: " + salary);
            }
        } catch (SQLException e) {
            System.out.println("GRESKA pri citanju podataka: " + e.getMessage());

        }

    }

    /**
     * Fetches all active employees from the database and maps them to Java
     * object
     *
     * @return ArrayList of Employee objects
     */
    public ArrayList<Employee> getAllEmployeesFromDB() {
        ArrayList<Employee> list = new ArrayList<>();

        //SQL Query: Select all columns for active staff members
        String query = "SELECT * FROM employees WHERE is_active = 1";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                //Extracting data from SQL columns
                String name = rs.getString("full_name");
                String jmbg = rs.getString("personal_id");
                double salary = rs.getDouble("individual_salary");
                String contract = rs.getString("contract_number");

                //Converting SQL Date to Java LocalDate
                java.sql.Date sqlStart = rs.getDate("contract_start_date");
                java.sql.Date sqlEnd = rs.getDate("contract_end_date");

                LocalDate start = (sqlStart != null) ? sqlStart.toLocalDate() : null;
                LocalDate end = (sqlEnd != null) ? sqlEnd.toLocalDate() : null;

                //Creating a new OfficeWorker object as a template
                OfficeWorker e = new OfficeWorker(name, jmbg, salary, contract, start, end, true);

                //Adding the employee to our dynamic list
                list.add(e);
            }
        } catch (SQLException e) {
            System.out.println("DAO ERROR: " + e.getMessage());
        }
        return list;//Returning the completed list

    }

}
