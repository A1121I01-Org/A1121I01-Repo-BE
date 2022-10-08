package module6.backend.repository;

import module6.backend.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IImportRepository extends JpaRepository<Import, Long> {
    // Thắng code list import
    @Query(value = "select * from import where import_account_id > 0 and import_material_id > 0 and import_id > 0 and import_flag = 0 limit ?1, 3", nativeQuery = true)
    List<Import> findAllImport(Integer page);

    // Thắng code xoá import
    @Query(value = "UPDATE import SET import_id = ?1, `import_flag` = ?2  WHERE (`import_id` = ?3)", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteImport(Long idAfterUpdate, Boolean flag, Long idBeforeUpdate);

    // Thắng code find import by id
    @Query(value = "select * from import where `import_id` = ?1 and `import_flag` = 0 and `import_account_id` > 0 and `import_material_id` > 0", nativeQuery = true)
    Optional<Import> findImportById(Long id);

    // Thắng code find import theo import_code
    @Query(value = "select * from import where `import_code` = ?1 and `import_flag` = 0 and `import_account_id` > 0 and `import_material_id` > 0;", nativeQuery = true)
    Import findImportByCode(String importCode);

    // Thắng code update import
    @Query(value = "UPDATE `import` SET `import_code` = ?1, `import_start_date` = ?2, `import_quantity` = ?3, `import_account_id` = ?4, `import_material_id` = ?5 WHERE (`import_id` = ?6);", nativeQuery = true)
    @Transactional
    @Modifying
    void updateImport(String code, LocalDate startDate, Integer quantity, Long idAccount, Long idMaterial, Long idImport);

    // Thắng code create import
    @Query(value = "INSERT INTO `import` (`import_code`, `import_start_date`, `import_quantity`, `import_account_id`, `import_material_id`) VALUES (?1, ?2, ?3, ?4, ?5);", nativeQuery = true)
    @Transactional
    @Modifying
    void createImport(String code, LocalDate startDate, Integer quantity, Long idAccount, Long idMaterial);
}
