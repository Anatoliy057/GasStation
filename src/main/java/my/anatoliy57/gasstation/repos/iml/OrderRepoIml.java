package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.Order;
import my.anatoliy57.gasstation.repos.OrderRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepoIml extends ArrayRepo<Order> implements OrderRepo {

    @Override
    public Order findCompletedOrdersByGasStationId(Long currentTime, Long gasStationId) {
        return super.findByPredicate(order -> order.getGasStation().getId().equals(gasStationId)
                && order.getOrderTime() + order.getDuration() < currentTime);
    }

    @Override
    public List<Order> findAllByGasStationId(Long gasStationId) {
        return null;
    }
}
