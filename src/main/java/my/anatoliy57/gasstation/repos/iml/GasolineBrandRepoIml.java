package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.GasolineBrand;
import my.anatoliy57.gasstation.repos.GasolineBrandRepo;
import org.springframework.stereotype.Component;

@Component
public class GasolineBrandRepoIml extends ArrayRepo<GasolineBrand> implements GasolineBrandRepo {

    @Override
    public boolean existByBrand(String brand) {
        return super.findByPredicate(gasolineBrand -> gasolineBrand.getBrand().equals(brand)) != null;
    }
}
