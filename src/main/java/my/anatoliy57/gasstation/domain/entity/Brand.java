package my.anatoliy57.gasstation.domain.entity;

import lombok.*;

import java.util.ArrayList;
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

    private List<Markup> markups = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    private Station station;
}
