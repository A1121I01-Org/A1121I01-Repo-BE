package module6.backend.controller;

import module6.backend.entity.customer.Customer;
import module6.backend.entity.material.Material;
import module6.backend.entity.material.MaterialType;
import module6.backend.service.ICustomerService;
import module6.backend.service.IMaterialService;
import module6.backend.service.IMaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("api/material")
public class MaterialController {
    @Autowired
    private IMaterialService materialService;

    @Autowired
    private IMaterialTypeService materialTypeService;

    @Autowired
    private ICustomerService customerService;
    @PostMapping("/create")
    public ResponseEntity<Material> saveMaterial(@RequestBody Material material) {
        try {
            System.out.println(material.getMaterialCode());
            materialService.saveMaterial(material.getMaterialCode(),material.getMaterialName()
                    ,material.getMaterialPrice(),material.getMaterialQuantity(),material.getMaterialExpiridate(),
                    material.getMaterialImage(),material.getMaterialDescribe(),material.getMaterialUnit(),
                    material.getMaterialTypeId().getMaterialTypeId(),material.getMaterialCustomerId().getCustomerId());
            return new ResponseEntity<>(material, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(material,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping ("/getById/{id}")
    public ResponseEntity<Material> findById(@PathVariable("id") Long id){
        Material material = materialService.findById(id);
        return new ResponseEntity<>(material,HttpStatus.OK);
    }
    @PatchMapping("/update")
    public ResponseEntity<Material> findMaterialById(@RequestBody Material material) {
//       Material material = materialService.findById(id);
        System.out.println(material.getMaterialCode());
        materialService.updateMaterial(material.getMaterialCode(),material.getMaterialName(),material.getMaterialPrice(),
                material.getMaterialQuantity(),material.getMaterialExpiridate(),
                material.getMaterialImage(),material.getMaterialDescribe() ,material.getMaterialUnit(),material.getMaterialTypeId().getMaterialTypeId(),
                material.getMaterialCustomerId().getCustomerId(), material.getMaterialId());
        return new ResponseEntity<>(material,HttpStatus.OK);
    }
    @GetMapping("customer/list")
    public ResponseEntity<List<Customer>> findAllCustomer() {
        List<Customer> customers = materialService.findAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("materialType/list")
    public ResponseEntity<List<MaterialType>> findAllMaterialType() {
        List<MaterialType> materialTypes = materialService.findAllMaterialType();
        System.out.println(materialTypes.size());
        return new ResponseEntity<>(materialTypes, HttpStatus.OK);
    }
}
