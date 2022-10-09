package module6.backend.controller;

import module6.backend.entity.customer.Customer;
import module6.backend.entity.customer.CustomerType;
import module6.backend.service.ICustomerService;
import module6.backend.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerTypeService customerTypeService;


    //  HieuNT  get list customer  not pagination
    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customerList = this.customerService.getAllCustomer();
        if (customerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    /// HieuNT get list with pagination
    @GetMapping("/customer-pagination/{index}")
    public ResponseEntity<List<Customer>> getAllCustomer(@PathVariable("index") int index) {
        List<Customer> customers = customerService.getAllCustomerWithPagination(index);
        if (customers.isEmpty()) {
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }


    //  HieuNT  delete customer
    @DeleteMapping("/customer-delete/{id}")
    public ResponseEntity<Customer> delete(@PathVariable("id") Long id) {
        Optional<Customer> customerOptional = customerService.findCustomerById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomerById(-id, id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.NO_CONTENT);
    }

    //   HieuNT search customer by name and phone
    @GetMapping(value = "/search-customer")
    public ResponseEntity<List<Customer>> searchCustomerByNameAndPhone(@RequestParam("name") String name, @RequestParam("phone") String phone) {
        List<Customer> isCustomerExist = customerService.searchCustomerByNameAndPhone(name,phone);


        if (isCustomerExist != null){
            return new ResponseEntity<>(isCustomerExist,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


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

}