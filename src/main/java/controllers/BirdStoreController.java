package controllers;

import components.birdStore.Bird;
import components.birdStore.BirdStore;
import exceptions.birdStore.BirdAlreadyExistException;
import exceptions.birdStore.BirdNotFoundException;
import exceptions.birdStore.BirdsInAreaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import responses.SuccessResponse;

import java.util.List;

@RestController
@RequestMapping(value = {"/birdStore"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class BirdStoreController
{
    private static final Logger logger = LoggerFactory.getLogger(BirdStoreController.class);

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public SuccessResponse addBird(String name, String area) throws BirdAlreadyExistException
    {
        logger.debug("Adding bird");
        Bird bird = new Bird(name, area);
        BirdStore.getInstance().addBird(bird);
        return new SuccessResponse(bird);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public SuccessResponse removeBird(String name) throws BirdNotFoundException
    {
        logger.debug("Removing bird");
        if (BirdStore.getInstance().removeBird(name))
        {
            return new SuccessResponse("Bird with name '" + name + "' was removed");
        }
        else
        {
            throw new BirdNotFoundException(name);
        }
    }

    @RequestMapping(value = "/searchByName", method = RequestMethod.GET)
    public SuccessResponse searchByName(String name) throws BirdNotFoundException
    {
        logger.debug("Searching for a bird by name");
        Bird bird = BirdStore.getInstance().searchByName(name);
        return new SuccessResponse(bird);
    }

    @RequestMapping(value = "/searchByLivingArea", method = RequestMethod.GET)
    public SuccessResponse searchByLivingArea(String area) throws BirdsInAreaNotFoundException
    {
        logger.debug("Searching for birds by living area");
        List<Bird> birds = BirdStore.getInstance().searchByLivingArea(area);
        return new SuccessResponse(birds);
    }
}
