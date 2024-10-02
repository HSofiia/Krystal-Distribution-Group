package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.Material;
import be.kdg.prog6.family.domain.MaterialType;

import java.util.Optional;

@FunctionalInterface
public interface LoadMaterialPort {
    Optional<Material> loadMaterialByName(MaterialType name);
}
