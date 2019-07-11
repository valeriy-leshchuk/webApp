package controllers;

import components.birdStore.Bird;
import components.birdStore.BirdStore;
import exceptions.birdStore.BirdAlreadyExistException;
import exceptions.birdStore.BirdNotFoundException;
import exceptions.birdStore.BirdsInAreaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BirdStoreController
{
    private static final Logger logger = LoggerFactory.getLogger(BirdStoreController.class);

    @RequestMapping(value = {"/birdStore/add"}, method = RequestMethod.POST)
    @ResponseBody
    public String addBird(String name, String area) throws BirdAlreadyExistException
    {
        logger.debug("Adding bird");
        BirdStore.getInstance().addBird(new Bird(name, area));
        return "Bird with name '" + name + "' from area'" + area + "' was successfully added";
    }

    @RequestMapping(value = {"/birdStore/remove"}, method = RequestMethod.DELETE)
    @ResponseBody
    public String removeBird(String name)
    {
        logger.debug("Removing bird");
        return BirdStore.getInstance().removeBird(name) ?
               "Bird with name '" + name + "' was removed" :
               "There is no bird with name '" + name + "'";
    }

    @RequestMapping(value = {"/birdStore/searchByName"}, method = RequestMethod.GET)
    @ResponseBody
    public String searchByName(String name) throws BirdNotFoundException
    {
        logger.debug("Searching for a bird by name");
        return BirdStore.getInstance().searchByName(name).toString();
    }

    @RequestMapping(value = {"/birdStore/searchByLivingArea"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Bird> searchByLivingArea(String area) throws BirdsInAreaNotFoundException
    {
        logger.debug("Searching for birds by living area");
        return BirdStore.getInstance().searchByLivingArea(area);
    }
}
