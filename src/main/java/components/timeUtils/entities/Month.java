package components.timeUtils.entities;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Month
{
    enum Name {Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec}

    private String name;
    private int num;
    private int year;
    private List<Day> days;

    /**
     * Create a Month with all Days
     * @param year
     * @param numInYear
     */
    public Month(int year, int numInYear)
    {
        name = getMonthNameFromNumber(numInYear);
        num=numInYear;
        this.year = year;

        int lastDayOfMonth = LocalDate.of(year, numInYear, 1)
            .plusMonths(1)
            .minusDays(1)
            .getDayOfMonth();

        days = getListOfDaysInMonthInRange(year, numInYear, 1, lastDayOfMonth);
    }

    /**
     * Create a Month with Days in specified range.
     * @param year
     * @param numInYear
     * @param fromDay
     * @param toDay
     */
    public Month(int year, int numInYear, int fromDay, int toDay)
    {
    }

    public static String getMonthNameFromNumber(int num)
    {
        return Name.values()[num-1].toString();
    }


    private static List<Day> getListOfDaysInMonthInRange(int year, int numInYear, int fromDay, int toDay)
    {
        LocalDate ldFrom = LocalDate.of(year, numInYear, fromDay);
        LocalDate ldTo = LocalDate.of(year, numInYear, toDay);
        List<Day> result = new ArrayList<>();


        //

        return result;
    }

}
