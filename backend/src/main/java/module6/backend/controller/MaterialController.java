package module6.backend.controller;

import module6.backend.entity.material.Material;
import module6.backend.service.ICustomerService;
import module6.backend.service.IMaterialService;
import module6.backend.service.IMaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("api/material")
public class MaterialController {
    @Autowired
    private IMaterialService materialService;

//    @GetMapping(value = "/list")
//    public ResponseEntity<Page<Material>> materialList (@PageableDefault(value = 3) Pageable pageable){
//        Page<Material> materials = this.materialService.findAll(pageable,);
//        if (materials.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(materials, HttpStatus.OK);
//    }
//    @GetMapping("/find-by-id/{id}")
//    public ResponseEntity<Material> findFloorsById(@PathVariable Integer id) {
//        return null;
//    }

//    @GetMapping("/list")
//    public ResponseEntity<Iterable<Material>> findAllMaterial(Pageable pageable,
//                                                              @RequestParam(defaultValue = "0") String search){
//        System.out.println(1);
//        List<Material> materialList = materialService.findAll(pageable, search).getContent();
//        if (materialList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(materialList, HttpStatus.OK);
//}



/**
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
    /**
    HieuCLH
    List san pham moi
    */
    @GetMapping("detail")
    @ResponseStatus(HttpStatus.OK)
    public List<Material> getTopMaterial() {

        return materialService.findTopNewMaterial();
    }


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


    /** Get list material. */
    @GetMapping("/list")
    public ResponseEntity<Page<Material>> getAllMaterial(@RequestParam("page") Integer page,
                                                 @RequestParam("size") Integer size)
    {

        Page<Material>materials= materialService.getAllMaterial(page, size);
        return new ResponseEntity<>(materials,HttpStatus.OK);
    }

    /** Get list material. */
    @GetMapping("/search")
    public ResponseEntity<Page<Material>> getAllMaterialSearch(@RequestParam("page") Integer page,
                                                         @RequestParam("size") Integer size, @RequestParam("search") String search)
    {

        Page<Material>materials= materialService.getAllMaterialSearch(search, page, size);
        return new ResponseEntity<>(materials,HttpStatus.OK);
    }

}


