package hr_commander.services;

import hr_commander.models.CompanyProfile;
import hr_commander.models.Employee;
import hr_commander.models.VacationRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Service engine responsible for assembling official legal documents, contracts, and decisions.
 */
public class DocumentEngine {

    /**
     * Reads the external vacation template, populates placeholders, and saves the final document.
     *
     * @param emp     employee taking leave
     * @param vac     vacation record details
     * @param company employer legal profile
     * @return Path to generated file or null if failed
     */
    public String generateVacationDecision(Employee emp, VacationRecord vac, CompanyProfile company) {

        Path templatePath = Path.of("templates/resenje_odmor_sablon.txt");

        try {
            String content = Files.readString(templatePath);

            content = content.replace("{{BROJ_UGOVORA}}", emp.getActiveContractNumber());
            content = content.replace("{{NAZIV_FIRME}}", company.getCompanyName());
            content = content.replace("{{GRAD}}", company.getCity());
            content = content.replace("{{ADRESA}}", company.getAddress());
            content = content.replace("{{DIREKTOR}}", company.getDirectorName());
            content = content.replace("{{DATUM_REŠENJA}}", vac.getFormattedDate(vac.getDecisionDate()));
            content = content.replace("{{IME_RADNIKA}}", emp.getFullName());
            content = content.replace("{{POZICIJA}}", emp.getJobPosition());
            content = content.replace("{{BROJ_DANA}}", String.valueOf(vac.getWorkingDaysUsed()));
            content = content.replace("{{DATUM_OD}}", vac.getFormattedDate(vac.getStartDate()));
            content = content.replace("{{DATUM_DO}}", vac.getFormattedDate(vac.getEndDate()));
            content = content.replace("{{DATUM_POVRATKA}}", vac.getFormattedDate(vac.getReturnToWorkDate()));

            String safeFileName = emp.getFullName().replace(" ", "_");
            Path outputPath = Path.of("generated_docs/Rešenje_" + safeFileName + ".txt");

            Files.writeString(outputPath, content);

            System.out.println("USPEH: Rešenje uspešno kreirano na putanji: " + outputPath);
            return outputPath.toString();
        } catch (IOException e) {
            System.out.println("GREŠKA PRI RADU SA FAJLOVIMA: " + e.getMessage());
            return null;


        }
    }


}
