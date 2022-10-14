package module6.backend.repository;

import module6.backend.entity.material.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface IMaterialRepository extends JpaRepository<Material, Long> {


    @Query(value = "select * from material where material_flag = false and ( material_name like :search or material_code like :search or " +
            " material_unit like :search)", nativeQuery = true)
    Page<Material> findAllByMaterial(@Param("search") String search, Pageable pageable);

    Page<Material> findAll(Pageable pageable);

}
