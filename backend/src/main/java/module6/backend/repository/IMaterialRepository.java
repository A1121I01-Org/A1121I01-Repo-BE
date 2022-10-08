package module6.backend.repository;

import module6.backend.entity.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public interface IMaterialRepository extends JpaRepository<Material, Long> {
    // Thắng code tìm kiếm material theo material code
    Material findByMaterialCode(String materialCode);

    // Thắng code thêm mới Vật tư (Import)
    @Query(value = "INSERT INTO `material` (`material_code`, `material_name`, `material_quantity`, `material_price`, `material_expiridate`, `material_image`, `material_describe`, `material_unit`, `material_type_id`, `material_customer_id`) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
    @Transactional
    @Modifying
    void createMaterialImport(String code, String name, Integer quantity, Double price, LocalDate hsd, String image, String describe, String unit, Long typeId, Long customerId);
}
