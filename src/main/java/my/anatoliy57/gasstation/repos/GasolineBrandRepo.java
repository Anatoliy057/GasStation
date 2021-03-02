package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.GasolineBrand;
import my.anatoliy57.gasstation.domain.entity.Markup;

import java.util.List;

public interface GasolineBrandRepo {

    boolean existByBrand(String brand);

    GasolineBrand findById(Long id);

    List<GasolineBrand> findAll();

    GasolineBrand save(GasolineBrand order);

    void deleteById(Long id);
}
