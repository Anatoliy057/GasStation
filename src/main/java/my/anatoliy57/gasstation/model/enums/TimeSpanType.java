package my.anatoliy57.gasstation.model.enums;

public enum TimeSpanType {

    WEEK(10080),
    MONTH(312480),
    YEAR(3679200)
    ;

    private final int maxTime;

    TimeSpanType(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMaxTime() {
        return maxTime;
    }
}
