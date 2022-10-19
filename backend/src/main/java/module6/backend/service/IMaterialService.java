package module6.backend.service;

import module6.backend.entity.customer.Customer;
import module6.backend.entity.material.Material;
import module6.backend.entity.material.MaterialType;

import java.time.LocalDate;
import java.util.List;

public interface IMaterialService {
    void saveMaterial(String code, String name, Double price, Integer quantity, LocalDate hsd,String img,String des, String unit, Long typeId, Long customerId);
    Material findById(Long id);
    List<Customer> findAllCustomer();
    List<MaterialType> findAllMaterialType();
    void updateMaterial(String code, String name, Double price, Integer quantity, LocalDate hsd, String img,String des , String unit, Long typeId, Long customerId, Long materialId);
}
