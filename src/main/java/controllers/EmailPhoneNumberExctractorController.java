package controllers;

import components.emailPhoneExtractor.EmailPhoneNumberExtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import responses.SuccessResponse;

@RestController
@RequestMapping(value = "getEmailsAndPhoneNumbers")
public class EmailPhoneNumberExctractorController
{
    @GetMapping
    public SuccessResponse extractEmailsAndPhoneNumbers(@RequestParam("from") String from)
    {
        return new SuccessResponse(EmailPhoneNumberExtractor.extract(from));
    }
}
