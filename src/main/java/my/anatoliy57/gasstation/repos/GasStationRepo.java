package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.GasStation;

import java.util.List;

public interface GasStationRepo {

    GasStation findById(Long id);

    GasStation findByName(String name);

    List<GasStation> findAll();

    GasStation save(GasStation station);

    void deleteById(Long id);
}
