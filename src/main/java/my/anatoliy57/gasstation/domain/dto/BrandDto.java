package my.anatoliy57.gasstation.domain.dto;

import lombok.*;
import my.anatoliy57.gasstation.domain.entity.Markup;
import my.anatoliy57.gasstation.domain.entity.Order;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

    private Long id;
    private Long stationId;
    private String brand;
    private Integer cost;

    private List<MarkupDto> markups;
    private List<OrderDto> orders;
}
