package my.anatoliy57.gasstation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private Long gasolineBrandId;
    private Long gasStationId;
    private Integer queueId;
    private Integer volume;
    private Long orderTime;
    private Long duration;
}

