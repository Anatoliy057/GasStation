package my.anatoliy57.gasstation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class MarkupDto {

    private Long id;
    private Long gasolineBrandId;
    private Long gasStationId;
    private Integer percent;
    private Integer startTime;
    private Integer endTime;
}

