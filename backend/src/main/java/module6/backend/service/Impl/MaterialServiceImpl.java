package module6.backend.service.Impl;

import module6.backend.entity.customer.Customer;
import module6.backend.entity.material.Material;
import module6.backend.entity.material.MaterialType;
import module6.backend.repository.ICustomerRepository;
import module6.backend.repository.IMaterialRepository;
import module6.backend.repository.IMaterialTypeRepository;
import module6.backend.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaterialServiceImpl implements IMaterialService {
    @Autowired
    private IMaterialRepository materialRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IMaterialTypeRepository materialTypeRepository;

    @Override
    public void saveMaterial(String code, String name,  Double price,Integer quantity, LocalDate hsd,String img,String des, String unit, Long typeId, Long customerId) {
        materialRepository.createMaterialImport(code, name, price, quantity, hsd, img,des, unit, typeId, customerId);
    }

    @Override
    public Material findById(Long id) {
        return materialRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public List<MaterialType> findAllMaterialType() {
        return materialTypeRepository.findAll();
    }

    @Override
    public void updateMaterial(String code, String name, Double price, Integer quantity, LocalDate hsd, String img, String des, String unit, Long typeId, Long customerId, Long materialId) {
        materialRepository.updateMaterialImport(code, name, price, quantity, hsd, img, des , unit, typeId, customerId, materialId);
    }
}
