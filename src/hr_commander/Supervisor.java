package hr_commander;

import hr_commander.models.Employee;
import java.util.ArrayList;

/**
 * Business supervisor engine that monitors all employee deadlines and contract limits.
 */

public class Supervisor {

    //The in-memory list of employees to supervise
    private ArrayList<Employee> staffList;

    /**
     * Constructor accepts the list fetched from the Database via DAO.
     *
     * @param staffList populated list of employees
     */

    public Supervisor(ArrayList<Employee> staffList){
        this.staffList = staffList;
    }

    /**
     * Filters and returns only employees with CRITICAL contract status (0-3 days).
     *
     * @return list of critical employees
     */
public ArrayList<Employee> getCriticalContracts(){
    ArrayList<Employee> criticalList = new ArrayList<>();
    for (Employee e: this.staffList){
        if (e.isActive() && e.isContractCritical()){
            criticalList.add(e);
        }
    }
    return criticalList;
}
/**
 * Filters and returns employees with URGENT contract status (4-30 days).
 *
 * @return list of urgent employees
 */

public ArrayList<Employee> getUrgentContracts(){
    ArrayList<Employee> urgentList = new ArrayList<>();
    for (Employee e: this.staffList){
        if (e.isActive() && e.isContractUrgent()){
            urgentList.add(e);
        }
    }
    return urgentList;
}
/**
 * Filters employees requiring permanent contract transition (24-month rule).
 *
 * @return list of candidates for permanent contract
 */
    public ArrayList<Employee> getCandidatesForPermanent() {
      ArrayList<Employee> permanentCandidates = new ArrayList<>();
      for (Employee e: this.staffList){
          if (e.isActive() && !e.isPermanent() && e.isTimeForPermanentContract()){
              permanentCandidates.add(e);
          }
      }
      return permanentCandidates;
    }
}
