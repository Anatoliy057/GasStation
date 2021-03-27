package my.anatoliy57.gasstation.exceptions;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends GasStationException {

    private final long id;

    public OrderNotFoundException(long id) {
        super(String.format("Order with id: %d not found", id));
        this.id = id;
    }
}
