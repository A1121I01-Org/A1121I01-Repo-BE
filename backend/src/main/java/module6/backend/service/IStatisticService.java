package module6.backend.service;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IStatisticService {

    // HuyenNTD - Thong ke khach hang tiem nang

    List<String> findAllStatisticCustomer();
    String[] searchForPotentialCustomers(String fromMonth, String toMonth, String year);
//    String[] chartCustomer();
}
