package hr_commander.utils;

import java.io.InputStream;
import java.util.Properties;


/**
 * Utility class to securely load company profile and legal entity details.
 */
public class CompanyConfig {

    private static Properties props = new Properties();

    static {
        try(InputStream input = CompanyConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(new java.io.InputStreamReader(input, java.nio.charset.StandardCharsets.UTF_8));

            }
        }catch (Exception e){
            System.out.println("Greška pri učitavanju company profila: " + e.getMessage());
        }
    }

    public static String getCompanyName() {
        return props.getProperty("company.name", "NAZIV POSLODAVCA d.o.o.");
    }
    public static String getCompanyCity() {
        return props.getProperty("company.city", "Beograd");
    }
    public static String getCompanyAddress() {
        return props.getProperty("company.address", "Sedište poslodavca bb");
    }
    public static String getDirectorName() {
        return props.getProperty("company.director", "Ovlašćeno lice/Direktor");
    }
    public static String getPib() {
        return props.getProperty("company.pib", "000000000");
    }
    public static String getMb(){
        return props.getProperty("company.mb", "00000000");
    }
}
