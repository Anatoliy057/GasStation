package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.FluxDensityPeriod;
import my.anatoliy57.gasstation.repos.FluxDensityPeriodRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FluxDensityPeriodRepoIml extends ArrayRepo<FluxDensityPeriod> implements FluxDensityPeriodRepo {

    @Override
    public boolean existCurrentPeriodByGasStationId(Long currentTime, Long gasStationId) {
        return findCurrentPeriodByGasStationId(currentTime, gasStationId) != null;
    }

    @Override
    public FluxDensityPeriod findCurrentPeriodByGasStationId(Long currentTime, Long gasStationId) {
        return findByPredicate(period -> period.getStartTime() < currentTime && period.getEndTime() > currentTime);
    }

    @Override
    public List<FluxDensityPeriod> findAllByGasStationId(Long gasStationId) {
        return findAllByPredicate(period -> period.getGasStation().getId().equals(gasStationId));
    }
}
