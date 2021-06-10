package my.anatoliy57.gasstation.repos.iml;

import my.anatoliy57.gasstation.domain.entity.Markup;

import java.util.List;

public class MarkupRepoIml extends ArrayRepo<Markup> {

    public Markup findCurrentByBrandIdAndStationId(long currentTime, long brandId, long stationId) {
        return findByPredicate(markup -> markup.getBrand().getId().equals(brandId)
                && markup.getStartTime() < currentTime
                && markup.getEndTime() < currentTime
                && markup.getStation().getId().equals(stationId));
    }

    public List<Markup> findAllByStationId(long stationId) {
        return super.findAllByPredicate(markup -> markup.getStation().getId().equals(stationId));
    }

    public boolean existCurrentByBrandIdAndStationId(long currentTime, long brandId, long stationId) {
        return findCurrentByBrandIdAndStationId(currentTime, brandId, stationId) != null;
    }

    public boolean existMarkupInByBrandIdAndStationId(long startTime, long endTime, long brandId, long stationId) {
        return findByPredicate(
                markup -> markup.getStation().getId().equals(stationId)
                        && markup.getBrand().getId().equals(brandId)
                        && markup.getStartTime() <= startTime
                        && markup.getEndTime() >= endTime
        ) != null;
    }

    public Markup save(Markup markup) {
        Markup savedMarkup = super.save(markup);

        savedMarkup.getStation().getMarkups().add(savedMarkup);
        savedMarkup.getBrand().getMarkups().add(savedMarkup);

        return savedMarkup;
    }
}
