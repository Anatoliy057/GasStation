package my.anatoliy57.gasstation.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "BRANDS")
@Table(name = "BRANDS")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BRAND")
    private Long id;
    private String brand;
    private Integer cost;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Markup> markups = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "STATION_ID")
    private Station station;
}
