package exceptions.birdStore;

import lombok.Getter;

public class BirdAlreadyExistException extends BirdStoreBaseException
{
    @Getter private String birdName;

    public BirdAlreadyExistException(String birdName)
    {
        this.birdName = birdName;
    }
}
