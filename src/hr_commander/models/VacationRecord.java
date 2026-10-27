package hr_commander.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Model representing an individual vacation leave record or day-off event.
 */
public class VacationRecord {

    private int id;
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int workingDaysUsed;
    private String vacationType; // 'OLD', 'NEW', 'PAID_LEAVE', 'BONUS_DAY', 'INFORMAL'
    private LocalDate decisionDate;
    private LocalDate returnToWorkDate;
    private String note;

    /**
     * Master Constructor for creating a vacation leave record.
     *
     * @param employeeId ID of the employee
     * @param startDate first day of vacation
     * @param endDate last day of vacation
     * @param workingDaysUsed number of work days deducted
     * @param vacationType type of leave (OLD, NEW, BONUS...)
     */

    public VacationRecord(int employeeId, LocalDate startDate, LocalDate endDate, int workingDaysUsed, String vacationType){
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workingDaysUsed = workingDaysUsed;
        this.vacationType = (vacationType != null)? vacationType : "NEW";
    }

    // --- GETTERS AND SETTERS ---


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getWorkingDaysUsed() {
        return workingDaysUsed;
    }

    public void setWorkingDaysUsed(int workingDaysUsed) {
        this.workingDaysUsed = workingDaysUsed;
    }

    public String getVacationType() {
        return vacationType;
    }

    public void setVacationType(String vacationType) {
        this.vacationType = vacationType;
    }

    public LocalDate getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(LocalDate decisionDate) {
        this.decisionDate = decisionDate;
    }

    public LocalDate getReturnToWorkDate() {
        return returnToWorkDate;
    }

    public void setReturnToWorkDate(LocalDate returnToWorkDate) {
        this.returnToWorkDate = returnToWorkDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    /**
     * Formats date to Serbian legal standard (dd.MM.yyyy.).
     */

    public String getFormattedDAte(LocalDate date){
        if (date == null) return "";
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
    }




}
