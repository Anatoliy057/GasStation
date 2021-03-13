package my.anatoliy57.gasstation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class Markup {

    private Long id;
    private Integer percent;
    private Integer startTime;
    private Integer endTime;

    private GasolineBrand gasolineBrand;
    private GasStation gasStation;
}
