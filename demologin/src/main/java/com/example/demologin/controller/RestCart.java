package com.example.demologin.controller;

import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.cart.Cart;
import com.example.demologin.entity.cart.CartBook;
import com.example.demologin.entity.cart.CartStatus;
import com.example.demologin.repository.CartBookRepository;
import com.example.demologin.repository.CartRepository;
import com.example.demologin.repository.CartStatusRepository;
import com.example.demologin.service.ICartBookService;
import com.example.demologin.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/cart")
public class RestCart {

    @Autowired
    private CartStatusRepository cartStatusRepository;

//    @Autowired
//    private ICustomerService customerService;

    @Autowired
    private ICartBookService cartBookService;

    @Autowired
    private CartBookRepository cartBookRepository;

    @Autowired
    private ICartService cartService;

    @Autowired
    private CartRepository cartRepository;


    @GetMapping("/list/{id}")
    public ResponseEntity<List<Cart>> getCartByStatus(@PathVariable("id") Long id) {
        try {
            List<Cart> carts = cartService.getAllCartWithUser(id);
            if (carts.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/addBookIntoCart/{id}")
    public ResponseEntity<?> insertBookCart(@PathVariable("id") Long id ,@RequestBody Book book) {

        Optional<Cart> existCartBydId = cartService.existsByCartId(id, book.getBookName());
        if (existCartBydId.get() != null){
            cartRepository.updateQuantityCart(book.getBookQuantityBuy()+1,id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            long millis=System.currentTimeMillis();
            Date date = new Date(millis);
            cartService.insertBookIntoCart(book.getBookName(), book.getBookImage(),book.getBookPublisher(),book.getBookTranslator(),
                    book.getBookPrice(),book.getBookQuantityBuy(),book.getBookPrice()*book.getBookQuantityBuy(),date,(long) 1,
                    id,book.getBookPromotionId().getPromotionPercent());

            return new ResponseEntity<>(HttpStatus.CREATED);
        }


//        try {
//            Long[] bookId = cartBookRepository.getCartBookByIdAndStatus();
//            CartBook cartBook0 = cartBookRepository.findCartBookByStatusAndFlagAndBookId1(book.getBookId());
//            for (int i = 0 ; i< bookId.length;i++) {
//                if (bookId[i] == book.getBookId()) {
//                    if (book.getBookQuantityBuy() > cartBook0.getCartId().getCartQuantity()) {
//                        cartService.updateCart(cartBook0.getCartId().getCartQuantity()+1,(cartBook0.getCartId().getCartQuantity()+1)*cartBook0.getBookId().getBookPrice(),cartBook0.getCartId().getCartId());
//                    }
//                    break;
//                } else {
//                    if (i == bookId.length-1) {
//                        CartStatus cartStatus = cartStatusRepository.getCartStatus();
//                        Cart cart = new Cart(1,book.getBookPrice(),cartStatus);
//                        cartRepository.save(cart);
//                        CartBook cartBook = new CartBook(cart,false,book);
//                        cartBookRepository.save(cartBook);
//                        return new ResponseEntity<>(HttpStatus.CREATED);
//                    }
//                }
//            }
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable("cartId") Long cartId) {
        try {
            cartService.deleteCartByCartIdAndAccountId(cartId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/findCartIdExists/{id}")
//    public ResponseEntity<?> findCartIdExists(@RequestBody Book book) {
//        try {
//            Boolean exist = cartService.existsAllByBookName(book.getBookName());
//            if (exist){
//                cartRepository.updateQuantityCart(book.getBookQuantityBuy()+1,book.getBookName());
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
}
