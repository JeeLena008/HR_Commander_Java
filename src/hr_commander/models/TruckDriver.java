package hr_commander.models;

import hr_commander.interfaces.Drivable;

import java.time.LocalDate;

/**
 * Class for Truck Drivers - Subclass of Employee
 */
public class TruckDriver extends Employee implements Drivable {

    //Specific field for drivers
    private double perDiemAmount;

    /**
     * CONSTRUCTOR - Generated via Fn + Alt + Insert
     *
     * @param perDiemAmount Daily allowance amount
     * @param fullName Full name
     * @param personalId Unique ID
     * @param salary Base salary
     * @param contractId Contract ID
     * @param start Start date
     * @param end End date
     * @param permanent Permanent status
     */
    public TruckDriver(double perDiemAmount, String fullName, String personalId, double salary, String contractId, LocalDate start, LocalDate end, boolean permanent) {
        // Calling Father and passing mandatory data
        super(fullName, personalId, salary, contractId, start, end, permanent);
        this.perDiemAmount = perDiemAmount;
    }

    /**
     * Implementation of salary calculation for drivers
     */
    @Override
    public double calculateFinalSalary() {
        // Base salary from Father + Per Diems + overTime
        return this.baseSalary + (this.perDiemAmount * 22) + (this.overTimeHours * 1000);
    }

    public double getPerDiemAmount() {
        return perDiemAmount;
    }

    public void setPerDiemAmount(double perDiemAmount) {
        this.perDiemAmount = perDiemAmount;
    }
    private LocalDate licenseExpiryDate;
    private LocalDate tachographExpiryDate;

    public void setLicenseExpiryDate(LocalDate licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public void setTachographExpiryDate(LocalDate tachographExpiryDate) {
        this.tachographExpiryDate = tachographExpiryDate;
    }
    
    

    @Override
    public LocalDate getLicenseExpiryDate() {
        return this.licenseExpiryDate;
    }

    @Override
    public LocalDate getTachographExpiryDate() {
        return this.tachographExpiryDate;
    }
}
