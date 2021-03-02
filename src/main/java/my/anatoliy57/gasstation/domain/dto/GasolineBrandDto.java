package my.anatoliy57.gasstation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class GasolineBrandDto {

    private Long id;
    private Long gasStationId;
    private String brand;
    private Integer cost;
}
