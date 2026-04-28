package hr_commander.interfaces;

import java.time.LocalDate;

/**
 * Interface for heavy machinery operators.
 */
public interface MachineOperatable {

    // When does the machine certificate expire?
    LocalDate getMachineCertificateExpiry();
}
