package module6.backend.controller;

import module6.backend.entity.Import;
import module6.backend.entity.customer.Customer;
import module6.backend.entity.employee.Employee;
import module6.backend.entity.material.Material;
import module6.backend.entity.material.MaterialType;
import module6.backend.service.IImportService;
import module6.backend.service.Impl.pdf.PDFGeneratorImportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/import")
public class ImportController {
    @Autowired
    private IImportService importService;

    @Autowired
    private PDFGeneratorImportServiceImpl PDFImport;

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

    //Thắng code list import
    @GetMapping("import-list-not-pagination")
    public ResponseEntity<List<Import>> findAllImportNotPagination() {
        List<Import> importList = importService.findAllImportNotPagination();
        if (importList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(importList, HttpStatus.OK);
        }
    }

    //Thắng code list import string
    @GetMapping("import-list-string")
    public ResponseEntity<List<String>> findAllImportString() {
        List<String> importList = importService.findAllImportString();
        if (importList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(importList, HttpStatus.OK);
        }
    }

    //Thắng code list material import string
    @GetMapping("import-material-list-string")
    public ResponseEntity<List<String>> findAllImportMaterialString() {
        List<String> importMaterialList = importService.findAllMaterialImportString();
        if (importMaterialList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(importMaterialList, HttpStatus.OK);
        }
    }

    //Thắng code list customer import string kiểm tra code
    @GetMapping("import-customer-list-string")
    public ResponseEntity<List<String>> findAllImportCustomerString() {
        List<String> importCustomerList = importService.findAllCustomerImportString();
        if (importCustomerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(importCustomerList, HttpStatus.OK);
        }
    }

    //Thắng code list customer phone import string kiểm tra code
    @GetMapping("import-phone-customer-list-string")
    public ResponseEntity<List<String>> findAllImportPhoneCustomerString() {
        List<String> phoneCustomerList = importService.findAllCustomerPhoneImportString();
        if (phoneCustomerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(phoneCustomerList, HttpStatus.OK);
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
            importService.updateImport(importUpdate, quantityBeforeUpdate);
            return new ResponseEntity<>(importUpdate, HttpStatus.OK);
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

    @PostMapping("/import-pdf")
    public ResponseEntity<InputStreamResource> generatePDF(@RequestBody Import import1) throws IOException {
        Employee employeePDF = null;
        List<Employee> employeeList = importService.findAllEmployeeImport();
        for (Employee employee : employeeList) {
            if (employee.getEmployeeAccountId().getAccountId() == import1.getImportAccountId().getAccountId()) {
                employeePDF = employee;
            }
        }
        ByteArrayInputStream bais = PDFImport.export(import1, employeePDF);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline;filename=cart.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }

    //Thắng code list nhà cung cấp
    @GetMapping("customer-list")
    public ResponseEntity<List<Customer>> findAllCustomer() {
        List<Customer> customerList = importService.findAllCustomerImport();
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        }
    }

    //Thắng code lấy list admin
    @GetMapping("employee-list")
    public ResponseEntity<List<Employee>> findAllEmployee() {
        List<Employee> employeeList = importService.findAllEmployeeImport();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        }
    }

    // Thắng code list material theo customer_id
    @GetMapping("material-list/{id}")
    public ResponseEntity<List<Material>> findAllMaterialImport(@PathVariable("id") Long id) {
        List<Material> materialList = importService.findAllMaterialImport(id);
        if (materialList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(materialList, HttpStatus.OK);
        }
    }

    // Thắng code list material type
    @GetMapping("material-type-list")
    public ResponseEntity<List<MaterialType>> findAllMaterialTypeImport() {
        List<MaterialType> materialTypeList = importService.findAllMaterialTypeImport();
        if (materialTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(materialTypeList, HttpStatus.OK);
        }
    }
}
