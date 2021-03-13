package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.GasStation;
import my.anatoliy57.gasstation.repos.iml.Repository;

public interface GasStationRepo extends Repository<Long, GasStation> {

    GasStation findByName(String name);
}
