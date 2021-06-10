package my.anatoliy57.gasstation.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "brands")
@Table(name = "brands")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_brand")
    private Long id;
    private String brand;
    private Integer cost;

    @OneToMany
    private List<Markup> markups = new ArrayList<>();
    @OneToMany
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
}
