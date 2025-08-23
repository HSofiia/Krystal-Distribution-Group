package be.kdg.prog4.core;

import be.kdg.prog4.domain.Warehouse;
import be.kdg.prog4.port.in.CalculateStorageFeeUseCase;
import be.kdg.prog4.port.out.LoadWarehousePort;
import be.kdg.prog6.common.domain.SellerId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalculateStorageFeeUseCaseImpl implements CalculateStorageFeeUseCase {

    private final LoadWarehousePort loadWarehousePort;

    public CalculateStorageFeeUseCaseImpl(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    @Transactional
    public double calculate(SellerId sellerId, LocalDate asOfDate) {
        List<Warehouse> warehouseList = loadWarehousePort.loadWarehousesBySellerId(sellerId);

        return warehouseList.stream()
                .mapToDouble(warehouse -> warehouse.calculateStorageFee(asOfDate))
                .sum();
    }
}
