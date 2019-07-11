package components.timeUtils;

import components.timeUtils.entities.Day;
import components.timeUtils.entities.Month;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DateUtilsProvider
{

    public List<Month> getMonthes(int fiscalDateFrom, int fiscalDateTo)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");
        LocalDate ldFrom = LocalDate.parse(String.valueOf(fiscalDateFrom), dtf);
        LocalDate ldTo = LocalDate.parse(String.valueOf(fiscalDateTo), dtf);

        List<Month> result = new ArrayList<>();

        while (!ldFrom.equals(ldTo))
        {
            Month month = new Month(ldFrom.getYear(), ldFrom.getMonthValue());
            result.add(month);
            ldFrom = ldFrom.plusMonths(1);
        }
        return result;
    }


    public Month getDayInfo(int year, int dayInYear)
    {
        LocalDate ld = LocalDate.of(year, 1, 1);
        ld = ld.plusDays(dayInYear);
        Month month = new Month(year, ld.getMonthValue(), ld.getDayOfMonth(), ld.getDayOfMonth());
        return month;
    }


    public List<String> getMonthesFormatted(int fiscalDateFrom, int fiscalDateTo)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");
        LocalDate ldFrom = LocalDate.parse(String.valueOf(fiscalDateFrom), dtf);
        LocalDate ldTo = LocalDate.parse(String.valueOf(fiscalDateTo), dtf);

        List<String> result = new ArrayList<>();
        while (!ldFrom.equals(ldTo))
        {
            String year = String.valueOf(ldFrom.getYear());
            String month = Month.getMonthNameFromNumber(ldFrom.getMonthValue());
            String day = String.valueOf(ldFrom.getDayOfMonth());
            result.add(year + "-" + month + "-" + day);
            ldFrom = ldFrom.plusMonths(1);
        }
        return result;
    }
}






