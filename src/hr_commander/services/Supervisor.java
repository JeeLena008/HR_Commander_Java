package hr_commander.services;

import hr_commander.models.Employee;

import java.util.ArrayList;

/**
 * Enterprise supervisor engine monitoring legal compliance, deadlines, and documentation.
 */
public class Supervisor {

    private ArrayList<Employee> staffList;

    /**
     * Constructor accepts live employee collection from the database via DAO.
     *
     * @param staffList populated list of active employees
     */
    public Supervisor(ArrayList<Employee> staffList) {
        this.staffList = staffList;
    }

    // =========================================================================
    // 1. DEFAULT ACTIVE RADAR
    // =========================================================================

    /**
     * Returns all active compliance breaches (Expired contracts, expired medicals, expired BZR).
     *
     * @return list of non-compliant employees
     */
    public ArrayList<Employee> getExpiredComplianceAlerts() {
        ArrayList<Employee> list = new ArrayList<>();
        for (Employee e : this.staffList) {
            if (e.isActive() && (e.isContractExpired() || e.isMedicalExpired() || e.isBzrExpired() || e.isDriverLicenseExpired() || e.isIdCardExpired())) {
                list.add(e);

            }
        }
        return list;
    }

    /**
     * Returns all critical deadlines (0 to 3 days remaining) across all documents.
     *
     * @return list of critical alerts
     */

    public ArrayList<Employee> getCriticalAlerts() {
        ArrayList<Employee> list = new ArrayList<>();
        for (Employee e : this.staffList) {
            if (e.isActive() && (e.isContractCritical() || e.isMedicalCritical() || e.isBzrCritical() || e.isDriverLicenseCritical() || e.isIdCardCritical())) {
                list.add(e);

            }

        }
        return list;
    }

    /**
     * Returns urgent deadlines (4 to 30 days remaining) across all documents.
     *
     * @return list of urgent alerts
     */
    public ArrayList<Employee> getUrgentAlerts() {
        ArrayList<Employee> list = new ArrayList<>();
        for (Employee e : this.staffList) {
            if (e.isActive() && (e.isContractUrgent() || e.isMedicalUrgent() || e.isBzrUrgent() || e.isDriverLicenseUrgent() || e.isIdCardUrgent())) {
                list.add(e);
            }
        }
        return list;
    }
    // =========================================================================
    // 2. PLANNING RADAR (31 to 60 days) - CHECKBOX
    // =========================================================================

    /**
     * Returns upcoming deadlines for long-term monthly planning (31 to 60 days ahead).
     *
     * @return list of upcoming planning alerts
     */
    public ArrayList<Employee> getUpcomingPlanningAlerts() {
        ArrayList<Employee> list = new ArrayList<>();
        for (Employee e : this.staffList) {
            if (e.isActive() && (e.isContractUpcoming() || e.isMedicalUpcoming() || e.isBzrUpcoming() || e.isDriverLicenseUpcoming() || e.isIdCardUpcoming())) {
                list.add(e);
            }
        }
        return list;

    }
    // =========================================================================
    // 3. STATUTORY 24-MONTH RULE & INCOMPLETE DOCUMENTATION
    // =========================================================================

    /**
     * Filters employees requiring permanent contract transition (Labor Law Art. 37).
     *
     * @return list of candidates for permanent status
     */
    public ArrayList<Employee> getCandidatesForPermanent() {
        ArrayList<Employee> list = new ArrayList<>();
        for (Employee e : this.staffList) {
            if (e.isActive() && !e.isPermanent() && e.isTimeForPermanentContract()) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns employees with incomplete personnel files (missing bank account, medical, BZR, etc.).
     *
     * @return list of employees with missing files
     */
    public ArrayList<Employee> getEmployeesWithIncompleteFiles() {
        ArrayList<Employee> list = new ArrayList<>();
        for (Employee e : this.staffList) {
            if (e.isActive() && e.hasIncompleteDocumentation()) {
                list.add(e);
            }
        }
        return list;
    }
    /**
     * Filters active employees who still have unused OLD vacation days approaching June 30th.
     *
     * @return list of employees with pending old vacation
     */

    public ArrayList<Employee> getEmployeesWithExpiringOldVacation(){
        ArrayList<Employee> list = new ArrayList<>();

        for (Employee emp : this.staffList){
            if (emp.isActive() && emp.getOldVacation() > 0){

            }
        }
        return list;
    }

    // ========================================
    // 🌲 RECURSIVE CHAIN OF COMMAND (Use Case)
    // ========================================

    /**
     * Recursive method to find the ultimate operational head (top of the pyramid).
     *
     * @param current current employee
     * @return ultimate boss at the top
     */
    public Employee getUltimateBoss(Employee current){
        if (current.getManagerId() == null || current.getManagerId().equals(current.getEmployeeId())){
            return current;
        }
        for (Employee potentialBoss : this.staffList){
            if(potentialBoss.getEmployeeId() == current.getManagerId()){

                if (potentialBoss.getManagerId() != null && potentialBoss.getManagerId().equals(current.getEmployeeId())){
                    return potentialBoss;
                }

                return getUltimateBoss(potentialBoss);
            }
        }
        return current;
    }
    // ============================
    // DELEGATION ENGINE (Use Case)
    // ============================

    /**
     * Resolves the actual acting manager. If the direct manager is on sick leave
     * or vacation, automatically routes approval to their designated deputy or higher boss.
     *
     *
     * @param targetEmployee employee requesting approval
     * @return active acting manager
     */
    public  Employee getActingBoss(Employee targetEmployee){
        Employee directBoss = null;
        for (Employee e : this.staffList){
            if (e.getEmployeeId() == targetEmployee.getManagerId()){
                directBoss = e;
                break;
            }
        }
        if (directBoss == null) return targetEmployee;

        if (!directBoss.isOnLeave()){
            return directBoss;
        }
        System.out.println("INFO: Šef [" + directBoss.getFullName() + "] je na odsustvu/bolovanju! Tražim zamenu...");

        if (directBoss.getDeputyId() != null){
            for (Employee deputy : this.staffList){
                if (deputy.getEmployeeId() == directBoss.getDeputyId() && !deputy.isOnLeave()){
                    return deputy;
                }
            }
        }
        return getUltimateBoss(directBoss);
    }



}