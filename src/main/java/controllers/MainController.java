package controllers;

import org.springframework.web.bind.annotation.*;
import responses.SuccessResponse;

@RestController
public class MainController
{
    @GetMapping(value = {"/"})
    public SuccessResponse returnDefaultResponse(String value)
    {
        return new SuccessResponse("yes, it works");
    }
}
