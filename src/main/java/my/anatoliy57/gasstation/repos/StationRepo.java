package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Station;
import my.anatoliy57.gasstation.repos.iml.Repository;

public interface StationRepo extends Repository<Long, Station> {

    Station findByName(String name);

    boolean existByName(String name);
}
