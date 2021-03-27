package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.StationDto;
import my.anatoliy57.gasstation.domain.entity.Station;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface StationMapper {

    Station createFromDto(StationDto dto);

    void updateFromDto(StationDto dto, @MappingTarget Station station);

    StationDto toDto(Station station);

    List<StationDto> toDto(List<Station> stations);

    @Mappings({
            @Mapping(target = "orders", expression = "java(orderMapper.toDto(station.getOrders()))"),
            @Mapping(target = "markups", expression = "java(markupMapper.toDto(station.getMarkups()))"),
            @Mapping(target = "brands", expression = "java(brandMapper.toDto(station.getBrands()))"),
            @Mapping(target = "periods", expression = "java(periodMapper.toDto(station.getPeriods()))"),
    })
    StationDto toFullDto(Station station, OrderMapper orderMapper, MarkupMapper markupMapper, BrandMapper brandMapper, PeriodMapper periodMapper);
}
