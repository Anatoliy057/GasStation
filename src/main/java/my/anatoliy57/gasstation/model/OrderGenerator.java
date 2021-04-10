package my.anatoliy57.gasstation.model;

import my.anatoliy57.gasstation.domain.dto.BrandDto;
import my.anatoliy57.gasstation.domain.dto.OrderDto;
import my.anatoliy57.gasstation.domain.dto.StationDto;
import my.anatoliy57.gasstation.domain.entity.Station;
import my.anatoliy57.gasstation.enums.OrderStatus;
import my.anatoliy57.gasstation.services.BrandService;
import my.anatoliy57.gasstation.services.OrderService;

import java.util.List;
import java.util.Random;

public class OrderGenerator {

    private final BrandService brandService;
    private final OrderService orderService;

    private final long stationId;
    private final int maxVolume;
    private final int minVolume;
    private final int maxDruation;
    private final int minDuration;

    private final Random random;

    public OrderGenerator(StationDto station, BrandService brandService, OrderService orderService) {
        this.brandService = brandService;
        this.orderService = orderService;

        this.stationId = station.getId();
        this.maxVolume = station.getMaxVolumeGasoline();
        this.minVolume = station.getMinVolumeGasoline();
        this.maxDruation = station.getMaxTimeService();
        this.minDuration = station.getMinTimeService();

        random = new Random();
    }

    public OrderDto generate(long currentTime) {
        OrderDto dto = new OrderDto();
        dto.setStationId(stationId);
        dto.setStatus(OrderStatus.SERVICING);
        dto.setOrderTime(currentTime);
        dto.setVolume(random.nextInt(maxVolume - minVolume) + minVolume);
        dto.setDuration((dto.getVolume() - minVolume) * (maxDruation - minDuration) / (maxVolume - minVolume) + minDuration);
        List<BrandDto> brands = brandService.getAllInStation(dto.getStationId());
        dto.setBrandId(brands.get(random.nextInt(brands.size())).getId());

        dto = orderService.create(dto);

        return dto;
    }
}
