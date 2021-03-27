package my.anatoliy57.gasstation.domain.dto;

import lombok.*;
import my.anatoliy57.gasstation.domain.entity.Station;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PeriodDto {

    private Long id;
    private Long stationId;
    private Integer density;
    private Long startTime;
    private Long endTime;
}
