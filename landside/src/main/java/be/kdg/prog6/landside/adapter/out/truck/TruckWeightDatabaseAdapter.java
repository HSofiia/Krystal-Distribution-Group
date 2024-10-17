package be.kdg.prog6.landside.adapter.out.truck;

import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.landside.domain.TruckWeight;
import be.kdg.prog6.landside.port.out.LoadTruckWeightPort;
import be.kdg.prog6.landside.port.out.SaveTruckWeightPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class TruckWeightDatabaseAdapter implements SaveTruckWeightPort, LoadTruckWeightPort {

    private final TruckWeightJpaRepository truckWeightJpaRepository;

    public TruckWeightDatabaseAdapter(TruckWeightJpaRepository truckWeightJpaRepository) {
        this.truckWeightJpaRepository = truckWeightJpaRepository;
    }

    @Override
    @Transactional
    public void save(TruckWeight truckWeight) {
        TruckWeightJpaEntity truckWeightEntity = toTruckWeightJpaEntity(truckWeight);
        truckWeightJpaRepository.save(truckWeightEntity);
    }



    private TruckWeightJpaEntity toTruckWeightJpaEntity(final TruckWeight truckWeight) {
        TruckWeightJpaEntity truckWeightEntity = new TruckWeightJpaEntity();
        truckWeightEntity.setId(truckWeight.id());
        truckWeightEntity.setWeight(truckWeight.weight());
        truckWeightEntity.setLicencePlate(truckWeight.licencePlate().licensePlate());
        truckWeightEntity.setTime(truckWeight.time());
        return truckWeightEntity;
    }

    private TruckWeight toTruckWeight(final TruckWeightJpaEntity truckWeightJpa) {
        return new TruckWeight(
                truckWeightJpa.getId(),
                new TruckPlate(truckWeightJpa.getLicencePlate()),
                truckWeightJpa.getWeight(),
                truckWeightJpa.getTime()
        );
    }

    @Override
    public Optional<TruckWeight> loadTruckWeightByLicensePlate(String licensePlate) {
        return truckWeightJpaRepository.findByLicencePlate(licensePlate)
                .map(this::toTruckWeight);
    }
}
