package controllers;

import components.birdStore.Bird;
import components.birdStore.BirdStore;
import exceptions.birdStore.BirdAlreadyExistException;
import exceptions.birdStore.BirdNotFoundException;
import exceptions.birdStore.BirdsInAreaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import responses.SuccessResponse;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = {"/birdStore"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class BirdStoreController
{
    private static final Logger logger = LoggerFactory.getLogger(BirdStoreController.class);

    private static final String BIRD_NAME_PATTERN = "([a-zA-Z]|\\+){1,100}";
    private static final String BIRD_AREA_PATTERN = "([a-zA-Z]|\\+){1,50}";

    @PostMapping (value = "add")
    public SuccessResponse addBird(
        @RequestParam("name") @Pattern(regexp = BIRD_NAME_PATTERN) String name,
        @RequestParam("area") String area)
        throws BirdAlreadyExistException
    {
        logger.debug("Adding bird");
        Bird bird = new Bird(name, area);
        BirdStore.getInstance().addBird(bird);
        return new SuccessResponse(bird);
    }

    @DeleteMapping(value = "remove")
    public SuccessResponse removeBird(@RequestParam("name") @Pattern(regexp = BIRD_NAME_PATTERN) String name) throws BirdNotFoundException
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

    @GetMapping(value = "searchByName")
    public SuccessResponse searchByName(@RequestParam("name") @Pattern(regexp = BIRD_NAME_PATTERN) String name) throws BirdNotFoundException
    {
        logger.debug("Searching for a bird by name");
        Bird bird = BirdStore.getInstance().searchByName(name);
        return new SuccessResponse(bird);
    }

    @GetMapping(value = "searchByLivingArea")
    public SuccessResponse searchByLivingArea(@RequestParam("area") @Pattern(regexp = BIRD_AREA_PATTERN) String area) throws BirdsInAreaNotFoundException
    {
        logger.debug("Searching for birds by living area");
        List<Bird> birds = BirdStore.getInstance().searchByLivingArea(area);
        return new SuccessResponse(birds);
    }
}
