package hr_commander.models;

/**
 * Model representing the employer's legal entity profile (Company data).
 */
public class CompanyProfile {

    private int id;
    private String companyName;
    private String city;
    private String address;
    private String directorName;
    private String pib;
    private String mb;



    public CompanyProfile(int id, String companyName, String city, String address, String directorName, String pib, String mb) {
        this.id = id;
        this.companyName = companyName;
        this.city = city;
        this.address = address;
        this.directorName = directorName;
        this.pib = pib;
        this.mb = mb;

    }



        public int getId() {
            return id;
        }

        public String getCompanyName() {
            return companyName;
        }

        public String getCity() {
            return city;
        }

        public String getAddress() {
            return address;
        }

        public String getDirectorName() {
            return directorName;
        }

        public String getPib() {
            return pib;
        }

        public String getMb() {
            return mb;
        }




}
