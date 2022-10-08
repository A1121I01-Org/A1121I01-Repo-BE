package module6.backend.controller;

import module6.backend.entity.Import;
import module6.backend.service.IAccountService;
import module6.backend.service.IImportService;
import module6.backend.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/import")
public class ImportController {
    @Autowired
    private IImportService importService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IMaterialService materialService;

    //Thắng code list import
    @GetMapping("import-list")
    public ResponseEntity<List<Import>> findAllImport(@RequestParam Integer page) {
        List<Import> importList = importService.findAllImport(page);
        if (importList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(importList, HttpStatus.OK);
        }
    }

    //Thắng code tìm import theo id
    @GetMapping("import-detail/{id}")
    public ResponseEntity<Import> findImportById(@PathVariable Long id) {
        Optional<Import> importById = importService.findImportById(id);
        if (!importById.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(importById.get(), HttpStatus.OK);
        }
    }

    //Thắng code xoá import
    @DeleteMapping("import-delete/{id}")
    public ResponseEntity<Import> deleteCustomer(@PathVariable("id") Long id) {
        Optional<Import> foundImport = importService.findImportById(id);
        if (!foundImport.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            importService.deleteImport(-id, true, id);
            return new ResponseEntity<>(foundImport.get(), HttpStatus.NO_CONTENT);
        }
    }

    //Thắng code cập nhật import
    @PutMapping("import-update/{id}")
    public ResponseEntity<?> updateImport(@PathVariable("id") Long id, @Valid @RequestBody Import importUpdate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Optional<Import> foundImport = importService.findImportById(id);
        Integer quantityBeforeUpdate = foundImport.get().getImportQuantity();
        if (!foundImport.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            foundImport.get().setImportCode(importUpdate.getImportCode());
            foundImport.get().setImportStartDate(importUpdate.getImportStartDate());
            foundImport.get().setImportQuantity(importUpdate.getImportQuantity());
            foundImport.get().getImportAccountId().setAccountId(importUpdate.getImportAccountId().getAccountId());
            foundImport.get().getImportMaterialId().setMaterialId(importUpdate.getImportMaterialId().getMaterialId());
            foundImport.get().setImportId(importUpdate.getImportId());
            importService.updateImport(foundImport.get(), quantityBeforeUpdate);
            return new ResponseEntity<>(foundImport.get(), HttpStatus.OK);
        }
    }

    //Thắng code thêm mới import
    @PostMapping("import-create")
    public ResponseEntity<?> saveImport(@Valid @RequestBody Import importCreate, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || (importService.findImportByCode(importCreate.getImportCode()) != null)) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        importService.createImport(importCreate, importCreate.getImportMaterialId(), importCreate.getImportMaterialId().getMaterialCustomerId());
        return new ResponseEntity<>(importCreate, HttpStatus.CREATED);
    }
}
