package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController
{
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    @ResponseBody
    public String returnDefaultResponse(String value)
    {
        return "yes, it works";
    }
}
