package hr_commander.interfaces;

import java.time.LocalDate;

/**
 * Interface for employees who drive company vehicles.
 */
public interface Drivable {

    // Every driver must show when their license expires
    LocalDate getLicenseExpiryDate();

    // We can also track tachograph card
    LocalDate getTachographExpiryDate();
}
