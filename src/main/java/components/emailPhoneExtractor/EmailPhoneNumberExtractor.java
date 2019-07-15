package components.emailPhoneExtractor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.comparator.Comparators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailPhoneNumberExtractor
{
    private static final String PHONE_PATTERN = "\\+[0-9]{12}";
    private static final String EMAIL_PATTERN = "[a-zA-Z]([\\w.-]*@(\\w*)*\\w*)";

    public static List<ParsedEntity> extract(String fromStr)
    {
        List<ParsedEntity> result = new LinkedList<>();
        result.addAll(parseEntitiesByPattern(fromStr, PHONE_PATTERN));
        result.addAll(parseEntitiesByPattern(fromStr, EMAIL_PATTERN));
        result.sort((o1, o2) -> Comparators.comparable().compare(o1.getStartPos(), o2.getStartPos()));
        return result;
    }

    private static List<ParsedEntity> parseEntitiesByPattern(String str, String pattern)
    {
        List<ParsedEntity> parsedEntities = new ArrayList<>();
        Matcher matcher = Pattern.compile(pattern).matcher(str);

        int lastIndex = 0;
        while (matcher.find())
        {
            String match = matcher.group();
            int start = str.indexOf(match, lastIndex);
            int end = start + match.length();
            parsedEntities.add(new ParsedEntity(match, start, end));
            lastIndex = end;
        }
        return parsedEntities;
    }

    private EmailPhoneNumberExtractor()
    {
    }

    @AllArgsConstructor
    @Getter
    private static class ParsedEntity
    {
        private String value;
        private int startPos;
        private int endPos;
    }
}
