package my.anatoliy57.gasstation.model;

import my.anatoliy57.gasstation.domain.dto.StationDto;

import java.util.List;

public class GasStation {

    private final StationDto station;

    private long maxTime;
    private int step;

    private List<FillMachine> machines;
    private long currentTime;

    public GasStation(StationDto station) {
        this.station = station;

        maxTime = station.getTimeSpanType().getMaxTime();
        step = station.getStep();

        currentTime = 0;
    }

    public void run() {
        if (currentTime > maxTime) {
            return;
        }

        for (int i = 0; i < step; i++) {


            currentTime++;
        }
    }
}
