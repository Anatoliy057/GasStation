package my.anatoliy57.gasstation.exceptions;

public class GasStationException extends Exception {

    public GasStationException() {
    }

    public GasStationException(String message) {
        super(message);
    }

    public GasStationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GasStationException(Throwable cause) {
        super(cause);
    }
}
