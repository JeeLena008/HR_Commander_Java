package hr_commander.models;

import hr_commander.utils.SerbianHolidays;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

/**
 * Base abstract class for all employees.
 */
public abstract class Employee {

//--- 1. PERSONAL DATA
    protected String fullName;
    protected String personalId;//JMBG
    protected String address;
    protected String city;
    protected String municipality;
    protected String phone;
    protected String educationLevel;
    protected String profession;//Zanimanje iz diplome
    protected String jobPosition;//Radno mesto u firmi
    protected int employeeId;//Interni ID (1001, 1002...)
    private static int idCounter = 1000;//Brojac za ID

    //--- 2. CONTRACT DATA
    protected String contractStatus;//Ugovor/Neodredjeno
    protected String activeContractNumber;
    protected int annexNumber;
    protected int extensionCount;
    protected boolean isPermanent;
    protected LocalDate firstHireDate;
    protected LocalDate contractDate;//Datum potpisivanja
    protected LocalDate contractStartDate;
    protected LocalDate contractEndDate;
    protected LocalDate probationEndDate;
    protected boolean isActive = true;

    //--- 3. HEALTH & SAFETY
    protected LocalDate medicalExamDate;
    protected LocalDate safetyTrainingDate;//BZR
    protected boolean wearsGlasses;
    protected LocalDate idCardExpiry;
    protected LocalDate drivingLicenseExpiry;
    protected LocalDate tachographCardExpiry;

    //--- 4. FINANCE & VACATION
    protected double baseSalary;
    protected double individual_salary;//Prava plata iz baze
    protected String bankAccount;
    protected double overTimeHours;
    protected int oldVacation;
    protected int newVacation;
    protected int daysOfVacationUsed;

    /**
     * Master Constructor - The official form for creating an employee
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
        this.individual_salary = salary;// Using our new refactored name
        this.activeContractNumber = (contractId != null) ? contractId : "N/A";
        this.contractStartDate = start;
        this.contractEndDate = end;
        this.isPermanent = permanent;

        // AUTOMATIC CONTRACT DATE LOGIC
        this.employeeId = ++idCounter; //Auto-assign ID

        if (start != null) {
            //Calculate siging date (skip weekends/holidays)
            LocalDate tempDate = start.minusDays(1);
            while (SerbianHolidays.isHolidayOrWeekend(tempDate)) {
                tempDate = tempDate.minusDays(1);

            }
            this.contractDate = tempDate;
            this.probationEndDate = start.plusMonths(1);

        }

    }
    //=================================================================
    //GETTERS AND SETTERS - GROUP 1: PERSONAL DATA
    //=================================================================

    /**
     * @return full name of the employee
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return personal ID (JMBG)
     */
    public String getPersonalId() {
        return personalId;
    }

    /**
     * @return internal company ID
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @return current job position
     */
    public String getJobPosition() {
        return jobPosition;
    }

    /**
     * @param jobPosition sets the actual role
     */
    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    /**
     * @return home address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address sets the home address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return city of residence
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city sets the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //===============================================
    //GETTERS AND SETTERS - GROUP 2: CONTRACT DATA
    //===============================================
    /**
     * @return current contract status
     */
    public String getContractStatus() {
        return contractStatus;
    }

    /**
     * @param contractStatus sets the status
     */
    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    /**
     * @return active contract number
     */
    public String getActiveContractNumber() {
        return activeContractNumber;
    }

    /**
     * @param number sets the contract number
     */
    public void setActiveContractNumber(String number) {
        this.activeContractNumber = number;
    }

    /**
     * @return number of extension
     */
    public int getExtensionCount() {
        return extensionCount;
    }

    /**
     * @param count sets the extension count
     */
    public void setExtensionCount(int count) {
        this.extensionCount = count;
    }

    /**
     * @param firstHireDate sets the first day of employment
     */
    public void setFirstHireDate(LocalDate firstHireDate) {
        this.firstHireDate = firstHireDate;

    }

    /**
     * @return true if active employee
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @param active sets status
     */
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /**
     * @return true if permanent employee
     */
    public boolean isPermanent() {
        return isPermanent;
    }

    /**
     * @param permanent sets permanent status
     */
    public void setPermanent(boolean permanent) {
        this.isPermanent = permanent;
    }

    /**
     * @return contract start date
     */
    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    /**
     * @param date sets the start date
     */
    public void setContractStartDate(LocalDate date) {
        this.contractStartDate = date;
    }

    /**
     * @return contract end date
     */
    public LocalDate getContractEndDate() {
        return contractEndDate;
    }

    /**
     * @param date sets the end date
     */
    public void setContractEndDate(LocalDate date) {
        this.contractEndDate = date;
    }

