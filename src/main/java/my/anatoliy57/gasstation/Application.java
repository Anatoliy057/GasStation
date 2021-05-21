package my.anatoliy57.gasstation;

import lombok.AccessLevel;
import lombok.Setter;
import my.anatoliy57.gasstation.domain.dto.*;
import my.anatoliy57.gasstation.enums.TimeSpanType;
import my.anatoliy57.gasstation.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Setter(value = AccessLevel.PRIVATE, onMethod = @__(@Autowired))
@SpringBootApplication
public class Application implements CommandLineRunner {

    private BrandService brandService;
    private MarkupService markupService;
    private OrderService orderService;
    private PeriodService periodService;
    private StationService stationService;
    private ModelService modelService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        StationDto stationDto = StationDto.builder()
                .countColumns(2)
                .maxQueue(5)
                .maxTimeService(100)
                .minTimeService(80)
                .timeSpanType(TimeSpanType.WEEK)
                .maxVolumeGasoline(20)
                .minVolumeGasoline(10)
                .defaultDensity(200)
                .name("Test")
                .build();
        stationDto = stationService.create(stationDto);

        BrandDto brandDto = BrandDto.builder()
                .brand("Brand")
                .cost(49)
                .stationId(stationDto.getId())
                .build();
        brandDto = brandService.create(brandDto);

        MarkupDto markupDto = MarkupDto.builder()
                .endTime(5000)
                .startTime(1000)
                .percent(4)
                .brandId(brandDto.getId())
                .stationId(stationDto.getId())
                .build();
        markupDto = markupService.create(markupDto);

        PeriodDto periodDto = PeriodDto.builder()
                .density(4000)
                .startTime(2000)
                .endTime(4000)
                .stationId(stationDto.getId())
                .build();
        periodDto = periodService.create(periodDto);

        modelService.run(stationDto);

        List<OrderDto> result = orderService.getAllCompletedAsFull(TimeSpanType.WEEK.getMaxTime(), stationDto.getId());
        long sum = 0;
        for (OrderDto o :
                result) {
            sum += o.getBrandDto().getCost() * o.getVolume();
        }
        System.out.println("Доход " + sum);
    }
}
