package hr_commander;

import java.util.ArrayList;

import hr_commander.models.*;

import java.time.LocalDate;

/**
 * Main Testing Class
 */
public class TestUnit {

    public static void main(String[] args) {

        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            System.out.println("Greška sa enkodingom: " + e.getMessage());
        }
        //=======================================
        //HR SUPERVISOR: CONTRACT EXPIRY CHECK
        //=======================================
        System.out.println("\n--- RUNNING AUTOMATIC SUPERVISOR CHECK ---");

        EmployeeDAO dao = new EmployeeDAO();

        //1. Fetch the "folder" with all employees from the DB
        ArrayList<Employee> allStaff = dao.getAllEmployeesFromDB();

        LocalDate today = LocalDate.now();
        LocalDate criticalLimit = today.plusDays(30); //We look 30 days ahead

        //2. Loop through the folder and check each "file"
        for (Employee e : allStaff) {
            if (e.getContractEndDate() != null) {
                //If the cotract ends before our critical limit, trigger alarm

                if (e.getContractEndDate().isBefore(criticalLimit)) {
                    System.out.println("🚨 ALARM: Contract for [" + e.getFullName() + "] expires on: " + e.getFormattedDate(e.getContractEndDate()));
                }
            }
        }
        System.out.println("--- CHECK COMPLETED ---");

        // ========================================================
        // 🚀 LIVE TEST: ENTERPRISE 360° SUPERVISOR AUDIT
        // ========================================================
        System.out.println("\n========================================================");
        System.out.println("          HR COMMANDER - 360° SUPERVISOR AUDIT          ");
        System.out.println("========================================================");

        ArrayList<Employee> dbStaff = dao.getAllEmployeesFromDB();
        Supervisor supervisor = new Supervisor(dbStaff);

        // 1. PREKRŠAJI / ILEGALAN STATUS (Istekli ugovori, lekarski, BZR)
        ArrayList<Employee> expired = supervisor.getExpiredComplianceAlerts();
        System.out.println("\n🚨 ISTEKLI ROKOVI / PREKRŠAJI (HITNO REŠITI): " + expired.size());
        for (Employee emp : expired) {
            System.out.println("   [!] " + emp.getFullName() + " | Pozicija: " + emp.getJobPosition());
        }

        // 2. KRITIČNI ROKOVI (0 do 3 dana)
        ArrayList<Employee> critical = supervisor.getCriticalAlerts();
        System.out.println("\n⚡ KRITIČNO (0 - 3 DANA): " + critical.size());
        for (Employee emp : critical) {
            System.out.println("   -> " + emp.getFullName() + " | Ugovor ističe: " + emp.getFormattedDate(emp.getContractEndDate()));
        }

        // 3. HITNI ROKOVI (4 do 30 dana)
        ArrayList<Employee> urgent = supervisor.getUrgentAlerts();
        System.out.println("\n🟡 HITNO ZA REŠAVANJE OVOG MESECA (4 - 30 DANA): " + urgent.size());
        for (Employee emp : urgent) {
            System.out.println("   -> " + emp.getFullName() + " | Ugovor ističe: " + emp.getFormattedDate(emp.getContractEndDate()));
        }

        // 4. DIJAGNOSTIKA DOSIJEA (TAČNO ŠTA KOME FALI OD DOKUMENTACIJE!)
        ArrayList<Employee> incomplete = supervisor.getEmployeesWithIncompleteFiles();
        System.out.println("\n📋 DOSIJEI SA NEKOMPLETNOM DOKUMENTACIJOM: " + incomplete.size());
        for (Employee emp : incomplete) {
            System.out.println("   ❌ " + emp.getFullName() + " -> NEDOSTAJE: " + emp.getMissingDocumentationDetails());
        }

        System.out.println("\n========================================================");

        // ========================================================
        // 🏖️ TEST: UPIS GODIŠNJEG ODMORA U BAZU (SQL INSERT)
        // ========================================================
        System.out.println("\n--- TESTIRAMO UPIS ODMORA U MYSQL BAZU ---");

