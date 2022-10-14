package module6.backend.repository;

import module6.backend.entity.material.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface IMaterialRepository extends JpaRepository<Material, Long> {

/*
HieuCLH
Query lay san pham moi
*/
@Query(value = "select * from material order by material_id desc limit 4" , nativeQuery = true)
List<Material> findTopNewMaterial();

    @Transactional
    @Modifying
    @Query(value = "UPDATE material set material_flag = true where material_id = :id", nativeQuery = true)
    void deleteById(Long id);

    @Query(value = "select * from material where material_flag = false and ( material_name like %:search% or material_code like %:search% or " +
                    " material_unit like %:search%)", nativeQuery = true)
    Page<Material> findAll(Pageable pageable, String search);



}
