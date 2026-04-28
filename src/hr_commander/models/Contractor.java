package hr_commander.models;

import java.time.LocalDate;

/**
 * Class for persons engaged under a Service Contract (Freelancers/Contractors).
 */
public class Contractor {

    private String fullName;
    private String personalId;
    private String profession; //Profession from diploma
    private String jobPosition;//Job position in contract
    private double contractAmount;

    private LocalDate medicalExamDate;
    private LocalDate safetyTrainingDate;

    public Contractor(String fullName, String personalId, String profession, String jobPosition, double amount) {
        this.fullName = fullName;
        this.personalId = personalId;
        this.profession = profession;
        this.jobPosition = jobPosition;
        this.contractAmount = amount;
    }

    public LocalDate getMedicalExamDate() {
        return (medicalExamDate != null) ? medicalExamDate.plusYears(1) : null;
    }

    public LocalDate getSafetyTrainingDate() {
        return (safetyTrainingDate != null) ? safetyTrainingDate.plusYears(1) : null;
    }

    public void setMedicalExamDate(LocalDate medicalExamDate) {
        this.medicalExamDate = medicalExamDate;
    }

    public void setSafetyTrainingDate(LocalDate safetyTrainingDate) {
        this.safetyTrainingDate = safetyTrainingDate;
    }

}
