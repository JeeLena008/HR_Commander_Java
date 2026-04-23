package hr_commander;

import java.util.HashMap;
import java.util.ArrayList;
import hr_commander.models.Employee;
import hr_commander.models.MachineryOperator;
import hr_commander.models.TruckDriver;
import hr_commander.models.OfficeWorker;
import java.time.LocalDate;

/**
 * Main Testing Class
 */
public class TestUnit {

    public static void main(String[] args) {

        // 1. Creating a Truck Driver (Example: Marko)
        TruckDriver driver1 = new TruckDriver(
                2500.0, // perDiemAmount
                "Marko Markovic", // fullName
                "1501985710012", // personalId
                85000.0, // baseSalary
                "CON-2026-001", // activeContractNumber
                LocalDate.of(2026, 3, 1),// contractStartDate
                LocalDate.of(2027, 3, 1),// contractEndDate
                true // isPermanent
        );

        // Using SETTERS for additional info
        driver1.setAddress("Bulevar Oslobodjenja 12");
        driver1.setCity("Beograd");
        driver1.setPhone("064/123-456");
        driver1.setLicenseExpiryDate(LocalDate.now().plusMonths(2));
        driver1.setOldVacation(5);// 5 days left from last year
        driver1.setNewVacation(20);//Got 20 days for this year
        driver1.setDaysOfVacationUsed(7);//Used 7 days

        // Setting medical and safety training dates
        driver1.setMedicalExamDate(LocalDate.of(2024, 3, 15));
        driver1.setSafetyTrainingDate(LocalDate.of(2024, 5, 10));

      

        // 2. Creating a Office Worker (Example: Ana)
        OfficeWorker ana = new OfficeWorker(
                "Ana Anic", //fullName
                "2233455715000", // personalId
                100000.0, // baseSalary
                "CON-2026-002", // activeContractNumber
                LocalDate.of(2026, 3, 31), // contractStartDate
                LocalDate.of(2026, 6, 30), // contractEndDate
                true // isPermanent  

        );
        // Using SETTERS for additional info
        ana.setAddress("Brace Nedica bb");
        ana.setCity("Valjevo");
        ana.setPhone("064/111-333");
        ana.setFirstHireDate(LocalDate.of(2024,1,1));
        ana.setJobPosition("Saradnik u finansijama");

        //Add overtime hours for calculation
        ana.addOvertime(15);

    

        //3.Creating a Machinery Operator (Example: Nikola)
        MachineryOperator operator1 = new MachineryOperator(
                3000.0, //perDiemAmount       
                "Nikola Nikolic", //fullName
                "0506990710055", //personalId
                90000.0, //baseSalary
                "CON-2026-003", //activeContractNumber
                LocalDate.of(2026, 5, 10), //start
                LocalDate.of(2027, 5, 10), //end
                true //isPermanent
        );
        // Using SETTERS for additional info
        operator1.setCity("Novi Sad");
        
        //1. We create a map (Key is JMBG, Value is the Employee object
        HashMap<String, Employee> employeeMap = new HashMap<>();
        
        //2. Adding data to the map
        employeeMap.put(driver1.getPersonalId(), driver1);
        employeeMap.put(ana.getPersonalId(), ana);
        employeeMap.put(operator1.getPersonalId(), operator1);
        
        //3. Search test
        System.out.println("\n--- BRZA PRETRAGA PO JMBG-u ---");
        String trazeniJMBG = "2233455715000";
        
        Employee pronadjeni = employeeMap.get(trazeniJMBG);
        
        if(pronadjeni != null){
            System.out.println("Pronadjen radnik: " + pronadjeni.getFullName() + "-" + pronadjeni.getJobPosition());
        }else{
            System.out.println("Radnik sa JMBG-om " + trazeniJMBG + "nije u bazi.");
        }
        
        HashMap<Integer, Employee> idMap = new HashMap<>();
        
        ana.setEmployeeID(101);
        idMap.put(ana.getEmployeeID(), ana);
        
        System.out.println("Radnika sa ID 101 je: " + idMap.get(101).getFullName());

  //1. Creating a list for all employees
  ArrayList<Employee> staffList = new ArrayList<>();
  
  //2. Adding employees to the list
  staffList.add(driver1);
  staffList.add(ana);
  staffList.add(operator1);
  
  //3. FOREACH
        System.out.println("---PROVERA STATUSA UGOVORA---");
        for (Employee e : staffList){
            if (e.isTimeForPermanentContract()){
                System.out.println("ALARM: Radnik " + e.getFullName() + "mora dobiti ugovor na NEODREĐENO!");
            }else{
                System.out.println("Radnik " + e.getFullName() + "je u redu sa ugovorom. ");
                
            }
        }
        System.out.println("\n--- STANJE ODMORA ---");
        for (Employee e : staffList){
            System.out.println(e.getFullName() + "ima jos " + e.calculateRemainingVacation() + "dana odmora.");
        }
        
        System.out.println("\n--- TEST SIGURNOSTI (TRY/CATCH) ---");
        
        String unosSaEkrana = "85ooo";
                
                try{
                    //Trying to convert text to number
                    double plataIzUnosa = Double.parseDouble(unosSaEkrana);
                    System.out.println("Uspesno uneta plata: " + plataIzUnosa);
                    
                }catch (Exception e){
                    //If an error occurs, the program comes HERE instead of crashing!
                    System.out.println("GRESKA: Uneli ste slova umesto brojeva! Molimo proverite unos.");
                    System.out.println("Detalji greske za programera: " + e.getMessage());
                }
                System.out.println("\nProgram nastavlja da radi normalno... Cestitamo!");
    }

}
