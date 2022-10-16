package module6.backend.controller;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.cart.CartMaterial;
import module6.backend.entity.cart.CartStatus;
import module6.backend.entity.customer.Customer;
import module6.backend.entity.material.Material;
import module6.backend.repository.ICartMaterialRepository;
import module6.backend.repository.ICartRepository;
import module6.backend.repository.ICartStatusRepository;
import module6.backend.repository.IMaterialRepository;
import module6.backend.service.*;
import module6.backend.service.Impl.CartMaterialServiceImpl;
import module6.backend.service.Impl.PDFCartGeneratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private CartMaterialServiceImpl cartMaterialService;

    @Autowired
    private PDFCartGeneratorServiceImpl pdfGeneratorService;

    @Autowired
    private ICartStatusRepository cartStatusRepository;

    @Autowired
    private ICartMaterialRepository cartMaterialRepository;

    @Autowired
    private IMaterialRepository materialRepository;

    @Autowired
    private ICartRepository cartRepository;

    /** Get list cart by status id - SyNV. */
    @GetMapping("/list")
    public ResponseEntity<List<CartMaterial>> getCartByStatus() {
        try {
            List<CartMaterial> carts = cartMaterialService.findCartMaterialByStatusAndFlag();
            if (carts.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list2")
    public ResponseEntity<List<Material>> get() {
        try {
            List<Material> materials = materialRepository.findAll();
            return new ResponseEntity<>(materials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /** Delete cart by cart id - SyNV. */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long cartId) {
        try {
            cartMaterialService.deleteCartByCartId(cartId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /** Delete cart by list cart id - SyNV. */
    @PostMapping("/delete")
    public ResponseEntity<?> deleteCart2(@RequestBody Long[] cartId) {
        try {
            cartMaterialService.deleteCartByCartId2(cartId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**Check cart - SyNV. */
    @PostMapping("/checkCart")
    public ResponseEntity<List<CartMaterial>> checkCart(@RequestBody Long[] cartId) {
        try {
            List<CartMaterial> cartMaterials = cartMaterialService.findCartMaterialByStatusAndFlagAndId(cartId);
            return new ResponseEntity<>(cartMaterials,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /** Update cart status by cart id , quantity , total money - SyNV. */
    @GetMapping("/update")
    public ResponseEntity<?> updateCart1(@RequestParam("quantity") Integer quantity,
                                         @RequestParam("money") Integer money,@RequestParam("id") Long id) {
        try{
            cartService.updateCart(quantity,money,id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /** Export file PDF for bill payment - SyNV. */
    @PostMapping("/pdf")
    public ResponseEntity<InputStreamResource> generatePDF(@RequestBody Long[] cartId) throws IOException {
        List<CartMaterial> carts = cartMaterialService.findCartMaterialByFlagAndId(cartId);
        CartMaterial cartMaterial = cartMaterialService.getCartMaterialById(cartId[0]);
        Cart cart = cartMaterial.getCartId();
        ByteArrayInputStream bais = pdfGeneratorService.export(carts,cart);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","inline;filename=cart.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }

    /** Add customer and insert customer to cart - SyNV. */
    @PostMapping("/insert/{cartId}")
    public ResponseEntity<?> insertMaterialToCart(@RequestBody Customer customer, @PathVariable("cartId") Long[] cartId) {
        try {
            customer.setCustomerAvatar("img");
            String customerCode = cartService.randomCustomerCode();
            Long customerType = cartService.getTypeId();
            customerService.createCustomer(customer.getCustomerName(),customerCode,customer.getCustomerAvatar(),customer.getCustomerAddress(),customer.getCustomerPhone(),customer.getCustomerEmail(),customerType);
            Customer customer1 = customerService.getCustomerByCode(customerCode);
            List<CartMaterial> carts = cartMaterialService.findCartMaterialByStatusAndFlagAndId(cartId);
            String cartCode = cartService.randomCartCode();
            for (CartMaterial cartMaterial: carts) {
                cartService.updateCartStatusAndCustomer(customer1.getCustomerId(),cartMaterial.getCartId().getCartId(),java.time.LocalDate.now(),cartCode);
            }
            cartService.sendEmail(cartId,customer1);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /** Add material to cart - SyNV. */
    @PostMapping("/insertMaterial")
    public ResponseEntity<?> insertMaterialCart(@RequestBody Material material) {
        try {
            CartStatus cartStatus = cartStatusRepository.getCartStatus();
            Cart cart = new Cart(1,material.getMaterialPrice(),cartStatus);
            cartRepository.save(cart);
            CartMaterial cartMaterial = new CartMaterial(cart,false,material);
            cartMaterialRepository.save(cartMaterial);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}


