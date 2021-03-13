package my.anatoliy57.gasstation.services;

import my.anatoliy57.gasstation.mappers.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class TempService {

    private final OrderMapper mapper;

    public TempService(OrderMapper mapper) {
        this.mapper = mapper;
    }
}
