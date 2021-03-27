package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.Period;
import my.anatoliy57.gasstation.repos.PeriodRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeriodRepoIml extends ArrayRepo<Period> implements PeriodRepo {

    @Override
    public boolean existCurrentByStationId(long currentTime, long stationId) {
        return findCurrentByStationId(currentTime, stationId) != null;
    }

    @Override
    public Period findCurrentByStationId(long currentTime, long stationId) {
        return findByPredicate(period -> period.getStartTime() < currentTime && period.getEndTime() > currentTime);
    }

    @Override
    public List<Period> findAllByStationId(long stationId) {
        return findAllByPredicate(period -> period.getStation().getId().equals(stationId));
    }

    @Override
    public boolean existPeriodInByStationId(long startTime, long endTime, long stationId) {
        return findByPredicate(
                period -> period.getStation().getId().equals(stationId)
                        && period.getStartTime() <= startTime
                        && period.getEndTime() >= endTime
        ) != null;
    }

    @Override
    public Period save(Period period) {
        Period savedPeriod = super.save(period);

        savedPeriod.getStation().getPeriods().add(savedPeriod);

        return savedPeriod;
    }
}
