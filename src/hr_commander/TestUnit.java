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
        // LIVE TEST: SUPERVISOR IN ACTION WITH LIVE DATABASE
        // ========================================================

        System.out.println("\n=========================================");
        System.out.println("    HR COMMANDER - SUPERVISOR REPORT       ");
        System.out.println("===========================================");


        //1. Clerk brings all employees from database
        ArrayList<Employee> dbStaff = dao.getAllEmployeesFromDB();

        //2. We hand over the list to our Supervisor
        Supervisor supervisor = new Supervisor(dbStaff);

        //3. Supervisor pulls the critical list (0-3 days)
        ArrayList<Employee> critical = supervisor.getCriticalContracts();
        System.out.println("\n KRITIČNI UGOVORI (0-3 DANA): " + critical.size());
        for (Employee e : critical) {
            System.out.println("     ->" + e.getFullName() + " | Ističe: " + e.getFormattedDate(e.getContractEndDate()));
        }
            //4. Supervisor pulls the urgent list (4-30 days)
            ArrayList<Employee> urgent = supervisor.getUrgentContracts();
            System.out.println("\n HITNI UGOVORI (4-30 DANA): "+ urgent.size());
            for (Employee e: urgent){
                System.out.println("    -> " + e.getFullName() + "|Ističe: " + e.getFormattedDate(e.getContractEndDate()));
            }

            //5. Supervisor pulls employees hitting the 24-month legal limit
            ArrayList<Employee> permanent = supervisor.getCandidatesForPermanent();
            System.out.println("\n KANDIDATI ZA UGOVOR NA NEODREĐENO (24 MESECA LIMIT): " + permanent.size());
            for (Employee e : permanent){
                System.out.println("   ----> " + e.getFullName() + "| Prvo zaposlenje: " + e.getFormattedDate(e.getFirstHireDate()));

            }
            System.out.println("\n=========================================================================");

        }
    }

