package hr_commander.interfaces;

/**
 * Interface that acts as a contract for employees with special permits.
 */
public interface Licensable {

    // Every licensed worker must show how they use it.
    void workWithLicense();

    // Every license must have a unique identification number.
    String getLicenseNumber();

}
