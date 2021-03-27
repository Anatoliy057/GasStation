package my.anatoliy57.gasstation.domain.entity;

import lombok.*;
import my.anatoliy57.gasstation.enums.TimeSpanType;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Station {

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
    private Integer step;

    private List<Order> orders;
    private List<Markup> markups;
    private List<Brand> brands;
    private List<Period> periods;
}
