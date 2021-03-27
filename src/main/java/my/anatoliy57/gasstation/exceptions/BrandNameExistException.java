package my.anatoliy57.gasstation.exceptions;

import lombok.Getter;

@Getter
public class BrandNameExistException extends GasStationException {

    public final String name;
    private final long stationId;

    public BrandNameExistException(String name, long stationId) {
        super(String.format("Brand with name %s already exists in station %d", name, stationId));
        this.name = name;
        this.stationId = stationId;
    }
}
