package hr_commander;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.ArrayList;
import hr_commander.models.Employee;
import hr_commander.models.MachineryOperator;
import hr_commander.models.TruckDriver;
import hr_commander.models.OfficeWorker;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import hr_commander.models.EmployeeDAO;
import java.util.Arrays;

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
    }
}

