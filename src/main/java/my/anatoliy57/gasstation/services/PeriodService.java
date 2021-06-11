package my.anatoliy57.gasstation.services;

import my.anatoliy57.gasstation.domain.dto.PeriodDto;
import my.anatoliy57.gasstation.domain.entity.Period;
import my.anatoliy57.gasstation.exceptions.IntersectionPeriodsException;
import my.anatoliy57.gasstation.exceptions.PeriodNotFoundException;
import my.anatoliy57.gasstation.mappers.PeriodMapper;
import my.anatoliy57.gasstation.repos.PeriodRepo;
import my.anatoliy57.gasstation.repos.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodService {

    private final PeriodMapper periodMapper;

    private final PeriodRepo periodRepo;
    private final StationRepo stationRepo;

    @Autowired
    public PeriodService(PeriodMapper periodMapper,
                         PeriodRepo periodRepo,
                         StationRepo stationRepo) {
        this.periodMapper = periodMapper;
        this.periodRepo = periodRepo;
        this.stationRepo = stationRepo;
    }

    public PeriodDto create(PeriodDto dto) throws IntersectionPeriodsException {
        long stationId = dto.getStationId();
        int start = dto.getStartTime();
        int end = dto.getEndTime();
        if (
                periodRepo.existCurrentByStationId(start, stationId)
                        || periodRepo.existCurrentByStationId(end, stationId)
                        || periodRepo.existPeriodInByStationId(start, end, stationId)
        ) {
            throw new IntersectionPeriodsException(start, end);
        }

        Period period = periodMapper.createFromDto(dto, stationRepo);

        period = periodRepo.save(period);

        return periodMapper.toDto(period);
    }

    public PeriodDto update(PeriodDto dto) throws PeriodNotFoundException {
        long id = dto.getId();
        Optional<Period> periodOpt = periodRepo.findById(id);

        if (periodOpt.isEmpty()) {
            throw new PeriodNotFoundException(id);
        }
        Period period = periodOpt.get();

        periodMapper.updateFromDto(dto, period);

        period = periodRepo.save(period);

        return periodMapper.toDto(period);
    }

    public void remove(long id) {
        periodRepo.deleteById(id);
    }

    boolean exist(int currentTime, long stationId) {
        return periodRepo.existCurrentByStationId(currentTime, stationId);
    }

    public PeriodDto get(long id) throws PeriodNotFoundException {
        Optional<Period> periodOpt = periodRepo.findById(id);

        if (periodOpt.isEmpty()) {
            throw new PeriodNotFoundException(id);
        }
        Period period = periodOpt.get();

        return periodMapper.toDto(period);
    }

    public PeriodDto getCurrent(int currentTime, long stationId) {
        return periodMapper.toDto(periodRepo.findCurrentByStationId(currentTime, stationId));
    }

    public List<PeriodDto> getAllInStation(long stationId) {
        return periodMapper.toDto(periodRepo.findAllByStation_Id(stationId));
    }
}
