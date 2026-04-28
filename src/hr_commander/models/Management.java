package hr_commander.models;

import java.time.LocalDate;

/**
 * Management Class - Handles high-level bonuses
 */
public class Management extends Employee {

    private double managementBonus; //Hidden bonuses

    public Management(double managementBonus, String fullName, String personalId, double salary, String contractId, LocalDate start, LocalDate end, boolean permanent) {
        super(fullName, personalId, salary, contractId, start, end, permanent);
        this.managementBonus = managementBonus;
    }

    @Override
    public double calculateFinalSalary() {
        //Base + Management Bonus
        return this.baseSalary + this.managementBonus;
    }

}
