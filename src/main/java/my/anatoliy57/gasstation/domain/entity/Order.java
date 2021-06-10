package my.anatoliy57.gasstation.domain.entity;

import lombok.*;
import my.anatoliy57.gasstation.enums.OrderStatus;

import javax.persistence.*;

@Entity(name = "orders")
@Table(name = "orders")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;
    @Column(name = "queue_id")
    private Integer queueId;
    private Integer volume;
    @Column(name = "order_time")
    private Long orderTime;
    private Long duration;
    private OrderStatus status = OrderStatus.SERVICING;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
}
