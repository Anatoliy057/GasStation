package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.FluxDensityPeriodDto;
import my.anatoliy57.gasstation.domain.dto.GasolineBrandDto;
import my.anatoliy57.gasstation.domain.entity.FluxDensityPeriod;
import my.anatoliy57.gasstation.domain.entity.GasolineBrand;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface GasolineBrandMapper {

    GasolineBrand createFromDto(GasolineBrandDto dto);

    @Mappings({
            @Mapping(target = "gasStationId", source = "gasStationId"),
    })
    GasolineBrand createFromDto(Long gasStationId, GasolineBrandDto dto);

    void updateFromDto(GasolineBrandDto dto, @MappingTarget GasolineBrand brand);

    GasolineBrandDto toDto(GasolineBrand brand);

    List<GasolineBrandDto> toDto(List<GasolineBrand> brands);
}
