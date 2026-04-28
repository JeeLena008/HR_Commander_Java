package hr_commander.models;

import java.time.LocalDate;

/**
 * Engineer Class - Focus on project bonuses
 */
public class Engineer extends Employee {

    private double projectBonus; // Bonus for specific projects

    public Engineer(double projectBonus, String fullName, String personalId, double salary, String contractId, LocalDate start, LocalDate end, boolean permanent) {
        super(fullName, personalId, salary, contractId, start, end, permanent);
        this.projectBonus = projectBonus;
    }

    /**
     * Override for Salary - Base + Project Bonus
     */
    @Override
    public double calculateFinalSalary() {
        //Base salary + Project Bonus
        return this.baseSalary + this.projectBonus;
    }

    /**
     * Override for Vacation - Empolyee calc + 5 bonus days
     */
    @Override
    public double calculateCurrentVacation() {
        return super.calculateCurrentVacation() + 5;
    }

}
