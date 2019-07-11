package components.timeUtils.entities;

public class Day
{
    enum Name {Mon, Tue, Wed, Thu, Fri, Sat, Sun}

    private int numInWeek;
    private int numInmonth;
    private String name;

    public Day(int numInWeek, int numInMonth)
    {
        this.numInWeek = numInWeek;
        this.numInmonth = numInmonth;
        this.name = getDayNameFromNumber(numInWeek);
    }

    private static String getDayNameFromNumber(int num)
    {
        return Name.values()[num-1].toString();
    }
}
