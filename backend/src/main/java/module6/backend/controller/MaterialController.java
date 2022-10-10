package module6.backend.controller;

import module6.backend.entity.material.Material;
import module6.backend.service.ICustomerService;
import module6.backend.service.IMaterialService;
import module6.backend.service.IMaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    @GetMapping
        public ResponseEntity<Iterable<Material>> findAllMaterial(Pageable pageable,
                                                                  @RequestParam(defaultValue = "") String search){
        List<Material> materialList = materialService.findAll(pageable, search).getContent();
        if (materialList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materialList, HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
        public ResponseEntity<Material> deleteMaterial(@PathVariable Long id) {
            Optional<Material> materialOptional = materialService.findById(id);
            if (!materialOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            materialService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
