package my.anatoliy57.gasstation.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "PERIODS")
@Table(name = "PERIODS")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERIOD")
    private Long id;
    private Integer density;
    @Column(name = "START_TIME")
    private Integer startTime;
    @Column(name = "END_TIME")
    private Integer endTime;

    @ManyToOne
    @JoinColumn(name = "STATION_ID")
    private Station station;
}
