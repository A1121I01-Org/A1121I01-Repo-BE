package module6.backend.controller;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.customer.Customer;
import module6.backend.repository.ICustomerRepository;
import module6.backend.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ICustomerRepository customerRepository;

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

    @GetMapping("/search/customer")
    public ResponseEntity<List<String>> searchPotentialCustomers(@RequestParam String fromMonth, @RequestParam String toMonth,
                                                                 @RequestParam String year) {
        List<String> list = statisticService.searchForPotentialCustomers(fromMonth, toMonth, year);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
