import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

/*
  Represents a Tool which can be checked out by a customer.
 */
public class Tool {

    private final Code code;
    private final String toolType;
    private final String brand;
    private final BigDecimal dailyCharge;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    public Tool(
            Code code,
            String toolType,
            String brand,
            BigDecimal dailyCharge,
            boolean weekdayCharge,
            boolean weekendCharge,
            boolean holidayCharge
    ) {
        this.code = code;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public Code getCode() {
        return code;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    /**
     * Calculates the total number of days to charge for a specific tool. This takes
     * into account weekday charges, weekend charges, and holiday charges
     * @param tool from inventory
     * @param checkoutDate the date a customer first wants to rent tool
     * @param dueDate the date when a customer will return tool
     * @return the number of chargeable days
     */
    public static int calculateChargeableDays(Tool tool, LocalDate checkoutDate, LocalDate dueDate) {
        int chargeableDays = 0;

        /*
         Per requirements: chargeable days calculation starts 1 day after checkoutDate.
         */
        LocalDate currentDate = checkoutDate.plusDays(1);

        while (!currentDate.isAfter(dueDate)) {
            if (isChargeableDay(tool, currentDate)) {
                chargeableDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return chargeableDays;
    }

    /**
     * Determines if the specific date is chargeable based on tool
     * @param tool used for comparison
     * @param date used for comparison
     * @return true if date is chargeable, false if not
     */
    private static boolean isChargeableDay(Tool tool, LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        boolean isHoliday = isHoliday(date);
        boolean isWeekday = (dayOfWeek >= 1 && dayOfWeek <= 5);

        if (isHoliday && !tool.isHolidayCharge()) { // Very first, check if date is a holiday and if they should not be charged (weekday or weekend)
            return false;
        } else if (!isWeekday && !tool.isWeekendCharge()) { // If it's the weekend and tool shouldn't be charged on the weekend
            return false;
        } else
            return !isWeekday || tool.isWeekdayCharge(); // If it's not a holiday or a weekend then we check if we should charge for the weekday
    }

    /**
     * Determines whether the LocalDate passed in is Fourth of July or Labor Day
     * @param date a LocalDate representing a day which should be checked
     * @return true if date is Fourth of July or Labor Day, false if not
     */
    private static boolean isHoliday(LocalDate date) {
        return isObservableFourthOfJuly(date) || isLaborDay(date);
    }

    /**
     * Determines if a LocalDate is observed fourth of July
     * @param date LocalDate representing a day
     * @return true if date is observed fourth of July, false if not
     */
    private static boolean isObservableFourthOfJuly(LocalDate date) {
        return date.equals(observedFourthOfJuly(date.getYear()));
    }

    /**
     * Determines the observed day of fourth of July
     * @param year to check observed date
     * @return day when fourth of July is observed of given year
     */
    private static LocalDate observedFourthOfJuly(int year) {
        int julyMonth = 7;
        int fourthDay = 4;
        LocalDate fourthOfJuly = LocalDate.of(year, julyMonth, fourthDay);
        DayOfWeek julyFourthDayOfWeek = fourthOfJuly.getDayOfWeek();

        if (julyFourthDayOfWeek == DayOfWeek.SATURDAY) {
            return fourthOfJuly.minusDays(1);
        } else if (julyFourthDayOfWeek == DayOfWeek.SUNDAY) {
            return fourthOfJuly.plusDays(1);
        } else {
            return fourthOfJuly;
        }
    }

    /**
     * Determines if a LocalDate is Labor Day
     * @param date LocalDate representing a day
     * @return true if date is Labor Day, false if not
     */
    private static boolean isLaborDay(LocalDate date) {
        int septemberMonth = 9;
        int seventhDay = 7;
        int dateDayOfMonth = date.getDayOfMonth();

        // First check if date is a Monday
        if (date.getDayOfWeek() != DayOfWeek.MONDAY) {
            return false;
        }

        // Check if date is in September and within the first week
        return date.getMonthValue() == septemberMonth
                && dateDayOfMonth <= seventhDay;
    }

    /*
      Tool Codes
     */
    public enum Code {
        CHNS,
        JAKD,
        JAKR,
        LADW
    }
}
