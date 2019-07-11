package exceptions.birdStore;

import lombok.Getter;

public class BirdAlreadyExistException extends Exception
{
    public static final String MESSAGE = "Bird already exist in store";
    @Getter private String birdName;

    public BirdAlreadyExistException(String birdName)
    {
        super(MESSAGE);
        this.birdName = birdName;
    }
}
