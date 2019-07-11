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
    private static final Map<String, ReentrantLock> mapNameToLock = new HashMap<>();

    @Override
    public void addBird(Bird bird) throws BirdAlreadyExistException
    {
        synchronized (bird.getName())
        {
            ReentrantLock lock;
            synchronized (mapNameToLock)
            {
                if (!mapNameToLock.containsKey(bird.getName()))
                {
                    lock = new ReentrantLock();
                    mapNameToLock.put(bird.getName(), lock);
                }
                else
                {
                    lock = mapNameToLock.get(bird.getName());
                }
            }

            lock.lock();//if some thread will be trying to remove bird
            {
                //try{Thread.sleep(5 * 1000);}catch (InterruptedException ex){} //to emulate long adding process

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
                        List<Bird> birsInLivingArea = new ArrayList();
                        birsInLivingArea.add(bird);
                        mapLivingAreaToBirds.put(livingArea, birsInLivingArea);
                    }
                }
            }
            mapNameToLock.get(bird.getName()).unlock();
        }
    }

    @Override
    public boolean removeBird(String name)
    {
        if (!mapNameToLock.containsKey(name))
        {
            return false;
        }
        synchronized (name)
        {
            mapNameToLock.get(name).lock();
            {
                Bird bird = mapNameToBird.get(name);
                mapNameToBird.remove(name);
                List<Bird> birdsInLivingArea = mapLivingAreaToBirds.get(bird.getLivingArea());
                birdsInLivingArea.remove(bird);
                if (birdsInLivingArea.isEmpty())
                {
                    mapLivingAreaToBirds.remove(bird.getLivingArea());
                }
            }
            ReentrantLock lock = mapNameToLock.get(name);
            mapNameToLock.remove(lock);
            lock.unlock();
            return true;
        }
    }

    @Override
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
