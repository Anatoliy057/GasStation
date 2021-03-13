package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.Markup;
import my.anatoliy57.gasstation.repos.MarkupRepo;
import org.springframework.stereotype.Repository;

@Repository
public class MarkupRepoIml extends ArrayRepo<Markup> implements MarkupRepo {
    @Override
    public Markup findCurrentMarkupByBrandIdAndGasStationId(Long currentTime, Long brandId, Long gasStationId) {
        return findByPredicate(markup -> markup.getGasolineBrand().getId().equals(brandId)
                && markup.getStartTime() < currentTime
                && markup.getEndTime() < currentTime
                && markup.getGasStation().getId().equals(gasStationId));
    }
}
