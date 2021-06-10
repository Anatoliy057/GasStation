package my.anatoliy57.gasstation.services;

import my.anatoliy57.gasstation.domain.dto.BrandDto;
import my.anatoliy57.gasstation.domain.entity.Brand;
import my.anatoliy57.gasstation.exceptions.BrandNameExistException;
import my.anatoliy57.gasstation.exceptions.BrandNotFoundException;
import my.anatoliy57.gasstation.mappers.BrandMapper;
import my.anatoliy57.gasstation.mappers.MarkupMapper;
import my.anatoliy57.gasstation.mappers.OrderMapper;
import my.anatoliy57.gasstation.repos.BrandRepo;
import my.anatoliy57.gasstation.repos.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final BrandMapper brandMapper;
    private final OrderMapper orderMapper;
    private final MarkupMapper markupMapper;

    private final BrandRepo brandRepo;
    private final StationRepo stationRepo;

    @Autowired
    public BrandService(BrandMapper brandMapper,
                        OrderMapper orderMapper,
                        MarkupMapper markupMapper,
                        BrandRepo brandRepo,
                        StationRepo stationRepo) {
        this.brandMapper = brandMapper;
        this.orderMapper = orderMapper;
        this.markupMapper = markupMapper;
        this.brandRepo = brandRepo;
        this.stationRepo = stationRepo;
    }

    public BrandDto create(BrandDto dto) throws BrandNameExistException {
        String name = dto.getBrand();
        long stationId = dto.getStationId();
        if (brandRepo.existsByBrandAndStation_Id(name, stationId)) {
            throw new BrandNameExistException(name, stationId);
        }

        Brand brand = brandMapper.createFromDto(dto, stationRepo);

        brand = brandRepo.save(brand);

        return brandMapper.toDto(brand);
    }

    public BrandDto update(BrandDto dto) throws BrandNotFoundException, BrandNameExistException {
        long id = dto.getId();
        Optional<Brand> brandOpt = brandRepo.findById(id);

        if (brandOpt.isEmpty()) {
            throw new BrandNotFoundException(id);
        }
        Brand brand = brandOpt.get();

        String name = dto.getBrand();
        long stationId = dto.getStationId();

        brandOpt = brandRepo.findByBrandAndStation_Id(name, stationId);

        if (brandOpt.isEmpty()) {
            throw new BrandNameExistException(name, stationId);
        }

        brandMapper.updateFromDto(dto, brand);

        brand = brandRepo.save(brand);

        return brandMapper.toDto(brand);
    }

    public void remove(long id) {
        brandRepo.deleteById(id);
    }

    boolean exist(String name, long stationId) {
        return brandRepo.existsByBrandAndStation_Id(name, stationId);
    }

    public BrandDto get(long id) throws BrandNotFoundException {
        Optional<Brand> brandOpt = brandRepo.findById(id);

        if (brandOpt.isEmpty()) {
            throw new BrandNotFoundException(id);
        }
        Brand brand = brandOpt.get();

        return brandMapper.toDto(brand);
    }

    public BrandDto get(String name, long stationId) throws BrandNameExistException {
        Optional<Brand> brandOpt = brandRepo.findByBrandAndStation_Id(name, stationId);

        if (brandOpt.isEmpty()) {
            throw new BrandNameExistException(name, stationId);
        }
        Brand brand = brandOpt.get();
        return brandMapper.toDto(brand);
    }

    public List<BrandDto> getAllInStation(long stationId) {
        return brandRepo.findAllByStation_Id(stationId).stream()
                .map(brandMapper::toDto)
                .collect(Collectors.toList());
    }

    public BrandDto getAsFull(long id) throws BrandNotFoundException {
        Optional<Brand> brandOpt = brandRepo.findById(id);

        if (brandOpt.isEmpty()) {
            throw new BrandNotFoundException(id);
        }
        Brand brand = brandOpt.get();
        return brandMapper.toFullDto(brand, orderMapper, markupMapper);
    }

    public BrandDto getAsFull(String name, long stationId) throws BrandNameExistException {
        Optional<Brand> brandOpt = brandRepo.findByBrandAndStation_Id(name, stationId);

        if (brandOpt.isEmpty()) {
            throw new BrandNameExistException(name, stationId);
        }
        Brand brand = brandOpt.get();
        return brandMapper.toFullDto(brand, orderMapper, markupMapper);
    }

    public List<BrandDto> getAllInStationAsFull(long stationId) {
        return brandRepo.findAllByStation_Id(stationId).stream()
                .map(brand -> brandMapper.toFullDto(brand, orderMapper, markupMapper))
                .collect(Collectors.toList());
    }
}
