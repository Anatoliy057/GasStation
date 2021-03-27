package my.anatoliy57.gasstation.exceptions;

import lombok.Getter;

@Getter
public class BrandNotFoundException extends GasStationException {

    private final long id;

    public BrandNotFoundException(long id) {
        super(String.format("Brand with id: %d not found", id));
        this.id = id;
    }
}
