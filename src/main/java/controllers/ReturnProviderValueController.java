package controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import responses.SuccessResponse;

@Controller
@ResponseBody
@RequestMapping (produces = MediaType.APPLICATION_JSON_VALUE)
public class ReturnProviderValueController
{
    @GetMapping(value = {"returnValue"})
    public SuccessResponse getSomeData(String value)
    {
        return new SuccessResponse("provided value is " + value);
    }
}
