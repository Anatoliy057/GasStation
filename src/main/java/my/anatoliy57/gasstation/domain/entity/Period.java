package my.anatoliy57.gasstation.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "periods")
@Table(name = "periods")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_period")
    private Long id;
    private Integer density;
    @Column(name = "start_time")
    private Integer startTime;
    @Column(name = "end_time")
    private Integer endTime;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
}
