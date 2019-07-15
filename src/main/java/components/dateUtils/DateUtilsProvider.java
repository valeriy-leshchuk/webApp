package components.dateUtils;

import components.dateUtils.entities.Month;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public final class DateUtilsProvider
{

    public static List<Month> getMonthes(int fiscalDateFrom, int fiscalDateTo)
    {
        fiscalDateFrom = fiscalDateFrom*100+1;//to convert it to yyyyMM01
        fiscalDateTo = fiscalDateTo*100+1;//to convert it to yyyyMM01

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate ldFrom = LocalDate.parse(String.valueOf(fiscalDateFrom), dtf);
        LocalDate ldTo = LocalDate.parse(String.valueOf(fiscalDateTo), dtf);

        List<Month> result = new ArrayList<>();
        while(ldFrom.compareTo(ldTo)<=0)
        {
            Month month = new Month(ldFrom.getYear(), ldFrom.getMonthValue());
            result.add(month);
            ldFrom = ldFrom.plusMonths(1);
        }
        return result;
    }


    public static Month getDayInfo(int year, int dayInYear)
    {
        LocalDate ld = LocalDate.of(year, 1, 1);
        ld = ld.plusDays(dayInYear);
        Month month = new Month(year, ld.getMonthValue(), ld.getDayOfMonth(), ld.getDayOfMonth());
        return month;
    }


    public static List<String> getMonthesFormatted(int fiscalDateFrom, int fiscalDateTo)
    {
        fiscalDateFrom = fiscalDateFrom*100+1;//to convert it to yyyyMM01
        fiscalDateTo = fiscalDateTo*100+1;//to convert it to yyyyMM01

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate ldFrom = LocalDate.parse(String.valueOf(fiscalDateFrom), dtf);
        LocalDate ldTo = LocalDate.parse(String.valueOf(fiscalDateTo), dtf);

        List<String> result = new ArrayList<>();
        while(ldFrom.compareTo(ldTo) <=0)
        {
            String year = String.valueOf(ldFrom.getYear());
            String month = Month.getMonthNameFromNumber(ldFrom.getMonthValue());
            result.add(year + "-" + month);
            ldFrom = ldFrom.plusMonths(1);
        }
        return result;
    }

    private DateUtilsProvider()
    {
    }
}
