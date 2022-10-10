package module6.backend.service.Impl;

import module6.backend.entity.material.Material;
import module6.backend.repository.IMaterialRepository;
import module6.backend.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<Material> findAll(Pageable pageable, String search) {
        return materialRepository.findAll(pageable, search);
    }


    @Override
    public void deleteById(Long id) {
         materialRepository.deleteById(id);
    }
}
