package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.Material;
import be.kdg.prog6.landside.domain.MaterialType;

import java.util.Optional;

@FunctionalInterface
public interface LoadMaterialPort {
    Optional<Material> loadMaterialByName(MaterialType name);
}
