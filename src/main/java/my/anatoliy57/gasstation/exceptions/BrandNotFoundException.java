package my.anatoliy57.gasstation.exceptions;

import lombok.Getter;

@Getter
public class BrandNotFoundException extends GasStationException {

    private long id;
    private String name;

    public BrandNotFoundException(long id) {
        super(String.format("Brand with id: %d not found", id));
        this.id = id;
    }

    public BrandNotFoundException(String name) {
        super(String.format("Brand with name: %s not found", name));
        this.name = name;
    }
}
