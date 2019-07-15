package components.dateUtils.entities;

import lombok.Getter;

public class Day
{
    enum Name {Mon, Tue, Wed, Thu, Fri, Sat, Sun}

    @Getter private int numInWeek;
    @Getter private int numInMonth;
    @Getter private String name;

    public Day(int numInWeek, int numInMonth)
    {
        this.numInWeek = numInWeek;
        this.numInMonth = numInMonth;
        this.name = getDayNameFromNumber(numInWeek);
    }

    private static String getDayNameFromNumber(int num)
    {
        return Name.values()[num-1].toString();
    }
}
