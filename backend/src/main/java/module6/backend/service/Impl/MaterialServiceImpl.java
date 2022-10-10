package module6.backend.service.Impl;

import module6.backend.entity.material.Material;
import module6.backend.repository.IMaterialRepository;
import module6.backend.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements IMaterialService {
    @Autowired
    private IMaterialRepository materialRepository;

    @Override
    public Optional<Material> findById(Long id) {
        return materialRepository.findById(id);
    }

    @Override
    public List<Material> findTopNewMaterial() {
        return materialRepository.findTopNewMaterial();
    }
}
