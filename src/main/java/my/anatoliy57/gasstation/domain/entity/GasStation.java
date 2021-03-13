package my.anatoliy57.gasstation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import my.anatoliy57.gasstation.model.enums.TimeSpanType;

import java.util.Set;

@Data
@Builder
@ToString
@AllArgsConstructor
public class GasStation {

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

    private Set<Order> orders;
    private Set<Markup> markups;
    private Set<GasolineBrand> brands;
    private Set<FluxDensityPeriod> periods;
}
