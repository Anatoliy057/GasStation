package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Period;
import my.anatoliy57.gasstation.repos.iml.Repository;

import java.util.List;

public interface PeriodRepo extends Repository<Long, Period> {

    boolean existCurrentByStationId(long currentTime, long stationId);

    Period findCurrentByStationId(long currentTime, long stationId);

    List<Period> findAllByStationId(long stationId);

    boolean existPeriodInByStationId(long startTime, long endTime, long stationId);
}
