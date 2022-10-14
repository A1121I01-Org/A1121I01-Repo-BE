package module6.backend.repository;

import module6.backend.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IImportRepository extends JpaRepository<Import, Long> {
    @Query(value = "select sum(import_quantity * material.material_price) as tien_nhap_hang from `import` join material;", nativeQuery = true)
    Integer nhap();
}