        VacationRecord odmor = new VacationRecord(
                1,
                LocalDate.of(2026, 7, 1),
                LocalDate.of(2026, 7, 7),
                "OLD"
        );
        odmor.setNote("Prvi deo starog odmora - more");
        odmor.setDecisionDate(LocalDate.of(2026, 6, 20)); //Datum resenja

        boolean uspeh = dao.insertVacationRecord(odmor);

        if (uspeh) {
            System.out.println("BRAVO! Odmor je uspešno upisan u MySql bazu podataka!");
        }else{
            System.out.println("❌ Nešto je zapelo pri upisu.");
        }
        //=========================================
        //AUTOMATSKI OBRAČUN DANA I ROKOVA ODMORA
        //=========================================
            System.out.println("\n--- PROVERA SMART KALKULATORA (06.10. - 26.10.) ---");

            //RADNIK IDE NA ODMOR OD 06.10.2026. DO 26.10.2026.
            //NEMA RUČNOG UNOSA BROJA DANA - JAVA RAČUNA SAMA
            VacationRecord testOdmor = new VacationRecord(
                    1,
                    LocalDate.of(2026, 10, 6),
                    LocalDate.of(2026, 10, 26),
                    "NEW"
            );
            System.out.println("Prvi dan odmora: " + testOdmor.getFormattedDate(testOdmor.getStartDate()));
            System.out.println("Poslednji dan odmora: " + testOdmor.getFormattedDate(testOdmor.getEndDate()));
            System.out.println("AUTOMATSKI IZRAČUNATO RADNIH DANA: " + testOdmor.getWorkingDaysUsed() + "dana");
            System.out.println("ZAKONSKI DATUM REŠENJA (15 DANA PRE): " + testOdmor.getFormattedDate(testOdmor.getDecisionDate()));
            System.out.println("DUŽNOST JAVLJANJA NA RAD (PRVI RADNI DAN): " + testOdmor.getFormattedDate(testOdmor.getReturnToWorkDate()));

        // =========================================================================
        // TESTIRAMO SKIDANJE DANA: ZAKON VS INTERNI DOGOVOR FIRME
        // =========================================================================

        System.out.println("\n--- TEST: ODBITAK DANA ODMORA SA STANJA RADNIKA ---");

        Employee radnik = dbStaff.get(0);

        radnik.setOldVacation(5);
        radnik.setNewVacation(20);
        radnik.setDaysOfVacationUsed(0);

        System.out.println(" Radnik: " + radnik.getFullName());
        System.out.println("POČETNO STANJE -> Stari: " + radnik.getOldVacation() + "dana | Novi: " + radnik.getNewVacation() + "dana (Ukupno: 25)");

        radnik.deductVacationDays(testOdmor.getWorkingDaysUsed(), testOdmor.getStartDate(),true);

        System.out.println("\n ODOBREN ODMOR: " + testOdmor.getWorkingDaysUsed() + "radnih dana u oktobru (posle 30. juna)");
        System.out.println("NOVO STANJE NAKON SKIDANJA:");
        System.out.println("   -> Preostalo starog odmora: " + radnik.getOldVacation() + "dana (potrošen prvi po dogovoru!)");
        System.out.println("   -> Preostalo novog odmora: + " + radnik.getNewVacation() + "dana");
        System.out.println("   -> Ukupno iskorišćeno dana: " + radnik.getDaysOfVacationUsed() + "dana");
        System.out.println("   -> Ukupno još preostalo za tekuću godinu: " + radnik.calculateRemainingVacation() + "dana!");


        boolean uspehAžuriranja = dao.updateEmployeeVacationBalance(radnik);

        if (uspehAžuriranja == true) {
            System.out.println("NOVO STANJE ODMORA JE TRAJNO SAČUVANO U SQL BAZI");
        }else {
            System.out.println("GREŠKA PRI AŽURIRANJU BAZE");
        }


    }
}


