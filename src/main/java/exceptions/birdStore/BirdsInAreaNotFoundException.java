package exceptions.birdStore;

import lombok.Getter;

public class BirdsInAreaNotFoundException extends Exception
{
    public static final String MESSAGE = "No birds were not found in area";
    @Getter private String area;

    public BirdsInAreaNotFoundException(String area)
    {
        super(MESSAGE);
        this.area = area;
    }
}
