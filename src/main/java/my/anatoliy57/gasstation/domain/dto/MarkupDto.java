package my.anatoliy57.gasstation.domain.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MarkupDto {

    private Long id;
    private Long brandId;
    private Long stationId;
    private Integer percent;
    private Integer startTime;
    private Integer endTime;

    private BrandDto brandDto;
}

