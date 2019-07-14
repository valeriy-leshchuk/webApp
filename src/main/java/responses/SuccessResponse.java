package responses;

import lombok.Getter;

public class SuccessResponse extends AbstractResponse
{
    private static final String DEFAULT_STATUS_INDICATOR = "Success";

    @Getter private Object body;

    public SuccessResponse(Object body)
    {
        this.body = body;
        statusIndicator = DEFAULT_STATUS_INDICATOR;
    }
}
