package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import responses.SuccessResponse;

@RestController
public class ReturnProviderValueController
{
    @GetMapping(value = {"returnValue"})
    public SuccessResponse getSomeData(String value)
    {
        return new SuccessResponse("provided value is " + value);
    }
}
