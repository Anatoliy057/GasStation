package my.anatoliy57.gasstation.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "markups")
@Table(name = "markups")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Markup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_markup")
    private Long id;
    private Integer percent;
    @Column(name = "start_time")
    private Integer startTime;
    @Column(name = "end_time")
    private Integer endTime;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
}
