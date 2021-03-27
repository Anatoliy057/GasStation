package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Markup;
import my.anatoliy57.gasstation.repos.iml.Repository;

import java.util.List;

public interface MarkupRepo extends Repository<Long, Markup> {

    Markup findCurrentByBrandIdAndStationId(long currentTime, long brandId, long stationId);

    boolean existCurrentByBrandIdAndStationId(long currentTime, long brandId, long stationId);

    boolean existMarkupInByBrandIdAndStationId(long startTime, long endTime, long brandId, long stationId);

    List<Markup> findAllByStationId(long stationId);
}
