package components.birdStore;

import exceptions.birdStore.BirdAlreadyExistException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

public class BirdStoreTest
{
    @Test
    public void testAddBird() throws BirdAlreadyExistException, NoSuchFieldException, IllegalAccessException
    {
        BirdStore store = BirdStore.getInstance();
        store.addBird(new Bird("A", "A"));
        Assert.assertEquals(1, getSizeOfStore(store));
    }

    private static int getSizeOfStore(BirdStore store) throws NoSuchFieldException, IllegalAccessException
    {
        Field f = store.getClass().getDeclaredField("mapNameToBird");
        f.setAccessible(true);
        return ((Map<String, Bird>) f.get(store)).size();
    }
}
