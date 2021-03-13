package my.anatoliy57.gasstation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class FluxDensityPeriod {

    private Long id;
    private Integer density;
    private Long startTime;
    private Long endTime;

    private GasStation gasStation;
}
