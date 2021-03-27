package my.anatoliy57.gasstation.model;

import my.anatoliy57.gasstation.domain.dto.OrderDto;
import my.anatoliy57.gasstation.domain.entity.Station;
import my.anatoliy57.gasstation.enums.OrderStatus;
import my.anatoliy57.gasstation.exceptions.OrderNotFoundException;
import my.anatoliy57.gasstation.services.OrderService;
import org.springframework.context.annotation.Bean;

import java.util.LinkedList;
import java.util.Queue;

public class FillMachine {

    private final OrderService orderService;

    private final int maxQueue;
    private final int minVolume;
    private final int maxVolume;
    private final int minTime;
    private final int maxTime;

    private final Queue<OrderDto> orders;
    private OrderDto currentOrder;
    private int wait;

    public FillMachine(Station station, OrderService orderService) {
        this.orderService = orderService;

        this.maxQueue = station.getMaxQueue();
        this.minVolume = station.getMinVolumeGasoline();
        this.maxVolume = station.getMaxVolumeGasoline();
        this.minTime = station.getMinTimeService();
        this.maxTime = station.getMaxTimeService();

        orders = new LinkedList<>();
        wait = 0;
        currentOrder = null;
    }

    public boolean addOrderIfPossible(OrderDto order) throws OrderNotFoundException {
        if (orders.size() > maxQueue) {
            order.setStatus(OrderStatus.REJECTED);
            orderService.update(order);
            return false;
        }
        orders.add(order);
        return true;
    }

    public void service() throws OrderNotFoundException {
        if (wait > 0) {
            wait--;
            currentOrder.setStatus(OrderStatus.COMPLETED);
            orderService.update(currentOrder);
            return;
        }

        currentOrder = orders.poll();
        if (currentOrder != null) {
            wait = (currentOrder.getVolume() - minVolume) * (maxTime - minTime) / (maxVolume - minVolume) + minTime;
        }
    }
}
