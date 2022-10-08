package module6.backend.controller;

import module6.backend.entity.customer.Customer;
import module6.backend.service.ICustomerService;
import module6.backend.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerTypeService customerTypeService;

    @GetMapping("customer-findById/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findCustomerById(id).get(), HttpStatus.OK);
    }

    @PostMapping("customer-create")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer.getCustomerName(),customer.getCustomerCode(),customer.getCustomerAvatar(),customer.getCustomerAddress(),customer.getCustomerPhone(),customer.getCustomerEmail(),customer.getCustomerTypeId().getCustomerTypeId());
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PatchMapping("update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findCustomerById(customer.getCustomerId());
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.updateCustomer(customer.getCustomerName(), customer.getCustomerCode(), customer.getCustomerAvatar(), customer.getCustomerAddress(), customer.getCustomerPhone(), customer.getCustomerEmail(), customer.getCustomerTypeId().getCustomerTypeId(), customer.getCustomerId());
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
}
