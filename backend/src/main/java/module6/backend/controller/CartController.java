package module6.backend.controller;

import module6.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ICartStatusService cartStatusService;

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
        Integer ban = cartService.displayBan();
        return new ResponseEntity<>(ban, HttpStatus.OK);
    }

}
