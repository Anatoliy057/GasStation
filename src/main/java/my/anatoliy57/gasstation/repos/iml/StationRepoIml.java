package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.Station;
import my.anatoliy57.gasstation.repos.StationRepo;
import org.springframework.stereotype.Repository;


public class StationRepoIml extends ArrayRepo<Station>  {

    public Station findByName(String name) {
        return super.findByPredicate(station -> station.getName().equals(name));
    }

    public boolean existByName(String name) {
        return findByName(name) != null;
    }
}
