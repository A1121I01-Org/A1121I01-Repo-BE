package module6.backend.controller;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.cart.CartMaterial;
import module6.backend.entity.customer.Customer;
import module6.backend.repository.ICartRepository;
import module6.backend.repository.ICustomerRepository;
import module6.backend.service.ICustomerService;
import module6.backend.service.ICustomerTypeService;
import module6.backend.service.Impl.CartMaterialServiceImpl;
import module6.backend.service.Impl.CartServiceImpl;
import module6.backend.service.Impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private ICustomerTypeService customerTypeService;

    @Autowired
    private CartMaterialServiceImpl cartMaterialService;

    @Autowired
    private ICartRepository cartRepository;

}
