package my.anatoliy57.gasstation.domain.dto;

import lombok.*;
import my.anatoliy57.gasstation.enums.OrderStatus;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long brandId;
    private Long stationId;
    private Integer queueId;
    private Integer volume;
    private Long orderTime;
    private Integer duration;
    private OrderStatus status;

    private BrandDto brandDto;
}

