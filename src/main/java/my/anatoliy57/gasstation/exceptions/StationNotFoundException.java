package my.anatoliy57.gasstation.exceptions;

import lombok.Getter;

@Getter
public class StationNotFoundException extends GasStationException {

    private final long id;

    public StationNotFoundException(long id) {
        super(String.format("Gas station with id: %d not found", id));
        this.id = id;
    }
}
