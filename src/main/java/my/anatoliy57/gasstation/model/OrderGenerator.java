package my.anatoliy57.gasstation.model;

import my.anatoliy57.gasstation.domain.dto.BrandDto;
import my.anatoliy57.gasstation.domain.dto.OrderDto;
import my.anatoliy57.gasstation.domain.entity.Station;
import my.anatoliy57.gasstation.enums.OrderStatus;
import my.anatoliy57.gasstation.services.BrandService;
import my.anatoliy57.gasstation.services.OrderService;

import java.util.List;
import java.util.Random;

public class OrderGenerator {

    private final BrandService brandService;
    private final OrderService orderService;

    private final int maxVolume;
    private final int minVolume;

    private final Random random;

    public OrderGenerator(Station station, BrandService brandService, OrderService orderService) {
        this.brandService = brandService;
        this.orderService = orderService;

        this.maxVolume = station.getMaxVolumeGasoline();
        this.minVolume = station.getMinVolumeGasoline();

        random = new Random();
    }

    public OrderDto generate(long currentTime) {
        OrderDto dto = new OrderDto();
        dto.setStatus(OrderStatus.SERVICING);
        dto.setOrderTime(currentTime);
        dto.setDuration(random.nextInt(maxVolume - minVolume) + minVolume);
        List<BrandDto> brands = brandService.getAllInStation(dto.getStationId());
        dto.setBrandId(brands.get(random.nextInt(brands.size())).getId());

        dto = orderService.create(dto);

        return dto;
    }
}
