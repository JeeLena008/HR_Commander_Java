package hr_commander.models;

import hr_commander.utils.DatabaseConnection;

import java.sql.*;
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
                emp.setEmployeeId(rs.getInt("id"));

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
    // =========================================================================
    // VACATION OPERATIONS
    // =========================================================================

    /**
     * Inserts a new vacation leave record into MySQL database using PreparedStatement.
     *
     * @param record populated VacationRecord object
     * @return true if successfully inserted, false otherwise
     */

    public boolean insertVacationRecord(VacationRecord record) {
        String sql = "INSERT INTO vacation_records " +
                "(employee_id, start_date, end_date, working_days_used, vacation_type, decision_date, return_to_work_date, note) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, record.getEmployeeId());
            pstmt.setDate(2, java.sql.Date.valueOf(record.getStartDate()));
            pstmt.setDate(3, java.sql.Date.valueOf(record.getEndDate()));
            pstmt.setInt(4, record.getWorkingDaysUsed());
            pstmt.setString(5, record.getVacationType());


            if (record.getDecisionDate() != null) {
                pstmt.setDate(6, java.sql.Date.valueOf(record.getDecisionDate()));
            } else {
                pstmt.setNull(6, Types.DATE);

            }

            if (record.getReturnToWorkDate() != null) {
                pstmt.setDate(7, java.sql.Date.valueOf(record.getReturnToWorkDate()));
            } else {
                pstmt.setNull(7, Types.DATE);
            }
            pstmt.setString(8, record.getNote());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("GREŠKA pri upisu odmora u bazu: " + e.getMessage());
            return false;
        }
    }
    /**
     * Updates an employee's vacation balance in MySQL database after leave deduction.
     *
     * @param emp employee with updated vacation balance
     * @return true if update succeeded
     */
    public boolean updateEmployeeVacationBalance(Employee emp){
        String sql = "UPDATE employees SET " +
                "old_vacation = ?, " +
                "new_vacation = ?, " +
                "days_of_vacation_used = ? " +
                "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, emp.getOldVacation());
            pstmt.setInt(2, emp.getNewVacation());
            pstmt.setInt(3, emp.getDaysOfVacationUsed());
            pstmt.setInt(4, emp.getEmployeeId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        }catch (SQLException e){
            System.out.println("GREŠKA pri ažuriranju stanja odmora: " + e.getMessage());
            return false;
        }
    }
    // ==========================
    // COMPANY PROFILE OPERATIONS
    // ==========================

    public CompanyProfile getCompanyProfileFromDB(){
        String sql = "SELECT * FROM company_profile LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("company_name");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String director = rs.getString("director_name");
                String pib = rs.getString("pib");
                String mb = rs.getString("mb");

                return new CompanyProfile(id, name, city, address, director, pib, mb);
            }

        }
        catch (SQLException e){

            System.out.println("GREŠKA PRI ČITANJU PROFILA FIRME IZ BAZE: " + e.getMessage());

        }
        return null;
    }
    // =================================================================
    // VACATION HISTORY RETRIEVAL
    // =================================================================

    /**
     * Fetches all vacation leave records for a specific employee from MySQL database.
     *
     * @param employeeId ID of the employee
     * @return list of VacationRecord objects
     */

    public ArrayList<VacationRecord> getVacationHistoryByEmployeeId(int employeeId){
        ArrayList<VacationRecord> history = new ArrayList<>();

        String sql = "SELECT * FROM vacation_records WHERE employee_id = ? ORDER BY start_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, employeeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    LocalDate start = toLocalDate(rs.getDate("start_date"));
                    LocalDate end = toLocalDate(rs.getDate("end_date"));
                    String type = rs.getString("vacation_type");

                    VacationRecord record = new VacationRecord(employeeId, start, end, type);

                    record.setId(rs.getInt("id"));
                    record.setDecisionDate(toLocalDate(rs.getDate("decision_date")));

                    record.setReturnToWorkDate(toLocalDate(rs.getDate("return_to_work_date")));
                    record.setNote(rs.getString("note"));

                    history.add(record);

                }
            }
        }catch (SQLException e){

            System.out.println("GREŠKA pri citanju istorije odmora: " + e.getMessage());
        }
        return history;
    }



}


