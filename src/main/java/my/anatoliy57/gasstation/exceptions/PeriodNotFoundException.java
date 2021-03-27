package my.anatoliy57.gasstation.exceptions;

import lombok.Getter;

@Getter
public class PeriodNotFoundException extends GasStationException {

    private final long id;

    public PeriodNotFoundException(long id) {
        super(String.format("Period with id: %d not found", id));
        this.id = id;
    }
}
