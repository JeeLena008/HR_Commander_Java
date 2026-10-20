package hr_commander;

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
}