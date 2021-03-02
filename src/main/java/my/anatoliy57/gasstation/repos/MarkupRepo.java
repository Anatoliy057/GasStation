package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Markup;

import java.util.List;

public interface MarkupRepo {

    Markup findCurrentMarkupByBrandId(Long currentTime, Long brandId);

    Markup findById(Long id);

    List<Markup> findAll();

    Markup save(Markup order);

    void deleteById(Long id);
}
