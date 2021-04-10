package my.anatoliy57.gasstation.domain.entity;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Period {

    private Long id;
    private Integer density;
    private Integer startTime;
    private Integer endTime;

    private Station station;
}
