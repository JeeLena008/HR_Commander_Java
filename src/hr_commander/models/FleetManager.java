package hr_commander.models;

import hr_commander.interfaces.Drivable;
import java.time.LocalDate;

/**
 * Fleet Manager - responsible for vehicles, maintenance, and driver
 * organization.
 */
public class FleetManager extends Employee implements Drivable {

    private LocalDate licenseExpiryDate; //License expiry date

    /**
     * Constructor for the Fleet Manager.
     *
     * @param fullName
     * @param personalId (personal ID)
     * @param salary (base salary)
     * @param contractId (contract ID)
     * @param start (contract start date)
     * @param end (contract end date)
     * @param permanent (permanent status)
     */
    public FleetManager(String fullName, String personalId, double salary, String contractId, LocalDate start, LocalDate end, boolean permanent) {
        super(fullName, personalId, salary, contractId, start, end, permanent);

    }

    /**
     * Salary calculation: Base salary + overtime.
     *
     * @return total salary with overtime
     */
    @Override
    public double calculateFinalSalary() {

        //Base salary + (overtime hours *1000 RSD)
        return this.baseSalary + (this.overTimeHours * 1000);
    }

    /**
     * Returns the driver's license expiry date
     *
     * @return license expiry date
     */
    @Override
    public LocalDate getLicenseExpiryDate() {
        return this.licenseExpiryDate;
    }

    /**
     * Returns the tachograph card expiry date (always null for this position).
     *
     * @return null (no tachograph needed)
     */
    @Override
    public LocalDate getTachographExpiryDate() {
        // Fleet Manager drives a passenger car, no tachograph card needed.
        return null;
    }

    /**
     * Sets the driver's license expiry date.
     *
     * @param licenseExpiryDate (new expiry date)
     */
    public void setLicenseExpiryDate(LocalDate licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

}
