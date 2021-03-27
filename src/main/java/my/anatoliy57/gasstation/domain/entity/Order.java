package my.anatoliy57.gasstation.domain.entity;

import lombok.*;
import my.anatoliy57.gasstation.enums.OrderStatus;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private Integer queueId;
    private Integer volume;
    private Long orderTime;
    private Long duration;
    private OrderStatus status = OrderStatus.SERVICING;

    private Brand brand;
    private Station station;
}
