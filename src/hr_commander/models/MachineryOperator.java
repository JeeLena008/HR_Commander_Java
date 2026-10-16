package hr_commander.models;

import java.time.LocalDate;

/**
 * Machinery Operator - Subclass for heavy equipment
 */
public class MachineryOperator extends Employee {

    //Per diem for field work
    private double perDiemAmount;

    /**
     *
     * @param perDiemAmount
     * @param fullName
     * @param personalId
     * @param salary
     * @param contractId
     * @param start
     * @param end
     * @param permanent
     */
    public MachineryOperator(double perDiemAmount, String fullName, String personalId, double salary, String contractId, LocalDate start, LocalDate end, boolean permanent) {
        super(fullName, personalId, salary, contractId, start, end, permanent);
        this.perDiemAmount = perDiemAmount;
    }

    /**
     * Overriding salary calculation
     */
    @Override
    public double calculateFinalSalary() {
        //Base salary + Per Diems
        return this.baseSalary + (this.perDiemAmount * 22) + (this.overTimeHours * 1000);
    }

    //GETTER AND SETTER - For field specific data
    public double getPerDiemAmount() {
        return perDiemAmount;
    }

    public void setPerDiemAmount(double perDiemAmount) {
        this.perDiemAmount = perDiemAmount;
    }

}
