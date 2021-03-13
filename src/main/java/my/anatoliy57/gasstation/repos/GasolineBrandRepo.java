package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.GasolineBrand;
import my.anatoliy57.gasstation.repos.iml.Repository;

public interface GasolineBrandRepo extends Repository<Long, GasolineBrand> {

    boolean existByBrandAndGasStationId(String brand, Long gasStationId);

    GasolineBrand findByBrandAndGasStationId(String brand, Long gasStationId);
}
