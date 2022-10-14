package module6.backend.service;

import module6.backend.entity.material.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface IMaterialService {

       Page<Material> getAllMaterialSearch(String search, Integer page, Integer size);

       Page<Material> getAllMaterial(Integer page, Integer size);
}

