package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.Brand;
import my.anatoliy57.gasstation.repos.BrandRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandRepoIml extends ArrayRepo<Brand> implements BrandRepo {

    @Override
    public boolean existByNameAndStationId(String name, long stationId) {
        return findByNameAndStationId(name, stationId) != null;
    }

    @Override
    public Brand findByNameAndStationId(String name, long stationId) {
        return findByPredicate(gasolineBrand -> gasolineBrand.getBrand().equals(name)
                && gasolineBrand.getStation().getId().equals(stationId));
    }

    @Override
    public List<Brand> findAllByStationId(long stationId) {
        return findAllByPredicate(brand -> brand.getStation().getId().equals(stationId));
    }

    @Override
    public Brand save(Brand brand) {
        Brand savedBrand = super.save(brand);

        savedBrand.getStation().getBrands().add(savedBrand);

        return savedBrand;
    }
}
