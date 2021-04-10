package my.anatoliy57.gasstation.domain.entity;

import lombok.*;
import my.anatoliy57.gasstation.enums.TimeSpanType;

import java.util.ArrayList;
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

    private List<Order> orders = new ArrayList<>();
    private List<Markup> markups = new ArrayList<>();
    private List<Brand> brands = new ArrayList<>();
    private List<Period> periods = new ArrayList<>();
}
