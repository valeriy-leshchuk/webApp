package responses;

import lombok.Getter;

public class ErrorResponse extends AbstractResponse
{
    private static final String DEFAULT_STATUS_INDICATOR = "Failure";

    @Getter private String data;

    public ErrorResponse(String data)
    {
        this.data = data;
        statusIndicator = DEFAULT_STATUS_INDICATOR;
    }
}
