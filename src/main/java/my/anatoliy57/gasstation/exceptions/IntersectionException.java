package my.anatoliy57.gasstation.exceptions;

import lombok.Getter;

@Getter
public class IntersectionException extends GasStationException {

    private final long start;
    private final long end;

    public IntersectionException(String msg, long start, long end) {
        super(msg);
        this.start = start;
        this.end = end;
    }
}
