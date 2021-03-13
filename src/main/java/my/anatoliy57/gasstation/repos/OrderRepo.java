package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Order;
import my.anatoliy57.gasstation.repos.iml.Repository;

import java.util.List;

public interface OrderRepo extends Repository<Long, Order> {

    Order findCompletedOrdersByGasStationId(Long currentTime, Long gasStationId);

    List<Order> findAllByGasStationId(Long gasStationId);
}
