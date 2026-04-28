package hr_commander;

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
        ana.setFirstHireDate(LocalDate.of(2024, 1, 1));
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

        if (pronadjeni != null) {
            System.out.println("Pronadjen radnik: " + pronadjeni.getFullName() + "-" + pronadjeni.getJobPosition());
        } else {
            System.out.println("Radnik sa JMBG-om " + trazeniJMBG + " nije u bazi.");
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
        for (Employee e : staffList) {
            if (e.isTimeForPermanentContract()) {
                System.out.println("ALARM: Radnik " + e.getFullName() + " mora dobiti ugovor na NEODREDJENO!");
            } else {
                System.out.println("Radnik " + e.getFullName() + " je u redu sa ugovorom. ");

            }
        }
        System.out.println("\n--- STANJE ODMORA ---");
        for (Employee e : staffList) {
            System.out.println(e.getFullName() + " ima jos " + e.calculateRemainingVacation() + " dana odmora.");
        }

        System.out.println("\n--- TEST SIGURNOSTI (TRY/CATCH) ---");

        String unosSaEkrana = "85ooo";

        try {
            //Trying to convert text to number
            double plataIzUnosa = Double.parseDouble(unosSaEkrana);
            System.out.println("Uspesno uneta plata: " + plataIzUnosa);

        } catch (Exception e) {
            //If an error occurs, the program comes HERE instead of crashing!
            System.out.println("GRESKA: Uneli ste slova umesto brojeva! Molimo proverite unos.");
            System.out.println("Detalji greske za programera: " + e.getMessage());
        }
        System.out.println("\nProgram nastavlja da radi normalno... Cestitamo!");

        System.out.println("\n--- CUVANJE PODATAKA U FAJL ---");

        //File path
        String putanja = "baza_zaposlenih.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(putanja))) {

            //Writing the header
            writer.write("ID; Ime i Prezime; JMBG; Radno Mesto");
            writer.newLine();

            //Iterating through the list and writing each employee to a new line
            for (Employee e : staffList) {
                writer.write(e.toFileFormat());
                writer.newLine();
            }
            System.out.println("PODACI SU USPESNO SACUVANI U FAJL: " + putanja);

        } catch (IOException ex) {
            //If a disk/file error occurs
            System.out.println("GRESKA PRILIKOM CUVANJA FAJLA: " + ex.getMessage());
        }
        procitajIzFajla("baza_zaposlenih.csv");
        stampajIzvestajOPlatama(staffList);
    }

    /**
     * Reads data from the CSV file and prints it to the console.
     *
     * @param putanja path to the file
     */
    public static void procitajIzFajla(String putanja) {
        System.out.println("\n--- CITANJE PODATAKA IZ FAJLA ---");

        //FAST READER
        try (BufferedReader reader = new BufferedReader(new FileReader(putanja))) {
            String linija;

            //Skipping the header line
            reader.readLine();

            //While there is text in the file, read line by line
            while ((linija = reader.readLine()) != null) {

                //Splitting the line by comma
                String[] delovi = linija.split(";");

                if (delovi.length >= 4) {
                    String id = delovi[0];
                    String ime = delovi[1];
                    String jmbg = delovi[2];
                    jmbg = jmbg.replace("=\"", "").replace("\"", "");
                    String pozicija = delovi[3];

                    System.out.println("Pronadjen u fajlu -> ID: " + id + "| Ime: " + ime + "| Pozicija: " + pozicija);
                }
            }

        } catch (IOException e) {
            System.out.println("GRESKA: Ne mogu da procitam fajl! " + e.getMessage());

        }

    }
    /**
     * Calculates and prints a summary payroll report for the entire company
     * 
     * @param list of all employees
     */
    
    public static void stampajIzvestajOPlatama(ArrayList<Employee> list){
        double ukupniTrosakPlata = 0;
        int brojAktivnihRadnika = 0;
        
        System.out.println("\n--- FINANSIJSKI IZVESTAJ (PAYROLL REPORT) ---");
        
        for (Employee e : list){
            //Only if employee is active
            if (e.isActive()){
                double plata = e.calculateFinalSalary();
                ukupniTrosakPlata += plata;
                brojAktivnihRadnika++;
                
                System.out.println("Radnik: " + e.getFullName() + "| Neto za isplatu: " + plata + " RSD");
            }
        }
        System.out.println("---------------------------------------------------");
        System.out.println("UKUPNO ZA ISPLATU: " + ukupniTrosakPlata + "RSD");
        System.out.println("BROJ RADNIKA NA PLATNOM SPISKU: " + brojAktivnihRadnika);
        
    }
}
