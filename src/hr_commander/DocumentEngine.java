package hr_commander;

import hr_commander.models.Employee;
import hr_commander.models.VacationRecord;
import hr_commander.utils.CompanyConfig;


/**
 * Service engine responsible for assembling official legal documents, contracts, and decisions.
 */
public class DocumentEngine {

/**
 * Generates formal statutory Vacation Leave Decision text based on Serbian Labor Law (Art. 192 & 68-75).
 *
 * @param emp employee taking leave
 * @param vac vacation record details
 * @return full legal document text
 */
public String generateVacationDecision(Employee emp, VacationRecord vac){
    return "Na osnovu člana 192, a u vezi sa čl. 68. do 75. Zakona o radu (\"Službeni glasnik RS\", br. 24/2005, 61/2005,\n" +
            "54/2009, 32/2013, 75/2014, 13/2017 (Odluka US), 113/2017 i 95/2018 (autentično tumačenje), dalje: Zakon),\n" +
            "a u skladu sa Ugovorom o radu br. " + emp.getActiveContractNumber() + ",\n" +
            CompanyConfig.getCompanyName() + ", " + CompanyConfig.getCompanyCity() + ", " + CompanyConfig.getCompanyAddress() + " (naziv i sedište poslodavca),\n" +
            "direktor " + CompanyConfig.getDirectorName() + ", dana " + vac.getFormattedDate(vac.getDecisionDate()) + " godine, donosi:\n\n" +
            "                               REŠENJE O KORIŠĆENJU GODIŠNJEG ODMORA\n\n" +
            "1. Zaposlenom " + emp.getFullName() + " na poslovima " + emp.getJobPosition() + "\n" +
            "   odobrava se korišćenje godišnjeg odmora u trajanju od " + vac.getWorkingDaysUsed() + " radnih dana.\n\n" +
            "2. Godišnji odmor koristiće se u periodu od " + vac.getFormattedDate(vac.getStartDate()) + " do " + vac.getFormattedDate(vac.getEndDate()) + " godine.\n\n" +
            "3. Zaposleni je dužan da se javi na rad dana " + vac.getFormattedDate(vac.getReturnToWorkDate()) + " godine (prvi radni dan).\n\n" +
            "Za vreme korišćenja godišnjeg odmora zaposlenom pripada naknada zarade u skladu sa Zakonom i opštim aktom.\n\n" +
            "Obrazloženje:\n" +
            "Zaposleni u skladu sa Zakonom i kriterijumima utvrđenim Ugovorom o radu br. " + emp.getActiveContractNumber() + "\n" +
            "ima pravo na godišnji odmor. Vreme korišćenja određeno je u skladu sa potrebama procesa rada i uz konsultaciju sa zaposlenim.\n\n" +
            "Pouka o pravnom leku:\n" +
            "Protiv ovog rešenja zaposleni može da pokrene spor pred nadležnim sudom u roku od 60 dana od dana dostavljanja.\n\n" +
            "U " + CompanyConfig.getCompanyCity() + ",\n" +
            "Dana: " + vac.getFormattedDate(vac.getDecisionDate()) + " godine\n\n" +
            "                                                        _____________________________________\n" +
            "                                                        " + CompanyConfig.getDirectorName() + ", direktor\n\n" +
            "Dostavljeno:\n" +
            "- Zaposlenom\n" +
            "- Službi za obračun zarada\n" +
            "- Arhivi";
}


}
