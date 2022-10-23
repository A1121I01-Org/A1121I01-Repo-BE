package module6.backend.repository;

import module6.backend.entity.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMaterialRepository extends JpaRepository<Material, Long> {
    // Thắng code tìm kiếm material theo material code
    Material findByMaterialCode(String materialCode);

    // Thắng code thêm mới Vật tư (Import)
    @Query(value = "INSERT INTO `material` (`material_code`, `material_name`, `material_quantity`, `material_price`, `material_expiridate`, `material_unit`, `material_type_id`, `material_customer_id`, `material_image`,`material_describe`) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
    @Transactional
    @Modifying
    void createMaterialImport(String code, String name, Integer quantity, Double price, LocalDate hsd, String unit, Long typeId, Long customerId, String image, String describe);

    // Thắng code list material theo customer_id
    @Query(value = "SELECT * FROM material where material_flag = 0 and material_id > 0 and material_type_id > 0 and material_customer_id = ?1", nativeQuery = true)
    List<Material> findAllMaterialImport(Long customerId);

    // Thắng code list material kiểm tra tồn tại code customer
    @Query(value = "SELECT material_code FROM material", nativeQuery = true)
    List<String> findAllMaterialImportString();

    //Thắng code update vật tư theo nhập kho
    @Query(value = "UPDATE `material` SET `material_code` = ?1, `material_name` = ?2, `material_unit` = ?3, `material_quantity` = ?4 WHERE (`material_id` = ?5)", nativeQuery = true)
    @Transactional
    @Modifying
    void updateMaterialImport(String materialCode, String materialName, String materialUnit, Integer quantityMaterial, Long materialId);
}
