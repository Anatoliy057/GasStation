package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.MarkupDto;
import my.anatoliy57.gasstation.domain.entity.Markup;
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
public interface MarkupMapper {

    @Mappings({
            @Mapping(target = "station", expression = "java(stationRepo.findById(dto.getStationId()))"),
            @Mapping(target = "brand", expression = "java(brandRepo.findById(dto.getBrandId()))"),
    })
    Markup createFromDto(MarkupDto dto, StationRepo stationRepo, BrandRepo brandRepo);

    @Mappings({
            @Mapping(target = "brand", ignore = true),
            @Mapping(target = "station", ignore = true),
    })
    void updateFromDto(MarkupDto dto, @MappingTarget Markup markup);

    @Mappings({
            @Mapping(target = "brandDto", ignore = true),

            @Mapping(target = "stationId", source = "markup.station.id"),
            @Mapping(target = "brandId", source = "markup.brand.id"),
    })
    MarkupDto toDto(Markup markup);

    List<MarkupDto> toDto(List<Markup> markups);

    @Mappings({
            @Mapping(target = "stationId", ignore = true),
            @Mapping(target = "brandId", ignore = true),

            @Mapping(target = "brandDto", expression = "java(brandMapper.toDto(markup.getBrand()))"),
    })
    MarkupDto toFullDto(Markup markup, BrandMapper brandMapper);
}
