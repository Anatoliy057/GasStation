package my.anatoliy57.gasstation.domain.entity;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    private Long id;
    private String brand;
    private Integer cost;

    private List<Markup> markups;
    private List<Order> orders;

    private Station station;
}
