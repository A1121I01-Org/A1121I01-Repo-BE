package module6.backend.service;
import module6.backend.entity.material.Material;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
public interface IMaterialService {
    Optional<Material> findById(Long id);

    List<Material>findTopNewMaterial();

    Page<Material> findAll(Pageable pageable, String search);
    void deleteById(Long id);

}