    //===============================================
    //GETTERS AND SETTERS - GROUP 3: HEALTH & SAFETY
    //===============================================
    /**
     * @return date of the last medical exam
     */
    public LocalDate getMedicalExamDate() {
        return medicalExamDate;
    }

    /**
     * @param date sets the medical exam date
     */
    public void setMedicalExamDate(LocalDate date) {
        this.medicalExamDate = date;
    }

    /**
     * @return date of safety training (BZR)
     */
    public LocalDate getSafetyTrainingDate() {
        return safetyTrainingDate;
    }

    /**
     * @param date sets the BZR training date
     */
    public void setSafetyTrainingDate(LocalDate date) {
        this.safetyTrainingDate = date;
    }

    /**
     * @return true if employee wears glasses
     */
    public boolean isWearsGlasses() {
        return wearsGlasses;
    }

    /**
     * @param wearsGlasses sets the glasses status
     */
    public void setWearsGlasses(boolean wearsGlasses) {
        this.wearsGlasses = wearsGlasses;
    }

    //====================================================
    //GETTERS AND SETTERS - GROUP 4: FINANCE & VACATION
    //====================================================
    /**
     * @return individual salary from database
     */
    public double getIndividualSalary() {
        return individual_salary;
    }

    /**
     * @param salary sets the actual salary
     */
    public void setIndividualSalary(double salary) {
        this.individual_salary = salary;
    }

    /**
     * @return banc account number
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * @param account sets the bank account
     */
    public void setBankAccount(String account) {
        this.bankAccount = account;
    }

    /**
     * @param days sets used vacation days
     */
    public void setDaysOfVacationUsed(int days) {
        this.daysOfVacationUsed = days;
    }

    /**
     * @param days sets old vacation days
     */
    public void setOldVacation(int days) {
        this.oldVacation = days;
    }

    /**
     * @param days sets new vacation days
     */
    public void setNewVacation(int days) {
        this.newVacation = days;
    }
    //==============================================
    //LOGIC METHODS
    //=============================================

    /**
     * ABSTRACT method for salary calculation. Must be implemented by subclasses
     *
     * @return final calculated salary
     */
    public abstract double calculateFinalSalary();

    /**
     * Calculates vacation days based on 1.66 days per month.
     *
     * @return current vacation days
     */
    public double calculateCurrentVacation() {
        if (this.firstHireDate == null) {
            return 0;
        }
        long monthsWorked = ChronoUnit.MONTHS.between(this.firstHireDate, LocalDate.now());
        return monthsWorked * 1.66;
    }

    /**
     * Adds overtime hours to the employee record.
     *
     * @param hours hours to add
     */
    public void addOvertime(double hours) {
        this.overTimeHours += hours;

    }

    /**
     * Calculates remaining vacation days: (Old + New) - Used
     *
     * @return remaining days
     */
    public int calculateRemainingVacation() {
        return (this.oldVacation + this.newVacation) - this.daysOfVacationUsed;

    }

    /**
     * Checks if the 24-month limit for fixed-term contracts is reached
     *
     * @return true if limit reached
     */
    public boolean isTimeForPermanentContract() {
        if (this.firstHireDate == null || this.contractEndDate == null) {
            return false;

        }
        long months = ChronoUnit.MONTHS.between(this.firstHireDate, this.contractEndDate);
        return months >= 24;

    }

    /**
     * Generates a unique contract reference number ( format:ddMMyy/ID).
     *
     * @return contract number
     */
    public String generateContractNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        return LocalDate.now().format(formatter) + "/" + this.employeeId;

    }

    /**
     * Formats any days to Serbian standard
     *
     * @param date date to format
     * @return formatted string
     */
    public String getFormattedDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        DateTimeFormatter srbFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        return date.format(srbFormat);

    }

    /**
     * Prepares a single line for CSV export with Excel JMBG protection.
     *
     * @return CSV line
     */
    public String toFileFormat() {
        return this.employeeId + ";" + this.fullName + ";=\"" + this.personalId + "\";" + this.jobPosition;

    }
    //=================================================
    //LOGIC METHODS
    //=================================================
    
    /**
     * CHECKS if the contract expires in the next 30 days
     * 
     * @return true if urgent
     */
    public boolean isContractUrgent(){
        if(this.contractEndDate == null) return false;
        LocalDate today = LocalDate.now();
        return this.contractEndDate.isBefore(today.plusDays(30));
    }
    /**
     * Check if the medical exam expires i  the next 30 days
     * 
     * @return true if expiring soon
     */
    public boolean isMedicalExpiring(){
        if(this.medicalExamDate == null) return false;
        LocalDate today = LocalDate.now();
        
        //Medical exam usually lasts 1 year
        LocalDate expiryDate = this.medicalExamDate.plusYears(1);
        return expiryDate.isBefore(today.plusDays(30));
        
    }
   
    
    
    
    
}
