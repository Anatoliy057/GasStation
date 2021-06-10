package my.anatoliy57.gasstation.services;

import my.anatoliy57.gasstation.domain.dto.MarkupDto;
import my.anatoliy57.gasstation.domain.entity.Markup;
import my.anatoliy57.gasstation.exceptions.IntersectionMarkupsException;
import my.anatoliy57.gasstation.exceptions.MarkupNotFoundException;
import my.anatoliy57.gasstation.mappers.BrandMapper;
import my.anatoliy57.gasstation.mappers.MarkupMapper;
import my.anatoliy57.gasstation.repos.BrandRepo;
import my.anatoliy57.gasstation.repos.MarkupRepo;
import my.anatoliy57.gasstation.repos.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarkupService {

    private final MarkupMapper markupMapper;
    private final BrandMapper brandMapper;

    private final MarkupRepo markupRepo;
    private final BrandRepo brandRepo;
    private final StationRepo stationRepo;

    @Autowired
    public MarkupService(MarkupMapper markupMapper,
                         BrandMapper brandMapper,
                         MarkupRepo markupRepo,
                         StationRepo stationRepo,
                         BrandRepo brandRepo) {
        this.markupMapper = markupMapper;
        this.brandMapper = brandMapper;
        this.markupRepo = markupRepo;
        this.stationRepo = stationRepo;
        this.brandRepo = brandRepo;
    }

    public MarkupDto create(MarkupDto dto) throws IntersectionMarkupsException {
        long brandId = dto.getBrandId();
        long stationId = dto.getStationId();
        long start = dto.getStartTime();
        long end = dto.getEndTime();
        if (
                markupRepo.existCurrentByBrandIdAndStationId(start, brandId, stationId)
                        || markupRepo.existCurrentByBrandIdAndStationId(end, brandId, stationId)
                        || markupRepo.existMarkupInByBrandIdAndStationId(start, end, brandId, stationId)
        ) {
            throw new IntersectionMarkupsException(start, end);
        }

        Markup markup = markupMapper.createFromDto(dto, stationRepo, brandRepo);

        markup = markupRepo.save(markup);

        return markupMapper.toDto(markup);
    }

    public MarkupDto update(MarkupDto dto) throws MarkupNotFoundException {
        Long id = dto.getId();
        Optional<Markup> markupOpt = markupRepo.findById(id);

        if (markupOpt.isEmpty()) {
            throw new MarkupNotFoundException(id);
        }
        Markup markup = markupOpt.get();

        markupMapper.updateFromDto(dto, markup);

        markup = markupRepo.save(markup);

        return markupMapper.toDto(markup);
    }

    public void remove(long id) {
        markupRepo.deleteById(id);
    }

    public MarkupDto get(long id) throws MarkupNotFoundException {
        Optional<Markup> markupOpt = markupRepo.findById(id);
        if (markupOpt.isEmpty()) {
            throw new MarkupNotFoundException(id);
        }
        Markup markup = markupOpt.get();
        return markupMapper.toDto(markup);
    }

    public Optional<MarkupDto> getCurrent(long time, long brandId, long stationId) {
        return Optional.ofNullable(markupMapper.toDto(markupRepo.findCurrentByBrandIdAndStationId(time, brandId, stationId)));
    }

    public List<MarkupDto> getAllInStation(long stationId) {
        return markupMapper.toDto(markupRepo.findAllByStationId(stationId));
    }

    public MarkupDto getAsFull(long id) throws MarkupNotFoundException {
        Optional<Markup> markupOpt = markupRepo.findById(id);
        if (markupOpt.isEmpty()) {
            throw new MarkupNotFoundException(id);
        }
        Markup markup = markupOpt.get();
        return markupMapper.toFullDto(markup, brandMapper);
    }

    public MarkupDto getCurrentAsFull(long time, long brandId, long stationId) {
        return markupMapper.toFullDto(markupRepo.findCurrentByBrandIdAndStationId(time, brandId, stationId), brandMapper);
    }

    public List<MarkupDto> getAllInStationAsFull(long stationId) {
        return markupRepo.findAllByStationId(stationId).stream()
                .map(order -> markupMapper.toFullDto(order, brandMapper))
                .collect(Collectors.toList());
    }
}
