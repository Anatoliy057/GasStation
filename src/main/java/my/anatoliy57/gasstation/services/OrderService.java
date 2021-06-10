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
import java.util.Optional;
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
        Optional<Order> orderOpt = orderRepo.findById(id);

        if (orderOpt.isEmpty()) {
            throw new OrderNotFoundException(id);
        }
        Order order = orderOpt.get();

        orderMapper.updateFromDto(dto, order);

        order = orderRepo.save(order);

        return orderMapper.toDto(order);
    }

    public void remove(long id) {
        orderRepo.deleteById(id);
    }

    public OrderDto get(long id) throws OrderNotFoundException {
        Optional<Order> orderOpt = orderRepo.findById(id);

        if (orderOpt.isEmpty()) {
            throw new OrderNotFoundException(id);
        }
        Order order = orderOpt.get();
        return orderMapper.toDto(order);
    }

    public List<OrderDto> getAllCompleted(long time, long stationId) {
        return orderMapper.toDto(orderRepo.findCompletedByStationId(time, stationId));
    }

    public List<OrderDto> getAllInStation(long stationId) {
        return orderMapper.toDto(orderRepo.findAllByStation_Id(stationId));
    }

    public OrderDto getAsFull(long id) throws OrderNotFoundException {
        Optional<Order> orderOpt = orderRepo.findById(id);

        if (orderOpt.isEmpty()) {
            throw new OrderNotFoundException(id);
        }
        Order order = orderOpt.get();
        return orderMapper.toFullDto(order, brandMapper);
    }

    public List<OrderDto> getAllCompletedAsFull(long time, long stationId) {
        return orderRepo.findCompletedByStationId(time, stationId).stream()
                .map(order -> orderMapper.toFullDto(order, brandMapper))
                .collect(Collectors.toList());
    }

    public List<OrderDto> getAllInStationAsFull(long stationId) {
        return orderRepo.findAllByStation_Id(stationId).stream()
                .map(order -> orderMapper.toFullDto(order, brandMapper))
                .collect(Collectors.toList());
    }
}
