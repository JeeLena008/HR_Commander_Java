package hr_commander.models;

import hr_commander.interfaces.Drivable;
import java.time.LocalDate;

/**
 * OfficeWorker class - Inherits everything from Employee (strictly salary +
 * overtime)
 */
public class OfficeWorker extends Employee implements Drivable {

    private LocalDate licenseExpiryDate;

    //Constructor
    public OfficeWorker(String fullName, String personalId, double salary, String contractId, LocalDate start, LocalDate end, boolean permanent) {

        // Passing data to Father (Employee)
        super(fullName, personalId, salary, contractId, start, end, permanent);
    }

    /**
     * Implementation of salary calculation
     */
    @Override
    public double calculateFinalSalary() {
        // Return base salary + overTime
        return this.baseSalary + (this.overTimeHours * 1000);

    }

    @Override
    public LocalDate getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    @Override
    public LocalDate getTachographExpiryDate() {
        //Office workwrs don't have tachograph
        return null;
    }

}
