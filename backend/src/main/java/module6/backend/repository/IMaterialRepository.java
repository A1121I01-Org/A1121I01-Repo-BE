package module6.backend.repository;
import module6.backend.entity.Import;
import module6.backend.entity.material.Material;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface IMaterialRepository extends JpaRepository<Material, Long> {
    //HoangTND - Statistic Material
    //List material
    @Query(value = "select material.material_code, material.material_name, `import`.import_quantity, cart.cart_quantity, material.material_quantity from `import` \n" +
            "join material on `import`.import_material_id = material.material_id\n" +
            "join cart_material on cart_material.material_id = material.material_id\n" +
            "join cart on cart.cart_id = cart_material.cart_id;  ", nativeQuery = true)
    List<String> findAllStatisticMaterial();

    //Search material
    @Query(value = "select material.material_code, material.material_name, sum(`import`.import_quantity), `import`.import_start_date, cart.cart_quantity, material.material_quantity from `import` \n" +
            "join material on `import`.import_material_id = material.material_id\n" +
            "join cart_material on cart_material.material_id = material.material_id\n" +
            "join cart on cart.cart_id = cart_material.cart_id\n" +
            "group by`import`.import_material_id\n" +
            "having `import`.import_start_date between :fromDate and :toDate", nativeQuery = true)
    List<String> searchStatisticMaterial(@Param("fromDate") String fromDate,
                                         @Param("toDate")String toDate);

    @Query(value = "select material.material_code, material.material_name, `import`.import_quantity, cart.cart_quantity, material.material_quantity from `import` \n" +
            "join material on `import`.import_material_id = material.material_id\n" +
            "join cart_material on cart_material.material_id = material.material_id\n" +
            "join cart on cart.cart_id = cart_material.cart_id;  ", nativeQuery = true)
    String[] findAllStatisticMaterial1();
}
