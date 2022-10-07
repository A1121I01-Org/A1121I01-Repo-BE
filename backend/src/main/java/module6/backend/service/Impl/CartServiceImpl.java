package module6.backend.service.Impl;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.cart.DataMail;
import module6.backend.entity.customer.Customer;
import module6.backend.repository.ICartRepository;
import module6.backend.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private DataMailServiceImpl dataMailService;

    private Double totalMoneyBill = 0.0;
    private String emailCustomer;

    @Override
    public List<Cart> findByCartStatusId() {
        return cartRepository.findByCartStatusId();
    }


    @Override
    public void updateCartStatusId(List<Cart> carts ) {
       for(Cart cart : carts){

          totalMoneyBill = totalMoneyBill + cart.getCartTotalMoney();
//          emailCustomer = cart.getCartCustomerId().getCustomerEmail();
          cartRepository.updateCartStatusId(cart.getCartId());
       }
        try {
            DataMail dataMail = new DataMail();
            dataMail.setTo("nguyenvansy091201@gmail.com");
            dataMail.setSubject("Hóa đơn thanh toán");
            Map<String, Object> props = new HashMap<>();
            props.put("cartList", carts);
            props.put("totalMoneyBill", totalMoneyBill);
            dataMail.setProps(props);

            dataMailService.sendMail(dataMail,"Mail");
            System.out.println("Send Mail pass");
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
    }

    @Override
    public void deleteCartByCartId(Long cartId) {
        cartRepository.deleteCartByCartId(cartId);
    }
}
