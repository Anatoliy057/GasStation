package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.FluxDensityPeriod;
import my.anatoliy57.gasstation.repos.iml.Repository;

import java.util.List;

public interface FluxDensityPeriodRepo extends Repository<Long, FluxDensityPeriod> {

    boolean existCurrentPeriodByGasStationId(Long currentTime, Long gasStationId);

    FluxDensityPeriod findCurrentPeriodByGasStationId(Long currentTime, Long gasStationId);

    FluxDensityPeriod findById(Long id);

    List<FluxDensityPeriod> findAll();

    List<FluxDensityPeriod> findAllByGasStationId(Long gasStationId);

    FluxDensityPeriod save(FluxDensityPeriod order);

    void deleteById(Long id);
}
