package hr_commander.models;

import hr_commander.utils.SerbianHolidays;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

/**
 * Base abstract class for all employees.
 */
public abstract class Employee {

    // PERSONAL DATA & ID CARD
    protected String fullName, personalId, idCardNumber;
    protected LocalDate idCardExpiry;
    protected String address, city, phone, bankAccount, bankName;
    protected String profession, educationLevel, jobPosition;

    //Unique ID for each employee
    protected int employeeID;

    //Static counter - shared memory for all employees
    private static int idCounter = 1000;

    // CONTRACTS & ANNEXES
    protected String activeContractNumber;
    protected int annexNumber, extensionCount;
    protected boolean isPermanent;
    protected LocalDate firstHireDate, contractDate, contractStartDate, contractEndDate;
    protected LocalDate probationEndDate;

    // HEALTH & SAFETY
    protected LocalDate medicalExamDate, safetyTrainingDate, glassesCertDate;
    protected boolean wearsGlasses;

    // FINANCE & TIME
    protected double baseSalary;
    protected int oldVacation;
    protected int newVacation;
    protected int daysOfVacationUsed;
    protected double overTimeHours; // Matches the field name precisely
    protected double vacationDaysEarned;
    protected boolean isActive = true; //Employee status (Active by default)

    /**
     * Master Constructor
     *
     * @param fullName
     * @param personalId
     * @param salary
     * @param contractId
     * @param start
     * @param end
     * @param permanent
     */
    public Employee(String fullName, String personalId, double salary, String contractId,
            LocalDate start, LocalDate end, boolean permanent) {

        this.fullName = (fullName != null) ? fullName : "NO NAME";
        this.personalId = (personalId != null) ? personalId : "0000000000000";
        this.baseSalary = salary;
        this.activeContractNumber = (contractId != null) ? contractId : "N/A";
        this.contractStartDate = start;
        this.contractEndDate = end;
        this.isPermanent = permanent;

        // AUTOMATIC CONTRACT DATE LOGIC
        if (start != null) {
            LocalDate tempDate = start.minusDays(1);
            while (SerbianHolidays.isHolidayOrWeekend(tempDate)) {
                tempDate = tempDate.minusDays(1);
            }
            this.contractDate = tempDate;

            // PROBATION LOGIC: 1 month for the first contract
            this.probationEndDate = start.plusMonths(1);

        }
        //Assign ID and increment the counter for the next one
        this.employeeID = ++idCounter;

    }

