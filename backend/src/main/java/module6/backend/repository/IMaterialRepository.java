package module6.backend.repository;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMaterialRepository extends JpaRepository<Material, Long> {

    @Query(value = "select material.material_code, material.material_name, `import`.import_quantity, cart.cart_quantity, material.material_quantity from `import`\n" +
            "    join material on `import`.import_material_id = material.material_id\n" +
            "    join cart_material on cart_material.material_id = material.material_id\n" +
            "    join cart on cart.cart_id = cart_material.cart_id;", nativeQuery = true)
    List<String> getData();

}
