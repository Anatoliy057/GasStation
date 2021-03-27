package my.anatoliy57.gasstation.exceptions;

import lombok.Getter;

@Getter
public class MarkupNotFoundException extends GasStationException {

    private final long id;

    public MarkupNotFoundException(long id) {
        super(String.format("Markup with id: %d not found", id));
        this.id = id;
    }
}
