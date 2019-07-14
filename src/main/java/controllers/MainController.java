package controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import responses.SuccessResponse;

@Controller
@ResponseBody
@RequestMapping (produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController
{
    @GetMapping(value = {"/"})
    public SuccessResponse returnDefaultResponse(String value)
    {
        return new SuccessResponse("yes, it works");
    }
}
