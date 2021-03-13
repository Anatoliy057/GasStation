package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.GasStation;
import my.anatoliy57.gasstation.repos.GasStationRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class GasStationRepoIml extends ArrayRepo<GasStation> implements GasStationRepo {

    @Override
    public GasStation findByName(String name) {
        return super.findByPredicate(station -> station.getName().equals(name));
    }
}
