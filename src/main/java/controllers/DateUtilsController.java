package controllers;

import components.dateUtils.DateUtilsProvider;
import components.dateUtils.entities.Month;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import responses.SuccessResponse;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "dateUtils")
public class DateUtilsController
{
    private static final Logger logger = LoggerFactory.getLogger(DateUtilsController.class);

    private static final String YEAR_PATTERN = "([0-9]{4})";
    private static final String FISCAL_DATE_PATTERN = YEAR_PATTERN + "((0[1-9])|(1[12]))";

    @GetMapping(value = "getMonthes")
    @ResponseBody
    public SuccessResponse getMonthes(
        @RequestParam("fiscalDateFrom") @Pattern(regexp = FISCAL_DATE_PATTERN) String fiscalDateFrom,
        @RequestParam("fiscalDateTo") @Pattern(regexp = FISCAL_DATE_PATTERN) String fiscalDateTo)
    {
        List<Month> result = DateUtilsProvider.getMonthes(Integer.valueOf(fiscalDateFrom), Integer.valueOf(fiscalDateTo));
        return new SuccessResponse(result);

    }

    @GetMapping(value = "getMonthesFormatted")
    @ResponseBody
    public SuccessResponse getMonthesFormatted(
        @RequestParam("fiscalDateFrom") @Pattern(regexp = FISCAL_DATE_PATTERN) String fiscalDateFrom,
        @RequestParam("fiscalDateTo") @Pattern(regexp = FISCAL_DATE_PATTERN) String fiscalDateTo)
    {
        List<String> result =  DateUtilsProvider.getMonthesFormatted(Integer.valueOf(fiscalDateFrom), Integer.valueOf(fiscalDateTo));
        return new SuccessResponse(result);

    }

    @GetMapping(value = "getDayInfo")
    @ResponseBody
    public SuccessResponse getDayInfo(
        @RequestParam("year") @Pattern(regexp = YEAR_PATTERN) String year,
        @RequestParam("dayInYear") @Min(value = 1) @Max(value = 366) String dayInYear)
    {
        Month resultMonth =  DateUtilsProvider.getDayInfo(Integer.valueOf(year), Integer.valueOf(dayInYear));
        return new SuccessResponse(resultMonth);
    }
}
