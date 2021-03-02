package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.FluxDensityPeriod;
import my.anatoliy57.gasstation.repos.FluxDensityPeriodRepo;
import org.springframework.stereotype.Component;

@Component
public class FluxDensityPeriodRepoIml extends ArrayRepo<FluxDensityPeriod> implements FluxDensityPeriodRepo {

    @Override
    public boolean existCurrentPeriod(Long currentTime) {
        return findCurrentPeriod(currentTime) != null;
    }

    @Override
    public FluxDensityPeriod findCurrentPeriod(Long currentTime) {
        return super.findByPredicate((period -> period.getStartTime() < currentTime && period.getEndTime() > currentTime));
    }
}
