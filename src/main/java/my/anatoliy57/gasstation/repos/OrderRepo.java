package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {

    @Query(value = "SELECT * FROM ORDERS WHERE STATION_ID = ?2 AND DURATION + ORDER_TIME < ?1", nativeQuery = true)
    List<Order> findCompletedByStationId(long currentTime, long stationId);

    List<Order> findAllByStation_Id(long stationId);
}
