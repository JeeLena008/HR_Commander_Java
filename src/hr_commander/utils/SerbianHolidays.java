package hr_commander.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for tracking national and religious holidays in Serbia
 */
public class SerbianHolidays {

    /**
     * Returns a list of public holidays for the year 2026.
     *
     * @return list of holiday
     */
    public static List<LocalDate> getHolidays2026() {
        List<LocalDate> holidays = new ArrayList<>();

        //-------- TODO: FUTURE API INTEGRATION FOR AUTOMATIC UPDATES ----------
        //FIXED HOLIDAYS
        holidays.add(LocalDate.of(2026, 1, 1)); //New Year
        holidays.add(LocalDate.of(2026, 1, 2)); //New Year
        holidays.add(LocalDate.of(2026, 1, 7)); //Orthodox Christmas
        holidays.add(LocalDate.of(2026, 2, 15)); //Statehood Day
        holidays.add(LocalDate.of(2026, 2, 16)); //Statehood Day
        holidays.add(LocalDate.of(2026, 5, 1)); //Labour Day
        holidays.add(LocalDate.of(2026, 5, 2)); //Labour Day
        holidays.add(LocalDate.of(2026, 11, 11)); // Armistice Day

        //MOVABLE HOLIDAYS (Orthodox Easter 2026)
        holidays.add(LocalDate.of(2026, 4, 10)); //Good Friday
        holidays.add(LocalDate.of(2026, 4, 13)); //Easter Monday

        return holidays;

    }

    /**
     * Checks if a specific date is a weekend or a public holiday.
     *
     * @param date the date to check
     * @return true if holiday/weekend, false otherwise
     */
    public static boolean isHolidayOrWeekend(LocalDate date) {
        //Check for Saturday (6) or Sunday (7)
        if (date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7) {
            return true;
        }

        // Check against the holiday list
        return getHolidays2026().contains(date);
    }
}
