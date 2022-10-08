package module6.backend.service;

import module6.backend.entity.customer.Customer;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IStatisticService {

    // HuyenNTD - Thong ke khach hang tiem nang
    Page<Customer> findForPotentialCustomers(LocalDate cartDateCreate, int pageable);
}
