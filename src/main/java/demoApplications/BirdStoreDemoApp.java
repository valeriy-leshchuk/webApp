package demoApplications;

import components.birdStore.Bird;
import controllers.BirdStoreController;
import exceptions.birdStore.BirdAlreadyExistException;
import components.birdStore.BirdStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BirdStoreDemoApp
{
    private static final Logger logger = LoggerFactory.getLogger(BirdStoreDemoApp.class);

    public static void main(String[] args)
    {
        Bird bird1 = new Bird("name1", "area1");
        Bird bird2 = new Bird("name2", "area2");
        Bird bird3 = new Bird("name3", "area2");
        Bird bird4 = new Bird("name3", "area2");
        Bird bird5 = new Bird("name3", "area2");

        Thread subThread = new Thread(() -> addBirdToStore(bird1));
        subThread.start();
        Thread subThread2 = new Thread(() -> addBirdToStore(bird2));
        subThread2.start();
        Thread subThread3 = new Thread(() -> addBirdToStore(bird3));
        subThread3.start();
        Thread subThread4 = new Thread(() -> addBirdToStore(bird4));
        subThread4.start();
        Thread subThread5 = new Thread(() -> BirdStore.getInstance().removeBird(bird5.getName()));
        subThread5.start();
    }

    private static void addBirdToStore(Bird bird)
    {
        try
        {
            BirdStore.getInstance().addBird(bird);
        }
        catch (BirdAlreadyExistException ex)
        {
            logger.error("Error happened during adding a bird to store", ex);
        }
    }
}
