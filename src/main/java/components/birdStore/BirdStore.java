package components.birdStore;

import exceptions.birdStore.BirdAlreadyExistException;
import exceptions.birdStore.BirdNotFoundException;
import exceptions.birdStore.BirdsInAreaNotFoundException;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class BirdStore extends AbstractBirdStore
{
    @Getter private static final BirdStore instance = new BirdStore();
    private static final Map<String, Bird> mapNameToBird = new HashMap<>();
    private static final Map<String, List<Bird>> mapLivingAreaToBirds = new HashMap<>();

    @Override
    public void addBird(Bird bird) throws BirdAlreadyExistException
    {
        synchronized (bird.getName())
        {
            if (mapNameToBird.containsKey(bird.getName()))
            {
                throw new BirdAlreadyExistException(bird.getName());
            }
            else
            {
                mapNameToBird.put(bird.getName(), bird);

                String livingArea = bird.getLivingArea();
                if (mapLivingAreaToBirds.containsKey(livingArea))
                {
                    mapLivingAreaToBirds.get(livingArea).add(bird);
                }
                else
                {
                    List<Bird> birsInLivingArea = new ArrayList<>();
                    birsInLivingArea.add(bird);
                    mapLivingAreaToBirds.put(livingArea, birsInLivingArea);
                }
            }
        }
    }

    @Override
    public boolean removeBird(String name)
    {
        synchronized (name)
        {
            if (!mapNameToBird.containsKey(name))
            {
                return false;
            }
            else
            {
                Bird bird = mapNameToBird.get(name);
                mapNameToBird.remove(name);
                List<Bird> birdsInLivingArea = mapLivingAreaToBirds.get(bird.getLivingArea());
                birdsInLivingArea.remove(bird);
                if (birdsInLivingArea.isEmpty())
                {
                    mapLivingAreaToBirds.remove(bird.getLivingArea());
                }
                return true;
            }
        }
    }

    @Override
    //Most likely it doesn't make sense to throw an exception if there is no bird with given name.
    public Bird searchByName(String nameToSearch) throws BirdNotFoundException
    {
        Bird bird = mapNameToBird.get(nameToSearch);
        if (bird == null)
        {
            throw new BirdNotFoundException(nameToSearch);
        }
        else
        {
            return bird;
        }
    }

    @Override
    //Most likely it doesn't make sense to throw an exception if there are no birds living in given area.
    public List<Bird> searchByLivingArea(String livingAreaToFind) throws BirdsInAreaNotFoundException
    {
        List<Bird> birds = mapLivingAreaToBirds.get(livingAreaToFind);
        if (birds == null)
        {
            throw new BirdsInAreaNotFoundException(livingAreaToFind);
        }
        return birds;
    }
}
