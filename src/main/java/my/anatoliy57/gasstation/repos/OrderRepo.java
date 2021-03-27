package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Order;
import my.anatoliy57.gasstation.repos.iml.Repository;

import java.util.List;

public interface OrderRepo extends Repository<Long, Order> {

    List<Order> findCompletedByStationId(long currentTime, long stationId);

    List<Order> findAllByStationId(long stationId);
}
