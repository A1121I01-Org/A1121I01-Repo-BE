package module6.backend.repository;

import module6.backend.entity.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IMaterialRepository extends JpaRepository<Material, Long> {
/*
HieuCLH
Query lay san pham moi
*/
@Query(value = "select * from material order by material_id desc limit 4" , nativeQuery = true)
List<Material> findTopNewMaterial();
}
