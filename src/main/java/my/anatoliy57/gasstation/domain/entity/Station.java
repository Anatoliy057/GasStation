package my.anatoliy57.gasstation.domain.entity;

import lombok.*;
import my.anatoliy57.gasstation.enums.TimeSpanType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "STATIONS")
@Table(name = "STATIONS")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_STATION")
    private Long id;
    private String name;
    @Column(name = "COUNT_COLUMNS")
    private Integer countColumns;
    @Column(name = "MAX_QUEUE")
    private Integer maxQueue;
    @Column(name = "MIN_VOLUME_GASOLINE")
    private Integer minVolumeGasoline;
    @Column(name = "MAX_VOLUME_GASOLINE")
    private Integer maxVolumeGasoline;
    @Column(name = "MIN_TIME_SERVICE")
    private Integer minTimeService;
    @Column(name = "MAX_TIME_SERVICE")
    private Integer maxTimeService;
    @Column(name = "TIME_SPAN_TYPE")
    private TimeSpanType timeSpanType;
    @Column(name = "DEFAULT_DENSITY")
    private Integer defaultDensity;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    private List<Order> orders = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "MARKUP_ID")
    private List<Markup> markups = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRAND_ID")
    private List<Brand> brands = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERIOD_ID")
    private List<Period> periods = new ArrayList<>();
}
