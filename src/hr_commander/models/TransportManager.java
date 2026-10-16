package hr_commander.models;

import java.time.LocalDate;

/**
 * Person responsible for transport - fictitious position with fixed salary.
 */
public class TransportManager extends Employee {

    public TransportManager(String fullName, String personalId, double salary, String contractId, LocalDate start, LocalDate end, boolean permanent) {
        super(fullName, personalId, salary, contractId, start, end, permanent);
    }

    @Override
    public double calculateFinalSalary() {
        //Always returns fixed base salary
        return this.baseSalary;
    }

}
