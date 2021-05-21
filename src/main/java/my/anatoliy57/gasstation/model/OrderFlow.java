package my.anatoliy57.gasstation.model;

import my.anatoliy57.gasstation.domain.dto.MarkupDto;
import my.anatoliy57.gasstation.domain.dto.OrderDto;
import my.anatoliy57.gasstation.domain.dto.PeriodDto;
import my.anatoliy57.gasstation.domain.dto.StationDto;
import my.anatoliy57.gasstation.enums.OrderStatus;
import my.anatoliy57.gasstation.exceptions.OrderNotFoundException;
import my.anatoliy57.gasstation.services.MarkupService;
import my.anatoliy57.gasstation.services.OrderService;
import my.anatoliy57.gasstation.services.PeriodService;

import java.util.Optional;
import java.util.Random;

public class OrderFlow {

    private final OrderService orderService;
    private final MarkupService markupService;
    private final PeriodService periodService;
    private final OrderGenerator generator;

    private int defaultDensity;

    private int wait;
    private final Random random;

    public OrderFlow(StationDto station, OrderService orderService, MarkupService markupService, PeriodService periodService, OrderGenerator generator) {
        this.orderService = orderService;
        this.markupService = markupService;
        this.periodService = periodService;
        this.generator = generator;

        defaultDensity = station.getDefaultDensity();

        wait = 0;
        random = new Random();
    }

    public Optional<OrderDto> getOrder(long currentTime) throws OrderNotFoundException {
        if (wait > 0) {
            wait--;
            return Optional.empty();
        }

        OrderDto order = generator.generate(currentTime);

        Optional<MarkupDto> currentMarkupOpt =  markupService.getCurrent(currentTime, order.getBrandId(), order.getStationId());
        int percent = currentMarkupOpt.map(MarkupDto::getPercent).orElse(0);
        int chance = Math.max(100 - percent * 3, 0);
        if (random.nextInt(100) < chance) {
            order.setStatus(OrderStatus.REJECTED);
            orderService.update(order);
        }

        int density;
        PeriodDto currentPeriod = periodService.getCurrent(currentTime, order.getStationId());
        if (currentPeriod == null) {
            density = defaultDensity;
        } else {
            density = currentPeriod.getDensity();
        }

        wait = density;

        return Optional.of(order);
    }
}
