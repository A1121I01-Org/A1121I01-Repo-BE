package module6.backend.service;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.customer.Customer;

import java.util.List;

public interface ICartService {
    List<Cart> findByCartStatusId();

    void updateCartStatusId(List<Cart> carts);

    void deleteCartByCartId(Long cartId);
}
