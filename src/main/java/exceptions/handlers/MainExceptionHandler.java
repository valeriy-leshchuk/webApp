package exceptions.handlers;

import controllers.BirdStoreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import responses.ErrorResponse;

@ControllerAdvice
public class MainExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(BirdStoreController.class);

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
