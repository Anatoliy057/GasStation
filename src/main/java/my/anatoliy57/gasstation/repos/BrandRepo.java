package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Brand;
import my.anatoliy57.gasstation.repos.iml.Repository;

import java.util.List;

public interface BrandRepo extends Repository<Long, Brand> {

    boolean existByNameAndStationId(String name, long stationId);

    Brand findByNameAndStationId(String name, long stationId);

    List<Brand> findAllByStationId(long stationId);
}
