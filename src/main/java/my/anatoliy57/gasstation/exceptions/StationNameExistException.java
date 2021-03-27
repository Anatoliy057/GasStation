package my.anatoliy57.gasstation.exceptions;

import lombok.Getter;

@Getter
public class StationNameExistException extends GasStationException {

    public final String name;

    public StationNameExistException(String name) {
        super(String.format("Station with name %s already exists", name));
        this.name = name;
    }
}
