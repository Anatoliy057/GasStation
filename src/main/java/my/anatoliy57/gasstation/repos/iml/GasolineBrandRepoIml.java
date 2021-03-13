package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.GasolineBrand;
import my.anatoliy57.gasstation.repos.GasolineBrandRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class GasolineBrandRepoIml extends ArrayRepo<GasolineBrand> implements GasolineBrandRepo {

    @Override
    public boolean existByBrandAndGasStationId(String brand, Long gasStationId) {
        return findByBrandAndGasStationId(brand, gasStationId) != null;
    }

    @Override
    public GasolineBrand findByBrandAndGasStationId(String brand, Long gasStationId) {
        return findByPredicate(gasolineBrand -> gasolineBrand.getBrand().equals(brand)
                && gasolineBrand.getGasStation().getId().equals(gasStationId));
    }
}
