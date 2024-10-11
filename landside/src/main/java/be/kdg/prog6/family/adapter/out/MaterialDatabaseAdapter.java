package be.kdg.prog6.family.adapter.out;

import be.kdg.prog6.family.domain.Material;
import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.port.out.LoadMaterialPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MaterialDatabaseAdapter implements LoadMaterialPort {
    private final MaterialJpaRepository materialJpaRepository;

    public MaterialDatabaseAdapter(MaterialJpaRepository materialJpaRepository) {
        this.materialJpaRepository = materialJpaRepository;
    }


    @Override
    public Optional<Material> loadMaterialByName(MaterialType name) {
        return materialJpaRepository.findByName(name).map(this::toMaterial);
    }

    private Material toMaterial(final MaterialJpaEntity materialJpaEntity) {
        return new Material(
                materialJpaEntity.getMaterialId(),
                materialJpaEntity.getName(),
                materialJpaEntity.getStorageCostPerTon()
                );
    }
}