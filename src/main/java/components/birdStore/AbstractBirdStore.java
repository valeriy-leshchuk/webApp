package components.birdStore;

import exceptions.birdStore.BirdAlreadyExistException;
import exceptions.birdStore.BirdNotFoundException;
import exceptions.birdStore.BirdsInAreaNotFoundException;

import java.util.List;

public abstract class AbstractBirdStore
{
    public abstract void addBird(Bird bird) throws BirdAlreadyExistException;

    public abstract boolean removeBird(String name);

    public abstract Bird searchByName(String nameToSearch) throws BirdNotFoundException;

    public abstract List searchByLivingArea(String livingAreaToFind) throws BirdsInAreaNotFoundException;
}
