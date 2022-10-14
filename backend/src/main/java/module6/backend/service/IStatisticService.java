package module6.backend.service;

import java.util.List;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.customer.Customer;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IStatisticService {
    //HoangTND - Statistic Material
    List<String> findAllStatisticMaterial();
    List<String> searchStatisticMaterial(String fromDate, String toDate);


    // HuyenNTD - Thong ke khach hang tiem nang

    List<String> findAllStatisticCustomer();
    List<String> searchForPotentialCustomers(String fromMonth, String toMonth, String year);
}
