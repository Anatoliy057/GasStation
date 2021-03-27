package my.anatoliy57.gasstation.exceptions;

public class IntersectionMarkupsException extends IntersectionException {

    public IntersectionMarkupsException(long start, long end) {
        super(String.format("The markup that starts at %d and ends at %d intersects with others", start, end), start, end);
    }
}
