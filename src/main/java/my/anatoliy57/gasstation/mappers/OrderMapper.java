package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.OrderDto;
import my.anatoliy57.gasstation.domain.entity.Order;
import my.anatoliy57.gasstation.repos.BrandRepo;
import my.anatoliy57.gasstation.repos.StationRepo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "station", expression = "java(stationRepo.findById(dto.getStationId()))"),
            @Mapping(target = "brand", expression = "java(brandRepo.findById(dto.getBrandId()))"),
    })
    Order createFromDto(OrderDto dto, StationRepo stationRepo, BrandRepo brandRepo);

    @Mappings({
            @Mapping(target = "brand", ignore = true),
            @Mapping(target = "station", ignore = true),
    })
    void updateFromDto(OrderDto dto, @MappingTarget Order order);

    @Mappings({
            @Mapping(target = "brandDto", ignore = true),

            @Mapping(target = "stationId", source = "order.station.id"),
            @Mapping(target = "brandId", source = "order.brand.id"),
    })
    OrderDto toDto(Order order);

    List<OrderDto> toDto(List<Order> orders);

    @Mappings({
            @Mapping(target = "stationId", ignore = true),
            @Mapping(target = "brandId", ignore = true),

            @Mapping(target = "brandDto", expression = "java(brandMapper.toDto(order.getBrand()))"),
    })
    OrderDto toFullDto(Order order, BrandMapper brandMapper);
}
