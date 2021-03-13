package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.FluxDensityPeriodDto;
import my.anatoliy57.gasstation.domain.entity.FluxDensityPeriod;
import my.anatoliy57.gasstation.domain.entity.GasStation;
import my.anatoliy57.gasstation.repos.GasStationRepo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface FluxDensityPeriodMapper {

    @Mappings({
            @Mapping(target = "gasStation",  expression = "java( repo.findById(dto.getGasStationId()) )"),
            @Mapping(target = "")
    })
    FluxDensityPeriod createFromDto(FluxDensityPeriodDto dto, GasStationRepo repo);

    @Mappings({
            @Mapping(target = "gasStationId", source = "gasStationId"),
    })
    FluxDensityPeriod createFromDto(Long gasStationId, FluxDensityPeriodDto dto);

    void updateFromDto(FluxDensityPeriodDto dto, @MappingTarget FluxDensityPeriod period);

    FluxDensityPeriodDto toDto(FluxDensityPeriod brand);

    List<FluxDensityPeriodDto> toDto(List<FluxDensityPeriod> periods);
}
