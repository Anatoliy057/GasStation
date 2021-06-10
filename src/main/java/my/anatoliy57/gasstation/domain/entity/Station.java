package my.anatoliy57.gasstation.domain.entity;

import lombok.*;
import my.anatoliy57.gasstation.enums.TimeSpanType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "stations")
@Table(name = "stations")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_station")
    private Long id;
    private String name;
    @Column(name = "count_columns")
    private Integer countColumns;
    @Column(name = "max_queue")
    private Integer maxQueue;
    @Column(name = "min_volume_gasoline")
    private Integer minVolumeGasoline;
    @Column(name = "max_volume_gasoline")
    private Integer maxVolumeGasoline;
    @Column(name = "min_time_service")
    private Integer minTimeService;
    @Column(name = "max_time_service")
    private Integer maxTimeService;
    @Column(name = "time_span_type")
    private TimeSpanType timeSpanType;
    @Column(name = "default_density")
    private Integer defaultDensity;

    @OneToMany
    private List<Order> orders = new ArrayList<>();
    @OneToMany
    private List<Markup> markups = new ArrayList<>();
    @OneToMany
    private List<Brand> brands = new ArrayList<>();
    @OneToMany
    private List<Period> periods = new ArrayList<>();
}
