package module6.backend.service.Impl;

import module6.backend.entity.Import;
import module6.backend.entity.customer.Customer;
import module6.backend.entity.material.Material;
import module6.backend.repository.ICustomerRepository;
import module6.backend.repository.IImportRepository;
import module6.backend.repository.IMaterialRepository;
import module6.backend.service.IImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImportServiceImpl implements IImportService {
    @Autowired
    private IImportRepository importRepository;

    @Autowired
    private IMaterialRepository materialRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    // Thắng code list import
    @Override
    public List<Import> findAllImport(Integer page) {
        return importRepository.findAllImport(page);
    }

    // Thắng code xoá import
    @Override
    public void deleteImport(Long idAfterUpdate, Boolean flag, Long idBeforeUpdate) {
        importRepository.deleteImport(idAfterUpdate, flag, idBeforeUpdate);
    }

    // Thắng tìm import theo id
    @Override
    public Optional<Import> findImportById(Long id) {
        return importRepository.findImportById(id);
    }

    // Thắng code update import
    @Override
    public void updateImport(Import importAfterUpdate, Integer importBeforeUpdate) {
        Integer quantityMaterial = importAfterUpdate.getImportMaterialId().getMaterialQuantity()
                - importBeforeUpdate
                + importAfterUpdate.getImportQuantity();
        importAfterUpdate.getImportMaterialId().setMaterialQuantity(quantityMaterial);
        importRepository.updateImport(importAfterUpdate.getImportCode(), importAfterUpdate.getImportStartDate(), importAfterUpdate.getImportQuantity(), importAfterUpdate.getImportAccountId().getAccountId(), importAfterUpdate.getImportMaterialId().getMaterialId(), importAfterUpdate.getImportId());
    }

    // Thắng code create import
    @Override
    public void createImport(Import importCreate, Material materialCreate, Customer customerCreate) {
        Integer quantity = importCreate.getImportMaterialId().getMaterialQuantity() + importCreate.getImportQuantity();

        if (customerRepository.findByCustomerCode(customerCreate.getCustomerCode()) == null) {
            customerRepository.createCustomerImport(customerCreate.getCustomerName(), customerCreate.getCustomerCode(), customerCreate.getCustomerAvatar(), customerCreate.getCustomerAddress(), customerCreate.getCustomerPhone(), customerCreate.getCustomerEmail(), customerCreate.getCustomerTypeId().getCustomerTypeId());
            Customer customer = customerRepository.findByCustomerCode(customerCreate.getCustomerCode());

            materialCreate.setMaterialCustomerId(customer);

            materialRepository.createMaterialImport(materialCreate.getMaterialCode(), materialCreate.getMaterialName(), quantity, materialCreate.getMaterialPrice(), materialCreate.getMaterialExpiridate(), materialCreate.getMaterialImage(), materialCreate.getMaterialDescribe(), materialCreate.getMaterialUnit(), materialCreate.getMaterialTypeId().getMaterialTypeId(), materialCreate.getMaterialCustomerId().getCustomerId());
            Material material = materialRepository.findByMaterialCode(materialCreate.getMaterialCode());
            importRepository.createImport(importCreate.getImportCode(), importCreate.getImportStartDate(), importCreate.getImportQuantity(), importCreate.getImportAccountId().getAccountId(), material.getMaterialId());

        } else if (materialRepository.findByMaterialCode(materialCreate.getMaterialCode()) == null) {
            materialRepository.createMaterialImport(materialCreate.getMaterialCode(), materialCreate.getMaterialName(), quantity, materialCreate.getMaterialPrice(), materialCreate.getMaterialExpiridate(), materialCreate.getMaterialImage(), materialCreate.getMaterialDescribe(), materialCreate.getMaterialUnit(), materialCreate.getMaterialTypeId().getMaterialTypeId(), customerCreate.getCustomerId());
            Material material = materialRepository.findByMaterialCode(materialCreate.getMaterialCode());
            importRepository.createImport(importCreate.getImportCode(), importCreate.getImportStartDate(), importCreate.getImportQuantity(), importCreate.getImportAccountId().getAccountId(), material.getMaterialId());
        } else {
            importCreate.getImportMaterialId().setMaterialQuantity(quantity);
            materialRepository.save(importCreate.getImportMaterialId());
            importRepository.createImport(importCreate.getImportCode(), importCreate.getImportStartDate(), importCreate.getImportQuantity(), importCreate.getImportAccountId().getAccountId(), importCreate.getImportMaterialId().getMaterialId());
        }
    }

    @Override
    public Import findImportByCode(String importCode) {
        return importRepository.findImportByCode(importCode);
    }

}
