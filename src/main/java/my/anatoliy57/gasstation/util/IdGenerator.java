package my.anatoliy57.gasstation.util;

public class IdGenerator {

    private long id = -1;

    public synchronized long generate() {
        id++;
        return id;
    }
}
