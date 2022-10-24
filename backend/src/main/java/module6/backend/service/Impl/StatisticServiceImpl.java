package module6.backend.service.Impl;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.customer.Customer;
import module6.backend.repository.ICustomerRepository;
import module6.backend.repository.IEmployeeRepository;
import module6.backend.repository.IMaterialRepository;
import module6.backend.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticServiceImpl implements IStatisticService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IMaterialRepository materialRepository;

    // HuyenNTD - Thong ke khach hang tiem nang
    @Override
    public List<String> findAllStatisticCustomer() {
        return customerRepository.findAllCustomer();
    }

    @Override
    public String[] searchForPotentialCustomers(String fromMonth, String toMonth, String year) {
        return customerRepository.findForPotentialCustomers(fromMonth, toMonth, year);
    }

//    @Override
//    public String[] chartCustomer() {
//        return customerRepository.chartCustomer();
//    }
    // abc
}
