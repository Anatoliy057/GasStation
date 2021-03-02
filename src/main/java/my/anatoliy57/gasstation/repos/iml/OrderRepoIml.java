package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.Order;
import my.anatoliy57.gasstation.repos.OrderRepo;
import org.springframework.stereotype.Component;

@Component
public class OrderRepoIml extends ArrayRepo<Order> implements OrderRepo {

    @Override
    public Order findCompletedOrders(Long currentTime) {
        return super.findByPredicate(order -> order.getOrderTime() + order.getDuration() < currentTime);
    }
}
