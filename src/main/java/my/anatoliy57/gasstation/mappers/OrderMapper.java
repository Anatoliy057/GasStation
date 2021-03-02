package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.OrderDto;
import my.anatoliy57.gasstation.domain.entity.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderMapper {

    Order createFromDto(OrderDto dto);

    @Mappings({
            @Mapping(target = "gasStationId", source = "gasStationId"),
    })
    Order createFromDto(Long gasStationId, OrderDto dto);

    void updateFromDto(OrderDto dto, @MappingTarget Order markup);

    OrderDto toDto(Order request);

    List<OrderDto> toDto(List<Order> requests);
}
