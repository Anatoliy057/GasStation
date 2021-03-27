package my.anatoliy57.gasstation.exceptions;

public class IntersectionPeriodsException extends IntersectionException {

    public IntersectionPeriodsException(long start, long end) {
        super(String.format("The period that starts at %d and ends at %d intersects with others", start, end), start, end);
    }
}
