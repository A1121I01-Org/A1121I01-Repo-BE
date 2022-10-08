package module6.backend.repository;

import module6.backend.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImportRepository extends JpaRepository<Import, Long> {
}
