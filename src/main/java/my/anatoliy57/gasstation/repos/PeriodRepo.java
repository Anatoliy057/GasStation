package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Period;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodRepo extends CrudRepository<Period, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(p) >= 1 THEN true ELSE false END FROM PERIODS p WHERE p.startTime < ?1 AND ?1 < p.endTime AND p.station.id = ?2")
    boolean existCurrentByStationId(int currentTime, long stationId);

    @Query(value = "SELECT * FROM PERIODS WHERE START_TIME < ?1 AND ?1 < END_TIME AND STATION_ID = ?2", nativeQuery = true)
    Period findCurrentByStationId(int currentTime, long stationId);

    List<Period> findAllByStation_Id(long stationId);

    @Query(value = "SELECT CASE WHEN COUNT(p) >= 1 THEN true ELSE false END FROM PERIODS p WHERE p.startTime <= ?1 AND ?2 <= p.endTime AND p.station.id = ?3")
    boolean existPeriodInByStationId(int startTime, int endTime, long stationId);
}
