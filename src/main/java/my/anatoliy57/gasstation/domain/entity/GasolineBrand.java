package my.anatoliy57.gasstation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class GasolineBrand {

    private Long id;
    private String brand;
    private Integer cost;

    private GasStation gasStation;
}
