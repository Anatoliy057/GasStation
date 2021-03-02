package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Order;

import java.util.List;

public interface OrderRepo {

    Order findCompletedOrders(Long currentTime);

    Order findById(Long id);

    List<Order> findAll();

    Order save(Order order);

    void deleteById(Long id);
}
