package module6.backend.service;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.customer.Customer;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IStatisticService {

    // HuyenNTD - Thong ke khach hang tiem nang

    List<String> findAllStatisticCustomer();
    List<String> searchForPotentialCustomers(String fromMonth, String toMonth, String year);
}