    public String getPersonalId() {
        return personalId;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public LocalDate getIdCardExpiry() {
        return idCardExpiry;
    }

    public void setIdCardExpiry(LocalDate idCardExpiry) {
        this.idCardExpiry = idCardExpiry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public int getAnnexNumber() {
        return annexNumber;
    }

    public void setAnnexNumber(int annexNumber) {
        this.annexNumber = annexNumber;
    }

    public int getExtensionCount() {
        return extensionCount;
    }

    public void setExtensionCount(int extensionCount) {
        this.extensionCount = extensionCount;
    }

    public LocalDate getFirstHireDate() {
        return firstHireDate;
    }

    public void setFirstHireDate(LocalDate firstHireDate) {
        this.firstHireDate = firstHireDate;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public LocalDate getProbationEndDate() {
        return probationEndDate;
    }

    public void setProbationEndDate(LocalDate probationEndDate) {
        this.probationEndDate = probationEndDate;
    }

    public LocalDate getMedicalExamDate() {
        return medicalExamDate;
    }

    public void setMedicalExamDate(LocalDate medicalExamDate) {
        this.medicalExamDate = medicalExamDate;
    }

    public LocalDate getSafetyTrainingDate() {
        return safetyTrainingDate;
    }

    public void setSafetyTrainingDate(LocalDate safetyTrainingDate) {
        this.safetyTrainingDate = safetyTrainingDate;
    }

    public LocalDate getGlassesCertDate() {
        return glassesCertDate;
    }

    public void setGlassesCertDate(LocalDate glassesCertDate) {
        this.glassesCertDate = glassesCertDate;
    }

    public boolean isWearsGlasses() {
        return wearsGlasses;
    }

    public void setWearsGlasses(boolean wearsGlasses) {
        this.wearsGlasses = wearsGlasses;
    }

    public double getOverTimeHours() {
        return overTimeHours;
    }

    public void setOverTimeHours(double overTimeHours) {
        this.overTimeHours = overTimeHours;
    }

    public double getVacationDaysEarned() {
        return vacationDaysEarned;
    }

    public void setVacationDaysEarned(double vacationDaysEarned) {
        this.vacationDaysEarned = vacationDaysEarned;
    }

    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    public int getOldVacation() {
        return oldVacation;
    }

    public void setOldVacation(int oldVacation) {
        this.oldVacation = oldVacation;
    }

    public int getNewVacation() {
        return newVacation;
    }

    public void setNewVacation(int newVacation) {
        this.newVacation = newVacation;
    }

    public int getDaysOfVacationUsed() {
        return daysOfVacationUsed;
    }

    public void setDaysOfVacationUsed(int daysOfVacationUsed) {
        this.daysOfVacationUsed = daysOfVacationUsed;
    }

    /**
     * Calculation of vacation days based on 1.66 days per month
     *
     * @return current vacation days
     */
    public double calculateCurrentVacation() {
        if (firstHireDate == null) {
            return 0;
        }
        long monthsWorked = ChronoUnit.MONTHS.between(firstHireDate, LocalDate.now());
        return monthsWorked * 1.66;
    }

    /**
     * Add overtime hours
     *
     * @param hours hours to add
     */
    public void addOvertime(double hours) {
        this.overTimeHours += hours;
    }

    /**
     * Abstract method for salary calculation
     *
     * @return final salary
     */
    public abstract double calculateFinalSalary();

    public String getFullName() {
        return this.fullName;
    }

    /**
     * Checks if the employee is currently active in the company.
     *
     * @return true if active, false if terminated
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Sets the employee status (we use false when the employee resigns)
     *
     * @param isActive the new status of the employee
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Checks if the employee must get a permanent contract (Labor Law - 24
     * months limit).
     *
     * @return true if limit reached, false if limit not reached
     */
    public boolean isTimeForPermanentContract() {
        // If any date is null, return FALSE immediately and stop!
        if (this.firstHireDate == null || this.contractDate == null) {
            return false;

        }
        // Calculate how many months will pass from the first day to the END of this contract.
        long monthsWorked = ChronoUnit.MONTHS.between(this.firstHireDate, this.contractEndDate);
        // If 24 months (or more) have passed, returns TRUE (Must be permanent!).
        return monthsWorked >= 24;

    }

    /**
     * Automatically calculates the medical exam expiry date (1 year from the
     * exam date).
     *
     * @return expiry date, or null if no exam date is set
     */
    public LocalDate getMedicalExamExpiryDate() {
        //If no exam date, return null
        if (this.medicalExamDate == null) {
            return null;
        }
        //Add exactly 1 year to the exam date
        return this.medicalExamDate.plusYears(1);
    }

    /**
     * Automatically calculates the safety training (BZR) expiry date (1 year
     * from the training date).
     *
     * @return expiry date, or null if no training date is set
     */
    public LocalDate getSafetyTrainingExpiryDate() {
        //If no training date, return null
        if (this.safetyTrainingDate == null) {
            return null;

        }
        //Add exactly 1 year to the training date
        return this.safetyTrainingDate.plusYears(1);
    }

    /**
     * Method that calculates the remaining vacation days.
     *
     * @return remaining vacation days
     */
    public int calculateRemainingVacation() {
        // Adding old and new vacation
        int totalVacation = this.oldVacation + this.newVacation;
        // Subtracting used days
        int remaining = totalVacation - this.daysOfVacationUsed;

        return remaining;
    }

    /**
     *
     * Generates a contract number based on today's date and employee ID.
     * Format: ddMMyy/ID
     *
     * @return zavodni broj ugovora (contract reference number)
     */
    public String generateContractNumber() {
        //Get today's date
        LocalDate today = LocalDate.now();

        //Format it to ddMMyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        String datePart = today.format(formatter);

        //Combine date, slash and ID
        return datePart + "/" + this.employeeID;

    }

    /**
     * Converts Java date to Serbian format for Word contract (dd.MM.yyyy.).
     *
     * @param date the date to format
     * @return date as text in dd.MM.yyyy. forma)
     */
    public String getFormattedDate(LocalDate date) {
        //If date is null, return empty string
        if (date == null) {
            return "";
        }
        //Define the pattern: (day.month.year.).
        DateTimeFormatter srbFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

        //Return formatted date as text
        return date.format(srbFormat);
    }
    
    /**
     * Converts employee data into a single line of text for file storage
     * 
     * @return data as a text line
     */
    public String toFileFormat(){
        //CSV format
        return this.employeeID + ";" + this.fullName + ";=\"" + this.personalId + "\";" + this.jobPosition;
    }
    
}
