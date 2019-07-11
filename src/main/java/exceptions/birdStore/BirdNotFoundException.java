package exceptions.birdStore;

import lombok.Getter;

public class BirdNotFoundException extends Exception
{
    public static final String MESSAGE = "Bird was not found";
    @Getter private String birdName;

    public BirdNotFoundException(String birdName)
    {
        super(MESSAGE);
        this.birdName = birdName;
    }
}
