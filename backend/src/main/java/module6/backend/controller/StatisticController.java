package module6.backend.controller;

import module6.backend.entity.customer.Customer;
import module6.backend.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@CrossOrigin("*")
@RequestMapping("api/statistic")
public class StatisticController {
    @Autowired
    private IStatisticService statisticService;

    // HuyenNTD - Thong ke khach hang tiem nang
    public ResponseEntity<Page<Customer>> findForPotentialCustomers (@RequestParam LocalDate cartDateCreate, @RequestParam int pageable) {
        Page<Customer> customerPage = statisticService.findForPotentialCustomers(cartDateCreate, pageable);
        if(customerPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerPage, HttpStatus.OK);
    }

}
