package module6.backend.controller;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.customer.Customer;
import module6.backend.repository.ICustomerRepository;
import module6.backend.repository.IMaterialRepository;
import module6.backend.service.*;
import module6.backend.service.Impl.PDFGeneratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * CartController
 *
 * <p>Version 1.0
 *
 * <p>Date: 07-10-2022
 *
 * <p>Copyright
 *
 * <p>Modification Logs:
 * DATE             AUTHOR      DESCRIPTION
 * ----------------------------------------
 * 07-10-2022       SyNV        Create
 */

@RestController
@CrossOrigin("*")
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private IMaterialRepository materialRepository;
    @Autowired
    private ICartStatusService cartStatusService;

    @Autowired
    private PDFGeneratorServiceImpl pdfGeneratorService;

    @Autowired
    private ICustomerRepository customerRepository;

    /** Get list cart by status id - SyNV. */
    @GetMapping("/list")
    public ResponseEntity<List<Cart>> getCartByStatus() {
        try {
            List<Cart> carts = cartService.findByCartStatusId();
            if (carts.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listCustomer")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    /** Delete cart by cart id - SyNV. */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long cartId) {
        try {
            cartService.deleteCartByCartId(cartId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /** Update cart status by cart id - SyNV. */
    @PatchMapping("/update")
    public ResponseEntity<?> updateCart(@RequestBody List<Cart> cart ) {
        try{
            cartService.updateCartStatusId(cart);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /** Export file PDF for bill payment - SyNV. */
    @PostMapping("/pdf")
    public ResponseEntity<InputStreamResource> generatePDF(@RequestBody List<Cart> cart) throws IOException {
        ByteArrayInputStream bais = pdfGeneratorService.export(cart);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","inline;filename=cart.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }
}


