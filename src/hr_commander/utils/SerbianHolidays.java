package hr_commander.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for managing Serbian statutory holidays and working days.
 */
public class SerbianHolidays {

    /**
     * Returns official non-working holidays in Serbia for 2026.
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
        holidays.add(LocalDate.of(2026, 2, 17)); //Statehood Day (Observed on Monday)
        holidays.add(LocalDate.of(2026, 4, 10)); //Good Friday
        holidays.add(LocalDate.of(2026, 4, 12)); //Easter
        holidays.add(LocalDate.of(2026, 4, 13)); //Easter Monday
        holidays.add(LocalDate.of(2026, 5, 1)); //Labour Day
        holidays.add(LocalDate.of(2026, 5, 2)); //Labour Day
        holidays.add(LocalDate.of(2026, 11,11)); // Armistice Day
        return holidays;

    }

    /**
     * Returns official non-working holidays in Serbia for 2027.
     */
    public static List<LocalDate> getHolidays2027() {
        List<LocalDate> holidays = new ArrayList<>();
        holidays.add(LocalDate.of(2027, 1, 1));  //New Year
        holidays.add(LocalDate.of(2027, 1, 2));  //New Year
        holidays.add(LocalDate.of(2027, 1, 7));  //Orthodox Christmas
        holidays.add(LocalDate.of(2027, 2, 15)); //Statehood Day
        holidays.add(LocalDate.of(2027, 2, 16)); //Statehood Day
        holidays.add(LocalDate.of(2027, 4, 30)); //Good Friday
        holidays.add(LocalDate.of(2027, 5, 2));  //Easter
        holidays.add(LocalDate.of(2027, 5, 3));  //Easter Monday
        holidays.add(LocalDate.of(2027, 5, 4));  //Labour Day (Observed)
        holidays.add(LocalDate.of(2027, 11,11)); // Armistice Day
        return holidays;
    }

    /**
     * Checks if a specific date is a weekend or a public holiday.
     *
     * @param date the date to check
     * @return true if holiday/weekend, false otherwise
     */
    public static boolean isHolidayOrWeekend(LocalDate date) {
        if (date == null) return false;


        // 1. Check for Saturday or Sunday
        DayOfWeek day = date.getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return true;
        }



       // 2. Check state holiday for current year
        int year = date.getYear();
        List<LocalDate> holidays = new ArrayList<>();

        if (year == 2026) {
            holidays = getHolidays2026();
        } else if (year == 2027){
            holidays = getHolidays2027();
        }
        return holidays.contains(date);
    }
    /**
     * Calculates actual statutory working days between two dates,
     * skipping weekends and official national holidays (Labor Law Art. 69).
     *
     * @param start start date of leave
     * @param end end date of leave
     * @return number of working days
     */
    public static int calculateWorkingDays(LocalDate start, LocalDate end) {
        if (start == null || end == null || start.isAfter(end)) return 0;

        int workingDays = 0;
        LocalDate current = start;

        while (!current.isAfter(end)){
            if (!isHolidayOrWeekend(current)){
                workingDays++;
            }
            current = current.plusDays(1);
        }
        return  workingDays;
    }

}
