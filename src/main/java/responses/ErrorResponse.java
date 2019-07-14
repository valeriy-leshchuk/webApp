package responses;

import lombok.Getter;

//TODO figure out why it is not returned as json
public class ErrorResponse extends AbstractResponse
{
    private static final String DEFAULT_STATUS_INDICATOR = "Failure";

    @Getter private String[] data;

    public ErrorResponse(String... data)
    {
        this.data = data;
        statusIndicator = DEFAULT_STATUS_INDICATOR;
    }
}
