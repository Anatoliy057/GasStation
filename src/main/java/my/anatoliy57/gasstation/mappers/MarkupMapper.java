package my.anatoliy57.gasstation.mappers;

import my.anatoliy57.gasstation.domain.dto.MarkupDto;
import my.anatoliy57.gasstation.domain.entity.Markup;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface MarkupMapper {

    Markup createFromDto(MarkupDto dto);

    @Mappings({
            @Mapping(target = "gasStationId", source = "gasStationId"),
    })
    Markup createFromDto(Long gasStationId, MarkupDto dto);

    void updateFromDto(MarkupDto dto, @MappingTarget Markup markup);

    MarkupDto toDto(Markup brand);

    List<MarkupDto> toDto(List<Markup> markups);
}
