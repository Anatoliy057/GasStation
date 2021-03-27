package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.Order;
import my.anatoliy57.gasstation.repos.OrderRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepoIml extends ArrayRepo<Order> implements OrderRepo {

    @Override
    public List<Order> findCompletedByStationId(long currentTime, long stationId) {
        return super.findAllByPredicate(order -> order.getStation().getId().equals(stationId)
                && order.getOrderTime() + order.getDuration() < currentTime);
    }

    @Override
    public List<Order> findAllByStationId(long stationId) {
        return super.findAllByPredicate(order -> order.getStation().getId().equals(stationId));
    }

    @Override
    public Order save(Order order) {
        Order savedOrder = super.save(order);

        savedOrder.getStation().getOrders().add(savedOrder);
        savedOrder.getBrand().getOrders().add(savedOrder);

        return savedOrder;
    }

    @Override
    public void deleteById(Long id) {
        Order order = removeById(id);

        order.getBrand().getOrders().remove(order);
        //order.getStation().get
    }
}
