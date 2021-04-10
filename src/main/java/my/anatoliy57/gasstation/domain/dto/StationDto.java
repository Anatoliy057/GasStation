package my.anatoliy57.gasstation.domain.dto;

import lombok.*;
import my.anatoliy57.gasstation.enums.TimeSpanType;

import java.util.List;
import java.util.Set;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StationDto {

    private Long id;
    private String name;
    private Integer countColumns;
    private Integer maxQueue;
    private Integer minVolumeGasoline;
    private Integer maxVolumeGasoline;
    private Integer minTimeService;
    private Integer maxTimeService;
    private TimeSpanType timeSpanType;
    private Integer defaultDensity;

    private List<OrderDto> orders;
    private List<MarkupDto> markups;
    private List<BrandDto> brands;
    private List<PeriodDto> periods;
}

