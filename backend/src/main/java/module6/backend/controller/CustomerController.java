package module6.backend.controller;

import module6.backend.entity.customer.Customer;
<<<<<<< HEAD
=======
import module6.backend.entity.customer.CustomerType;
>>>>>>> origin/customer-manager
import module6.backend.service.ICustomerService;
import module6.backend.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;

<<<<<<< HEAD
import org.springframework.data.domain.Pageable;
=======
>>>>>>> origin/customer-manager
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> origin/customer-manager

@RestController
@CrossOrigin("*")
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerTypeService customerTypeService;

<<<<<<< HEAD

    //    lay danh sach khach hang
    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customerList = this.customerService.getAllCustomer();
        if (customerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    /// get list phan trang
    @GetMapping("/pagination/{index}")
    public ResponseEntity<List<Customer>> getAllCustomer(@PathVariable("index") int index) {
        List<Customer> customers = customerService.getAllCustomerWithPagination(index);
        if (customers.isEmpty()) {
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    //    chinh sua thong tin khach hang
    @PatchMapping(value = "/api/customer/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id")  Long id, @RequestBody Customer customer){
        customerService.updateCustomer(customer.getCustomerName(), customer.getCustomerCode(),customer.getCustomerAvatar(),customer.getCustomerAddress(), customer.getCustomerPhone(), customer.getCustomerEmail(),customer.getCustomerTypeId().getCustomerTypeId(), customer.getCustomerId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

=======
    //  SonLH  Tìm kiếm khách hàng theo id
    @GetMapping("/detail/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findCustomerById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    //  SonLH lấy danh dách loại khách hàng
    @GetMapping("/customer-type")
    public ResponseEntity<List<CustomerType>> findAllCustomerType() {
        List<CustomerType> customerTypes = customerTypeService.findAllCustomerType();
        if (customerTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerTypes, HttpStatus.OK);
    }
>>>>>>> origin/customer-manager
}
