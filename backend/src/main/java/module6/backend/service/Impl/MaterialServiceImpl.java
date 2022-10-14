package module6.backend.service.Impl;

import module6.backend.entity.material.Material;
import module6.backend.repository.IMaterialRepository;
import module6.backend.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements IMaterialService{
    @Autowired
    private IMaterialRepository materialRepository;

    @Override
    public Page<Material> getAllMaterialSearch(String search, Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return materialRepository.findAllByMaterial("%"+search+"%",paging);
    }

    @Override
    public Page<Material> getAllMaterial(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size, Sort.by("materialId").descending());
        return materialRepository.findAll(paging);
    }

//    @Override
//    public Optional<Material> findById(Long id) {
//        return materialRepository.findById(id);
//    }
//
//    @Override
//    public Page<Material> findAll(Pageable pageable, String search) {
//        return materialRepository.findAll(pageable, search);
//    }


}
