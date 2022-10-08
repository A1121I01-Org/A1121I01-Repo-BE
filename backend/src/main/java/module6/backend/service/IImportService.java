package module6.backend.service;

import module6.backend.entity.Import;
import module6.backend.entity.customer.Customer;
import module6.backend.entity.material.Material;

import java.util.List;
import java.util.Optional;

public interface IImportService {
    List<Import> findAllImport(Integer page);

    void deleteImport(Long idAfterUpdate, Boolean flag, Long idBeforeUpdate);

    Optional<Import> findImportById(Long id);

    void updateImport(Import importUpdate, Integer quantityBeforeUpdate);

    void createImport(Import importCreate, Material materialCreate, Customer customerCreate);

    Import findImportByCode(String importCode);
}
