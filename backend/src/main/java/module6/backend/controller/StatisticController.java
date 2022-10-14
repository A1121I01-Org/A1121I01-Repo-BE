package module6.backend.controller;

import module6.backend.entity.ClassDTO.MaterialStatisticDTO;
import module6.backend.entity.material.Material;
import module6.backend.repository.IMaterialRepository;
import module6.backend.service.IStatisticService;
import module6.backend.service.Impl.PDFStatisticMaterialsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/statistic")
public class StatisticController {
    @Autowired
    private IStatisticService statisticService;

    @Autowired
    private PDFStatisticMaterialsImpl pdfStatisticMaterials;

    @Autowired
    private IMaterialRepository materialRepository;
    //HoangTND - Statistic Material
    //List material
    @GetMapping("/list/material")
    public ResponseEntity<List<String>> data() {
        List<String> data = statisticService.findAllStatisticMaterial();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/list/material1")
    public ResponseEntity<String[]> data1() {
        String[] data = materialRepository.findAllStatisticMaterial1();
        return new ResponseEntity<String[]>(data, HttpStatus.OK);
    }
    //Search material
    @GetMapping("/search/material")
    public ResponseEntity<List<String>>search(@RequestParam String fromDate, @RequestParam String toDate){
        List<String> data = statisticService.searchStatisticMaterial(fromDate,toDate);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    //Print list material
    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> generatePDF() throws IOException {
        String[] data = materialRepository.findAllStatisticMaterial1();
        ByteArrayInputStream bais = pdfStatisticMaterials.export(data);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","inline;filename=cart.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }

}
