package my.anatoliy57.gasstation.services;

import my.anatoliy57.gasstation.domain.dto.OrderDto;
import my.anatoliy57.gasstation.domain.dto.StationDto;
import my.anatoliy57.gasstation.exceptions.GasStationException;
import my.anatoliy57.gasstation.model.FillMachine;
import my.anatoliy57.gasstation.model.OrderFlow;
import my.anatoliy57.gasstation.model.OrderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    private final BrandService brandService;
    private final MarkupService markupService;
    private final OrderService orderService;
    private final PeriodService periodService;

    @Autowired
    public ModelService(BrandService brandService, MarkupService markupService, OrderService orderService, PeriodService periodService, StationService stationService) {
        this.brandService = brandService;

        this.markupService = markupService;
        this.orderService = orderService;
        this.periodService = periodService;
    }

    public void run(StationDto station) throws GasStationException {
        long maxTime = station.getTimeSpanType().getMaxTime();;
        int currentTime = 0;

        List<FillMachine> machines;
        OrderFlow orderFlow;
        OrderGenerator generator;

        generator = new OrderGenerator(station, brandService, orderService);
        orderFlow = new OrderFlow(station, orderService, markupService, periodService, generator);
        machines = new ArrayList<>();
        for (int i = 0; i < station.getCountColumns(); i++) {
            machines.add(new FillMachine(station, orderService));
        }
        int time = 0;
        System.out.println(time + "%");
        while (currentTime < maxTime) {
            int cTime = (int) (currentTime * 100 / maxTime );
            if (time != cTime) {
                time = cTime;
                System.out.println(time + "% " + currentTime);
            }
            Optional<OrderDto> orderOpt = orderFlow.getOrder(currentTime);
            if (orderOpt.isPresent()) {
                OrderDto orderDto = orderOpt.get();
                machines.sort(Comparator.comparingInt(FillMachine::getSizeOrders));
                machines.get(0).addOrderIfPossible(orderDto);
            }

            for (FillMachine machine :
                    machines) {
                machine.service();
            }

            currentTime++;
        }
        System.out.println("");
    }
}
