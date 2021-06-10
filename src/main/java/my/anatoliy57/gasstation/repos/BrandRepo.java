package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepo extends CrudRepository<Brand, Long> {

    boolean existsByBrandAndStation_Id(String brand, long stationId);

    Optional<Brand> findByBrandAndStation_Id(String brand, long stationId);

    List<Brand> findAllByStation_Id(long stationId);
}
