package module6.backend.repository;

import module6.backend.entity.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface IMaterialRepository extends JpaRepository<Material, Long> {
    @Query(value = "INSERT INTO `material` (`material_code`, `material_name`, `material_quantity`, `material_price`, `material_expiridate`, `material_unit`, `material_type_id`, `material_customer_id`) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    @Transactional
    @Modifying
    void createMaterialImport(String code, String name, Integer quantity, Double price, LocalDate hsd, String unit, Long typeId, Long customerId);
    @Query(value ="UPDATE `material` SET `material_code` = ?1, `material_name` = ?2, `material_price` = ?3, `material_quantity` = ?4, `material_expiridate` = ?5, `material_image` = ?6, `material_describe` = ?7, `material_unit` = ?8, `material_type_id` = ?9, `material_customer_id` = ?10 WHERE (`material_id` = ?11);",nativeQuery = true)
    @Transactional
    @Modifying
    void updateMaterialImport(String code, String name,  Double price,Integer quantity, LocalDate hsd,String img,String des, String unit, Long typeId, Long customerId,Long materialId);

}
