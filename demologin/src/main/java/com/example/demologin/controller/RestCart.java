package com.example.demologin.controller;

import com.example.demologin.entity.Account;
import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.cart.Cart;
import com.example.demologin.entity.cart.CartBook;
import com.example.demologin.entity.cart.CartStatus;
import com.example.demologin.repository.CartBookRepository;
import com.example.demologin.repository.CartRepository;
import com.example.demologin.repository.CartStatusRepository;
import com.example.demologin.service.IAccountService;
import com.example.demologin.service.IBookService;
import com.example.demologin.service.ICartBookService;
import com.example.demologin.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/cart")
public class RestCart {

    @Autowired
    private ICartBookService cartBookService;

    @Autowired
    private CartBookRepository cartBookRepository;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired private IBookService bookService;


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
        List<CartBook> cartBookList = cartBookService.findAllCartBook(id);
        Optional<Cart> existCartBydId = cartService.existsByCartId(id, book.getBookName());
        Optional<Book> existBookById = bookService.existsByBookId(book.getBookId());
        for (CartBook cartBook: cartBookList) {
            if (cartBook.getBookId().getBookId() == book.getBookId()) {
                if (existCartBydId.isPresent()){
                    if ( existBookById.get().getBookQuantity() > existCartBydId.get().getCartQuantity()){
                        Double totalMoney = existCartBydId.get().getBookPrice()*(existCartBydId.get().getCartQuantity()+1);
                        cartService.updateQuantityCart(existCartBydId.get().getCartQuantity()+1,totalMoney,id,book.getBookName());
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        String message = "Số lượng sách thêm đã lớn hơn số lượng trong kho hiện tại. Vui lòng nhập lại";
                        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
                    }

                }
            }
        }
        long millis=System.currentTimeMillis();
//                Date date = new Date(millis);
        LocalDate localDate = LocalDate.now();
        Account account = accountService.findAccountByAccountId(id);
        Double total = book.getBookPrice()*book.getBookQuantityBuy();
        Cart cart = new Cart(book.getBookName(),book.getBookImage(),book.getBookPublisher(),book.getBookTranslator(),
                book.getBookPrice(),book.getBookQuantityBuy(),total,localDate,account,book.getBookPromotionId().getPromotionPercent());
        CartBook cartBook1 = new CartBook(book,cart);
        cartRepository.save(cart);
        cartBookRepository.save(cartBook1);
//                cartService.insertBookIntoCart(book.getBookName(), book.getBookImage(),book.getBookPublisher(),book.getBookTranslator(),
//                        book.getBookPrice(),book.getBookQuantityBuy(),book.getBookPrice()*book.getBookQuantityBuy(),date,(long) 1,
//                        id,book.getBookPromotionId().getPromotionPercent());

        return new ResponseEntity<>(HttpStatus.CREATED);

//
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

    @PostMapping("/changeQuantityCart")
    public ResponseEntity<?> changeQuantityCart(@RequestParam("quantity") Integer quantity,
                                                @RequestParam("money") Double money,@RequestParam("id") Long id) {
        List<CartBook> cartBookList = cartBookService.findAllCartBookByCartId(id);
        Optional<Cart> existCartBydId = cartService.findCartByCartId(id);
//        Optional<Book> existBookById = bookService.existsByBookId(book.getBookId());

        if (existCartBydId.isPresent()){
            for (CartBook cartBook: cartBookList) {
                if (cartBook.getBookId().getBookQuantity() < quantity) {
                    String message = "Số lượng sách thêm đã lớn hơn số lượng trong kho hiện tại. Vui lòng nhập lại";
                    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
                } else {
                    cartService.updateCart(quantity,money,id);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
