package my.anatoliy57.gasstation.domain.entity;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Markup {

    private Long id;
    private Integer percent;
    private Integer startTime;
    private Integer endTime;

    private Brand brand;
    private Station station;
}
