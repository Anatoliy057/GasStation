package my.anatoliy57.gasstation.services;

import my.anatoliy57.gasstation.domain.dto.StationDto;
import my.anatoliy57.gasstation.domain.entity.Station;
import my.anatoliy57.gasstation.exceptions.StationNameExistException;
import my.anatoliy57.gasstation.exceptions.StationNotFoundException;
import my.anatoliy57.gasstation.mappers.*;
import my.anatoliy57.gasstation.repos.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final StationMapper stationMapper;
    private final OrderMapper orderMapper;
    private final MarkupMapper markupMapper;
    private final BrandMapper brandMapper;
    private final PeriodMapper periodMapper;

    private final StationRepo stationRepo;

    @Autowired
    public StationService(StationMapper stationMapper,
                          OrderMapper orderMapper,
                          MarkupMapper markupMapper,
                          BrandMapper brandMapper,
                          PeriodMapper periodMapper,
                          StationRepo stationRepo) {
        this.stationMapper = stationMapper;
        this.orderMapper = orderMapper;
        this.markupMapper = markupMapper;
        this.brandMapper = brandMapper;
        this.periodMapper = periodMapper;
        this.stationRepo = stationRepo;
    }

    public StationDto create(StationDto dto) throws StationNameExistException {
        String name = dto.getName();
        if (stationRepo.existByName(name)) {
            throw new StationNameExistException(name);
        }

        Station station = stationMapper.createFromDto(dto);

        station = stationRepo.save(station);

        return stationMapper.toDto(station);
    }

    public StationDto update(StationDto dto) throws StationNotFoundException, StationNameExistException {
        long id = dto.getId();
        Station station = stationRepo.findById(id);

        if (station == null) {
            throw new StationNotFoundException(id);
        }

        String name = dto.getName();
        Station stationNamed = stationRepo.findByName(name);

        if (stationNamed != null && stationNamed != station) {
            throw new StationNameExistException(name);
        }

        stationMapper.updateFromDto(dto, station);

        station = stationRepo.save(station);

        return stationMapper.toDto(station);
    }

    public StationDto remove(long id) {
        Station station = stationRepo.removeById(id);

        return stationMapper.toDto(station);
    }

    public List<StationDto> getAll() {
        return stationMapper.toDto(stationRepo.findAll());
    }

    public StationDto search(String name) {
        return stationMapper.toDto(stationRepo.findByName(name));
    }

    public StationDto getFull(long id) {
        Station station = stationRepo.findById(id);

        return stationMapper.toFullDto(station, orderMapper, markupMapper, brandMapper, periodMapper);
    }

    public StationDto get(long id) {
        return stationMapper.toDto(stationRepo.findById(id));
    }

    public void startGasStation(long id) {
        Station station = stationRepo.findById(id);

        // todo business logic
    }
}
