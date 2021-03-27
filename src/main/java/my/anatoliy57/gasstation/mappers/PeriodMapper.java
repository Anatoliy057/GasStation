package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.PeriodDto;
import my.anatoliy57.gasstation.domain.entity.Period;
import my.anatoliy57.gasstation.repos.StationRepo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PeriodMapper {

    @Mappings({
            @Mapping(target = "station",  expression = "java( stationRepo.findById(dto.getStationId()) )"),
    })
    Period createFromDto(PeriodDto dto, StationRepo stationRepo);

    @Mappings({
            @Mapping(target = "station", ignore = true)
    })
    void updateFromDto(PeriodDto dto, @MappingTarget Period period);

    @Mappings({
            @Mapping(target = "stationId", source = "brand.station.id"),
    })
    PeriodDto toDto(Period brand);

    List<PeriodDto> toDto(List<Period> periods);
}
