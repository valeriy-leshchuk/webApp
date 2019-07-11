package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReturnProviderValueController
{
    @RequestMapping(value = {"returnValue"}, method = RequestMethod.GET)
    @ResponseBody
    public String getSomeData(String value)
    {
        return "provided value is " + value;
    }
}
