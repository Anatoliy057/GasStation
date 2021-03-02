package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.FluxDensityPeriod;
import my.anatoliy57.gasstation.repos.iml.Repository;

import java.util.List;

public interface FluxDensityPeriodRepo extends Repository<Long, FluxDensityPeriod> {

    boolean existCurrentPeriod(Long currentTime);

    FluxDensityPeriod findCurrentPeriod(Long currentTime);

    FluxDensityPeriod findById(Long id);

    List<FluxDensityPeriod> findAll();

    FluxDensityPeriod save(FluxDensityPeriod order);

    void deleteById(Long id);
}
