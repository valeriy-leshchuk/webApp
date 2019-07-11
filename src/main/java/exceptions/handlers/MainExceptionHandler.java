package exceptions.handlers;

import controllers.BirdStoreController;
import exceptions.birdStore.BirdAlreadyExistException;
import exceptions.birdStore.BirdNotFoundException;
import exceptions.birdStore.BirdsInAreaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MainExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(BirdStoreController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String processException(Exception ex)
    {
        String error = "Okay, Houston, we've had a problem here";
        logger.error(error, ex);
        return error;
    }
}
