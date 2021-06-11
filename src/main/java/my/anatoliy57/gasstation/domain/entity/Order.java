package my.anatoliy57.gasstation.domain.entity;

import lombok.*;
import my.anatoliy57.gasstation.enums.OrderStatus;

import javax.persistence.*;

@Entity(name = "ORDERS")
@Table(name = "ORDERS")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDER")
    private Long id;
    @Column(name = "QUEUE_ID")
    private Integer queueId;
    private Integer volume;
    @Column(name = "ORDER_TIME")
    private Long orderTime;
    private Long duration;
    private OrderStatus status = OrderStatus.SERVICING;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "STATION_ID")
    private Station station;
}
