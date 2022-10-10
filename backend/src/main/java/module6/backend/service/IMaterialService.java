package module6.backend.service;

import module6.backend.entity.material.Material;

import java.util.List;
import java.util.Optional;

public interface IMaterialService {
    Optional<Material> findById(Long id);
    List<Material>findTopNewMaterial();
}
