package hr_commander.models;

import hr_commander.utils.SerbianHolidays;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

    /**
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone sets the phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @param IdCardExpiry sets Id card expiry
     */
    public void setIdCardExpiry(LocalDate date) {
        this.idCardExpiry = date;
    }
    /**
     * @param DrivingLicenseExpiry sets driving license expiry
     */
    public void setDrivingLicenseExpiry(LocalDate date){ this.drivingLicenseExpiry = date;}

    /**
     * @param TachographCardExpiry sets tachograph card expiry
     */
    public void setTachographCardExpiry(LocalDate date){ this.tachographCardExpiry = date;}


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
     * @return first hire date
     */
    public LocalDate getFirstHireDate() {
        return firstHireDate;
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
     * Calculates remaining vacation days: (Old + New) - Used
     *
     * @return remaining days
     */
    public int calculateRemainingVacation() {
        return (this.oldVacation + this.newVacation) - this.daysOfVacationUsed;

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
     * Automatically calculates and sets the contract end date based on duration in months.
     *
     * @param months duration of the contract (e.g. 1, 3, 6)
     */
    public void setContractDurationMonths(int months) {
        if (this.contractStartDate != null) {
            this.contractEndDate = this.contractStartDate.plusMonths(months).minusDays(1);
            this.isPermanent = false;
        }
    }

    /**
     * Sets the employee to permanent status (Neodredjeno vreme - no end date).
     */
    public void setContractToPermanent() {
        this.isPermanent = true;
        this.contractEndDate = null;
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
    // =========================================================================
    // UNIFIED STATUTORY COMPLIANCE & EXPIRATION SENSORS
    // (Four-Tier System: Expired / Critical [0-3d] / Urgent [4-30d] / Upcoming [31-60d])
    // =========================================================================


    /**
     * Internal helper method to calculate calendar days between today and target date.
     *
     * @param targetDate target expiry date
     * @return remaining days (negative if in the past, 99999 if date is null)
     */
    private long getDaysUntil(LocalDate targetDate) {
        if (targetDate == null) return 99999;
        return ChronoUnit.DAYS.between(LocalDate.now(), targetDate);
    }

    // -------------------------------------------------------------------------
    // 1. CONTRACT EXPIRATION SENSORS
    // -------------------------------------------------------------------------
    public boolean isContractExpired() {
        if (this.isPermanent || this.contractEndDate == null) return false;
        return getDaysUntil(this.contractEndDate) < 0; // Negativa days = ALREADY EXPIRED!
    }

    public boolean isContractCritical() {
        if (this.isPermanent || this.contractEndDate == null) return false;
        long days = getDaysUntil(this.contractEndDate);
        return days >= 0 && days <= 3; //0 to 3 days remaining
    }

    public boolean isContractUrgent() {
        if (this.isPermanent || this.contractEndDate == null) return false;
        long days = getDaysUntil(this.contractEndDate);
        return days > 3 && days <= 30; //4 to 30 days remaining
    }

    public boolean isContractUpcoming() {
        if (this.isPermanent || this.contractEndDate == null) return false;
        long days = getDaysUntil(this.contractEndDate);
        return days > 30 && days <= 60; //31 to 60 days remaining (Planning)
    }

    // -------------------------------------------------------------------------
    // 2. PERIODIC MEDICAL EXAM SENSORS (1 year)
    // -------------------------------------------------------------------------
    public LocalDate getMedicalExpiryDate() {
        if (this.medicalExamDate == null) return null;
        return this.medicalExamDate.plusYears(1);
    }

    public boolean isMedicalExpired() {
        LocalDate expiry = getMedicalExpiryDate();
        return expiry != null && getDaysUntil(expiry) < 0;
    }

    public boolean isMedicalCritical() {
        LocalDate expiry = getMedicalExpiryDate();
        long days = getDaysUntil(expiry);
        return days >= 0 && days <= 3;
    }

    public boolean isMedicalUrgent() {
        LocalDate expiry = getMedicalExpiryDate();
        long days = getDaysUntil(expiry);
        return days > 3 && days <= 30;
    }

    public boolean isMedicalUpcoming() {
        LocalDate expiry = getMedicalExpiryDate();
        long days = getDaysUntil(expiry);
        return days > 30 && days <= 60;
    }
// -------------------------------------------------------------------------
    // 3. IDENTIFICATION CARD SENSORS
    // -------------------------------------------------------------------------

    public boolean isIdCardExpired() {
        return this.idCardExpiry != null && getDaysUntil(this.idCardExpiry) < 0;

    }

    public boolean isIdCardCritical() {
        long days = getDaysUntil(this.idCardExpiry);
        return days >= 0 && days <= 3;
    }

    public boolean isIdCardUrgent() {
        long days = getDaysUntil(this.idCardExpiry);
        return days > 3 && days <= 30;
    }

    public boolean isIdCardUpcoming() {
        long days = getDaysUntil(this.idCardExpiry);
        return days > 30 && days <= 60;
    }

    // -------------------------------------------------------------------------
    // 4. DRIVING LICENSE & TACHOGRAPH SENSORS (Vozačke dozvole i CPC licence)
    // -------------------------------------------------------------------------
    public boolean isDriverLicenseExpired() {
        return this.drivingLicenseExpiry != null && getDaysUntil(this.drivingLicenseExpiry) < 0;
    }

    public boolean isDriverLicenseCritical() {
        long days = getDaysUntil(this.drivingLicenseExpiry);
        return days >= 0 && days <= 3;
    }

    public boolean isDriverLicenseUrgent() {
        long days = getDaysUntil(this.drivingLicenseExpiry);
        return days > 3 && days <= 30;
    }

    public boolean isDriverLicenseUpcoming() {
        long days = getDaysUntil(this.drivingLicenseExpiry);
        return days > 30 && days <= 60;
    }

    public boolean isTachographExpired() {
        return this.tachographCardExpiry != null && getDaysUntil(this.tachographCardExpiry) < 0;
    }

    public boolean isTachographCritical() {
        long days = getDaysUntil(this.tachographCardExpiry);
        return days >= 0 && days <= 3;
    }

    public boolean isTachographUrgent() {
        long days = getDaysUntil(this.tachographCardExpiry);
        return days > 3 && days <= 30;
    }

    public boolean isTachographUpcoming() {
        long days = getDaysUntil(this.tachographCardExpiry);
        return days > 30 && days <= 60;
    }
// -------------------------------------------------------------------------
    // BZR SENSORS (1 year)
    // -------------------------------------------------------------------------

    public LocalDate getBzrExpiryDate() {
        if (this.safetyTrainingDate == null) return null;
        return this.safetyTrainingDate.plusYears(1);
    }

    public boolean isBzrExpired() {
        LocalDate expiry = getBzrExpiryDate();
        return expiry != null && getDaysUntil(expiry) < 0;
    }

    public boolean isBzrCritical() {
        LocalDate expiry = getBzrExpiryDate();
        long days = getDaysUntil(expiry);
        return days >= 0 && days <= 3;
    }

    public boolean isBzrUrgent() {
        LocalDate expiry = getBzrExpiryDate();
        long days = getDaysUntil(expiry);
        return days > 3 && days <= 30;
    }

    public boolean isBzrUpcoming() {
        LocalDate expiry = getBzrExpiryDate();
        long days = getDaysUntil(expiry);
        return days > 30 && days <= 60;
    }

    /**
     * Checks employee profile and returns an exact list of missing mandatory documents/data.
     *
     * @return String detailing missing items, or empty string if complete
     */
    public String getMissingDocumentationDetails() {
        ArrayList<String> missing = new ArrayList<>();

        if (this.bankAccount == null || this.bankAccount.trim().isEmpty()) {
            missing.add("Tekući račun");
        }
        if (this.medicalExamDate == null) {
            missing.add("Lekarski pregled");
        }
        if (this.safetyTrainingDate == null) {
            missing.add("BZR Obrazac 6");
        }
        if (this.idCardExpiry == null) {
            missing.add("Lična karta");
        }
        if (this.phone == null || this.phone.trim().isEmpty()) {
            missing.add("Kontakt telefon");
        }
        if (missing.isEmpty()) {
            return ""; //All complete
        }
        return String.join(", ", missing);

    }

    /**
     * Quick check if documentation is incomplete.
     *
     * @return true if any required document is missing
     */
    public boolean hasIncompleteDocumentation() {
        return !getMissingDocumentationDetails().isEmpty();
    }

    // =========================================================================
    // STATUTORY ONBOARDING VALIDATOR
    // =========================================================================

    /**
     * Validates if the employee profile meets all mandatory legal requirements
     * for official registration (CROSO) and employment contract generation
     *
     * @return list of missing mandatory legal fields
     */
    public ArrayList<String> getOnboardingValidationErrors(){
        ArrayList<String> errors = new ArrayList<>();

        // 1. Full name
        if (this.fullName == null || this.fullName.trim().isEmpty() || this.fullName.equals("NO NAME")){
    errors.add("Ime i prezime radnika");
        }

        // 2. Personal ID must have 13 digits
        if (this.personalId == null || !this.personalId.matches("\\d{13}")){
            errors.add("JMBG (mora sadržati tačno 13 cifara)");
        }

        // 3. Address and city
        if (this.address == null || this.address.trim().isEmpty()){
            errors.add("Adresa prebivališta iz lične karte");
        }
        if (this.city == null || this.city.trim().isEmpty()){
            errors.add("Mesto/Grad prebivališta");
        }

        // 4. Profession
        if (this.profession == null || this.profession.trim().isEmpty()){
            errors.add("Zanimanje iz diplome");
        }

        // 5. Systematization workplace
        if (this.jobPosition == null || this.jobPosition.trim().isEmpty()){
            errors.add("Radno mesto (pozicija)");
        }

        // 6. Agreed earnings
        if (this.individual_salary <=0){
            errors.add("Ugovorena osnovna zarada (mora biti veća od 0)");
        }

        // 7. Start date
        if (this.contractStartDate == null){
            errors.add("Datum stupanja na rad (početak ugovora)");
        }

        return errors;

    }

    /**
     * Checks if the employee is legally ready for registration and contract generation.
     *
     * @return true if 100% compliant
     */
    public boolean isReadyForEmploymentContract(){
        return getOnboardingValidationErrors().isEmpty();
    }







}
