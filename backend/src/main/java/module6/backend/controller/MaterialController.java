package module6.backend.controller;

import module6.backend.entity.material.Material;
import module6.backend.service.ICustomerService;
import module6.backend.service.IMaterialService;
import module6.backend.service.IMaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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

/*
HieuCLh
Get ID Material
*/

    @GetMapping("detail/{id}")
    public ResponseEntity<Material> findMaterialById(@PathVariable("id") Long id){
        Optional<Material> material=materialService.findById(id);

        if(material.isPresent()){
            return new ResponseEntity<>(material.get(), HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    /*
    HieuCLH
    List san pham moi
    */
    @GetMapping("detail")
    @ResponseStatus(HttpStatus.OK)
    public List<Material> getTopMaterial() {

        return materialService.findTopNewMaterial();
    }

}
