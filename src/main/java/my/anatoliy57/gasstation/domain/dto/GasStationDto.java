package my.anatoliy57.gasstation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import my.anatoliy57.gasstation.model.enums.TimeSpanType;

@Data
@Builder
@ToString
@AllArgsConstructor
public class GasStationDto {

    private Long id;
    private String name;
    private Integer countColumns;
    private Integer maxQueue;
    private Integer minVolumeGasoline;
    private Integer maxVolumeGasoline;
    private Integer minTimeService;
    private Integer maxTimeService;
    private TimeSpanType timeSpanType;
    private Integer countTimes;
    private Integer percentChurn;
}

