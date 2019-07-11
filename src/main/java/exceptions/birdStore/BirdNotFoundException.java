package exceptions.birdStore;

import lombok.Getter;

public class BirdNotFoundException extends BirdStoreBaseException
{
    @Getter private String birdName;

    public BirdNotFoundException(String birdName)
    {
        this.birdName = birdName;
    }
}
