package hr_commander.models;

import hr_commander.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;//Added for lists
import java.time.LocalDate;//Added for dates
import java.util.Locale;

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
     * Helper method to safely convert SQL Date to Java LocalDate.
     * Converts SQL Date to Java LocalDate safely.
     *
     * @param sqlDate SQL Date from database
     * @return LocalDate or null if empty
     */
    private LocalDate toLocalDate(java.sql.Date sqlDate) {
        return (sqlDate != null) ? sqlDate.toLocalDate() : null;
    }


    /**
     * Fetches ALL 27 columns from MySQL and maps complete employee profiles into Java objects.
     *
     * @return ArrayList of fully populated Employee objects
     */
    public ArrayList<Employee> getAllEmployeesFromDB() {
        ArrayList<Employee> list = new ArrayList<>();

        String query = "SELECT e.*, j.position_name FROM employees e " +
                "LEFT JOIN job_positions j ON e.job_id = j.id " +
                "WHERE e.is_active = 1";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("full_name");
                String jmbg = rs.getString("personal_id");
                double salary = rs.getDouble("individual_salary");
                String contractNum = rs.getString("contract_number");

                LocalDate start = toLocalDate(rs.getDate("contract_start_date"));
                LocalDate end = toLocalDate(rs.getDate("contract_end_date"));

                String statusText = rs.getString("contract_status");
                boolean permanent = (end == null) || (statusText != null && statusText.toLowerCase().contains("neodređeno"));

                OfficeWorker emp = new OfficeWorker(name, jmbg, salary, contractNum, start, end, permanent);

                emp.setJobPosition(rs.getString("position_name"));
                emp.setAddress(rs.getString("address"));
                emp.setCity(rs.getString("city"));
                emp.setPhone(rs.getString("phone_number"));
                emp.setBankAccount(rs.getString("bank_account"));

                emp.setFirstHireDate(toLocalDate(rs.getDate("first_hire_date")));
                emp.setContractEndDate(end);
                emp.setContractStatus(statusText);
                emp.setExtensionCount(rs.getInt("extension_count"));
                emp.setActive(rs.getBoolean("is_active"));
                emp.setWearsGlasses(rs.getBoolean("wears_glasses"));

                emp.setMedicalExamDate(toLocalDate(rs.getDate("medical_expiry")));
                emp.setSafetyTrainingDate(toLocalDate(rs.getDate("bzr_passed")));
                emp.setIdCardExpiry(toLocalDate(rs.getDate("id_card_expiry")));

                emp.setDrivingLicenseExpiry(toLocalDate(rs.getDate("driving_license_expiry")));

                emp.setTachographCardExpiry(toLocalDate(rs.getDate("tachograph_card_expiry")));

                list.add(emp);

            }
        } catch (SQLException e) {
            System.out.println("GREŠKA u DAO pri čitanju baze: " + e.getMessage());

        }
        return list;
    }


}
