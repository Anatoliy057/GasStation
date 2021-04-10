package my.anatoliy57.gasstation.services;

import my.anatoliy57.gasstation.domain.dto.OrderDto;
import my.anatoliy57.gasstation.domain.entity.Order;
import my.anatoliy57.gasstation.exceptions.OrderNotFoundException;
import my.anatoliy57.gasstation.mappers.BrandMapper;
import my.anatoliy57.gasstation.mappers.OrderMapper;
import my.anatoliy57.gasstation.repos.BrandRepo;
import my.anatoliy57.gasstation.repos.OrderRepo;
import my.anatoliy57.gasstation.repos.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final BrandMapper brandMapper;

    private final OrderRepo orderRepo;
    private final BrandRepo brandRepo;
    private final StationRepo stationRepo;

    @Autowired
    public OrderService(OrderMapper orderMapper,
                        BrandMapper brandMapper,
                        OrderRepo orderRepo,
                        BrandRepo brandRepo,
                        StationRepo stationRepo) {
        this.orderMapper = orderMapper;
        this.brandMapper = brandMapper;
        this.orderRepo = orderRepo;
        this.brandRepo = brandRepo;
        this.stationRepo = stationRepo;
    }

    public OrderDto create(OrderDto dto) {
        Order order = orderMapper.createFromDto(dto, stationRepo, brandRepo);

        order = orderRepo.save(order);

        return orderMapper.toDto(order);
    }

    public OrderDto update(OrderDto dto) throws OrderNotFoundException {
        long id = dto.getId();
        Order order = orderRepo.findById(id);

        if (order == null) {
            throw new OrderNotFoundException(id);
        }

        orderMapper.updateFromDto(dto, order);

        order = orderRepo.save(order);

        return orderMapper.toDto(order);
    }

    public OrderDto remove(long id) {
        return orderMapper.toDto(orderRepo.removeById(id));
    }

    public OrderDto get(long id) {
        return orderMapper.toDto(orderRepo.findById(id));
    }

    public List<OrderDto> getAllCompleted(long time, long stationId) {
        return orderMapper.toDto(orderRepo.findCompletedByStationId(time, stationId));
    }

    public List<OrderDto> getAllInStation(long stationId) {
        return orderMapper.toDto(orderRepo.findAllByStationId(stationId));
    }

    public OrderDto getAsFull(long id) {
        return orderMapper.toFullDto(orderRepo.findById(id), brandMapper);
    }

    public List<OrderDto> getAllCompletedAsFull(long time, long stationId) {
        return orderRepo.findCompletedByStationId(time, stationId).stream()
                .map(order -> orderMapper.toFullDto(order, brandMapper))
                .collect(Collectors.toList());
    }

    public List<OrderDto> getAllInStationAsFull(long stationId) {
        return orderRepo.findAllByStationId(stationId).stream()
                .map(order -> orderMapper.toFullDto(order, brandMapper))
                .collect(Collectors.toList());
    }
}
