package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.FluxDensityPeriodDto;
import my.anatoliy57.gasstation.domain.entity.FluxDensityPeriod;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface FluxDensityPeriodMapper {

    FluxDensityPeriod createFromDto(FluxDensityPeriodDto dto);

    @Mappings({
            @Mapping(target = "gasStationId", source = "gasStationId"),
    })
    FluxDensityPeriod createFromDto(Long gasStationId, FluxDensityPeriodDto dto);

    void updateFromDto(FluxDensityPeriodDto dto, @MappingTarget FluxDensityPeriod period);

    FluxDensityPeriodDto toDto(FluxDensityPeriod brand);

    List<FluxDensityPeriodDto> toDto(List<FluxDensityPeriod> periods);
}
