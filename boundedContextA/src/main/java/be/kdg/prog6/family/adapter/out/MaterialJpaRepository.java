package be.kdg.prog6.family.adapter.out;

import be.kdg.prog6.family.domain.Material;
import be.kdg.prog6.family.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialJpaRepository extends JpaRepository<Material, String> {
    Optional<MaterialJpaEntity> findByName(MaterialType name);
}
