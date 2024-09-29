package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.Material;

import java.util.Optional;

@FunctionalInterface
public interface LoadMaterialPort {
    Optional<Material> loadMaterialByName(String name);
}
