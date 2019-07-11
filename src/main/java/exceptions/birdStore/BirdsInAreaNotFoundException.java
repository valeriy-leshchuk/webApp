package exceptions.birdStore;

import lombok.Getter;

public class BirdsInAreaNotFoundException extends BirdStoreBaseException
{
    @Getter private String area;

    public BirdsInAreaNotFoundException(String area)
    {
        this.area = area;
    }
}
