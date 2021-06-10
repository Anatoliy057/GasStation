package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepo extends CrudRepository<Station, Long> {

    Station findByName(String name);

    boolean existsByName(String name);
}
