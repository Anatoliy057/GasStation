package my.anatoliy57.gasstation.repos;

import my.anatoliy57.gasstation.domain.entity.Markup;
import my.anatoliy57.gasstation.repos.iml.Repository;

public interface MarkupRepo extends Repository<Long, Markup> {

    Markup findCurrentMarkupByBrandIdAndGasStationId(Long currentTime, Long brandId, Long gasStationId);
}
