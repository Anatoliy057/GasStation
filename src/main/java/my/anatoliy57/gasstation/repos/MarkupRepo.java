package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Markup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkupRepo extends CrudRepository<Markup, Long> {

    @Query(value = "SELECT * FROM markups m WHERE m.BRAND_ID = ?2 AND m.START_TIME <= ?1 AND ?1 < END_TIME AND m.STATION_ID = ?3",
            nativeQuery = true)
    Markup findCurrentByBrandIdAndStationId(long currentTime, long brandId, long stationId);

    @Query(value = "SELECT CASE WHEN COUNT(m) >= 1 THEN true ELSE false END FROM markups m WHERE m.brand.id = ?2 AND m.station.id = ?3 AND  m.startTime <= ?1 AND ?1 < m.endTime",
            nativeQuery = false)
    boolean existCurrentByBrandIdAndStationId(long currentTime, long brandId, long stationId);

    @Query(value = "SELECT CASE WHEN COUNT(m) >= 1 THEN true ELSE false END FROM markups m WHERE m.brand.id = ?3 AND m.station.id = ?4 AND  m.startTime <= ?1 AND ?2 >= m.endTime",
            nativeQuery = false)
    boolean existMarkupInByBrandIdAndStationId(long startTime, long endTime, long brandId, long stationId);

    List<Markup> findAllByStationId(long stationId);
}
