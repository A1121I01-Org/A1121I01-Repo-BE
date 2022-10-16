package module6.backend.repository;

import module6.backend.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IImportRepository extends JpaRepository<Import, Long> {
    @Query(value = "select sum(`import`.import_quantity * material.material_price) as tien_nhap_hang from `import` join material on import.import_material_id = material.material_id ;", nativeQuery = true)
    Integer nhap();

    @Query(value = "select sum(`import`.import_quantity * material.material_price) as tien_nhap_hang from `import` join material on import.import_material_id = material.material_id where month(import_start_date) like :month and year(import_start_date) like :year", nativeQuery = true)
    Integer searchnhap(@Param("month") String month , @Param("year") String year );
}
