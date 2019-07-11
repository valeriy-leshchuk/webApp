package exceptions.handlers;

import controllers.BirdStoreController;
import exceptions.birdStore.BirdAlreadyExistException;
import exceptions.birdStore.BirdNotFoundException;
import exceptions.birdStore.BirdStoreBaseException;
import exceptions.birdStore.BirdsInAreaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BirdStoreExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(BirdStoreController.class);

    @ExceptionHandler(BirdAlreadyExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String processException(BirdAlreadyExistException ex)
    {
        String error = "Bird with name '" + ex.getBirdName() + "' already exist in store";
        logger.error(error, ex);
        return error;
    }

    @ExceptionHandler(BirdNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String processException(BirdNotFoundException ex)
    {
        String error = "Bird with name '" + ex.getBirdName() + "' was not found in store";
        logger.error(error, ex);
        return error;
    }

    @ExceptionHandler(BirdsInAreaNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String processException(BirdsInAreaNotFoundException ex)
    {
        String error = "No birds were found in area '" + ex.getArea() + "'";
        logger.error(error, ex);
        return error;
    }

    @ExceptionHandler(BirdStoreBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String processException(BirdStoreBaseException ex)
    {
        String error = "Error happened in bird store";
        logger.error(error, ex);
        return error;
    }
}
