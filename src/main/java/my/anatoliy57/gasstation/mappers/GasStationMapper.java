package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.GasStationDto;
import my.anatoliy57.gasstation.domain.entity.GasStation;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface GasStationMapper {

    GasStation createFromDto(GasStationDto dto);

    void updateFromDto(GasStationDto dto, @MappingTarget GasStation station);

    GasStationDto toDto(GasStation brand);

    List<GasStationDto> toDto(List<GasStation> stations);
}
