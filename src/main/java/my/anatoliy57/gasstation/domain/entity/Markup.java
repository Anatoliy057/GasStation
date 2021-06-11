package my.anatoliy57.gasstation.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "MARKUPS")
@Table(name = "MARKUPS")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Markup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MARKUP")
    private Long id;
    private Integer percent;
    @Column(name = "START_TIME")
    private Integer startTime;
    @Column(name = "END_TIME")
    private Integer endTime;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "STATION_ID")
    private Station station;
}
