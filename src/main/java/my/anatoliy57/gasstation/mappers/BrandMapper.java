package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.BrandDto;
import my.anatoliy57.gasstation.domain.entity.Brand;
import my.anatoliy57.gasstation.repos.StationRepo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface BrandMapper {

    @Mappings({
            @Mapping(target = "markups", ignore = true),
            @Mapping(target = "orders", ignore = true),

            @Mapping(target = "station",  expression = "java( stationRepo.findById(dto.getStationId()).orElseThrow())"),
    })
    Brand createFromDto(BrandDto dto, StationRepo stationRepo);

    @Mappings({
            @Mapping(target = "station", ignore = true),
            @Mapping(target = "markups", ignore = true),
            @Mapping(target = "orders", ignore = true),
    })
    void updateFromDto(BrandDto dto, @MappingTarget Brand brand);

    @Mappings({
            @Mapping(target = "stationId", source = "brand.station.id"),
    })
    BrandDto toDto(Brand brand);

    List<BrandDto> toDto(List<Brand> brands);

    @Mappings({
            @Mapping(target = "stationId", ignore = true),

            @Mapping(target = "orders", expression = "java(orderMapper.toDto(brand.getOrders()))"),
            @Mapping(target = "markups", expression = "java(markupMapper.toDto(brand.getMarkups()))"),
    })
    BrandDto toFullDto(Brand brand, OrderMapper orderMapper, MarkupMapper markupMapper);
}
