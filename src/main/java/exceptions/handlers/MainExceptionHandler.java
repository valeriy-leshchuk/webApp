package exceptions.handlers;

import controllers.BirdStoreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import responses.ErrorResponse;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class MainExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(MainExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processException(ConstraintViolationException ex)
    {
        String[] failedValidations = ex.getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList()).
                toArray(new String[ex.getConstraintViolations().size()]);
        logger.error(Arrays.toString(failedValidations), ex);
        return new ErrorResponse(failedValidations);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse processException(Exception ex)
    {
        String error = "Okay, Houston, we've had a problem here";
        logger.error(error, ex);
        ErrorResponse response = new ErrorResponse(error);
        return response;
    }
}
