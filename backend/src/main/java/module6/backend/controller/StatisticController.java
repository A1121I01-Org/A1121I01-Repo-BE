package module6.backend.controller;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.customer.Customer;
import module6.backend.repository.ICustomerRepository;
import module6.backend.service.IStatisticService;
import module6.backend.service.Impl.pdf.PDFStatisticCustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/statistic")
public class StatisticController {
    @Autowired
    private IStatisticService statisticService;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private PDFStatisticCustomerImpl pdfStatisticCustomer;

    // HuyenNTD - Thong ke khach hang tiem nang
    @GetMapping("/list/customer")
    public ResponseEntity<List<String>> getAll() {
        List<String> list = statisticService.findAllStatisticCustomer();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/list/customers")
    public ResponseEntity<String[]> data1() {
        String[] data = customerRepository.findAllPotentialCustomer();
        return new ResponseEntity<String[]>(data, HttpStatus.OK);
    }

    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> generatePDF() throws IOException {
        String[] data = customerRepository.findAllPotentialCustomer();
        ByteArrayInputStream bais = pdfStatisticCustomer.export(data);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","inline;filename=cart.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }


    @GetMapping("/search/customer")
    public ResponseEntity<String[]> searchPotentialCustomers(@RequestParam String fromMonth, @RequestParam String toMonth,
                                                                 @RequestParam String year) {
        String[] list = statisticService.searchForPotentialCustomers(fromMonth, toMonth, year);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

//    @GetMapping("/chart")
//    public ResponseEntity<String[]>chart(){
//        String[] data = statisticService.chartCustomer();
//        return new ResponseEntity<>(data, HttpStatus.OK);
//    }

}
