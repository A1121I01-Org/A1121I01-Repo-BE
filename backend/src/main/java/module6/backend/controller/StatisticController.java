package module6.backend.controller;

import module6.backend.entity.ClassDTO.MaterialStatisticDTO;
import module6.backend.entity.material.Material;
import module6.backend.repository.ICustomerRepository;
import module6.backend.repository.IMaterialRepository;
import module6.backend.entity.cart.Cart;
import module6.backend.entity.customer.Customer;
import module6.backend.service.ICartService;
import module6.backend.service.IImportService;
import module6.backend.service.ISalaryService;
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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/statistic")
public class StatisticController {
    @Autowired
    private IStatisticService statisticService;

    @Autowired
    private PDFStatisticMaterialsImpl pdfStatisticMaterials;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IMaterialRepository materialRepository;
    //HoangTND - Statistic Material
    //List material

    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private IImportService importService;
    @Autowired
    private ICartService cartService;

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

    @GetMapping("/huyhang")
    public ResponseEntity<Integer> huyHang() {
        Integer huy = cartService.displayHuy();
        return new ResponseEntity<>(huy, HttpStatus.OK);
    }

    @GetMapping("/trahang")
    public ResponseEntity<Integer> traHang() {
        Integer tra = cartService.displayTra();
        return new ResponseEntity<>(tra, HttpStatus.OK);
    }

    @GetMapping("/banhang")
    public ResponseEntity<Integer> banHang() {
        System.out.println(1);
        Integer ban = cartService.displayBan();
        return new ResponseEntity<>(ban, HttpStatus.OK);
    }

    @GetMapping("/nhaphang")
    public ResponseEntity<Integer> nhapHang() {
        Integer nhap = importService.displayNhap();
        return new ResponseEntity<>(nhap, HttpStatus.OK);
    }

    @GetMapping("/luongNV")
    public ResponseEntity<Integer> luongNV() {
        Integer luong = salaryService.displayLuong();
        return new ResponseEntity<>(luong, HttpStatus.OK);
    }

    // HuyenNTD - Thong ke khach hang tiem nang
    @GetMapping("/list/customer")
    public ResponseEntity<List<String>> getAll() {
        List<String> list = statisticService.findAllStatisticCustomer();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/list/customers")
    public ResponseEntity<String[]> getAllCustomer() {
        String[] data = customerRepository.findAllPotentialCustomer();
        return new ResponseEntity<String[]>(data, HttpStatus.OK);
    }

    @GetMapping("/search/customer")
    public ResponseEntity<List<String>> searchPotentialCustomers(@RequestParam String fromMonth, @RequestParam String toMonth,
                                                                 @RequestParam String year) {
        List<String> list = statisticService.searchForPotentialCustomers(fromMonth, toMonth, year);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
