package module6.backend.service.Impl;

import module6.backend.entity.customer.Customer;
import module6.backend.repository.ICustomerRepository;
import module6.backend.repository.IEmployeeRepository;
import module6.backend.repository.IMaterialRepository;
import module6.backend.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
    public Page<Customer> findForPotentialCustomers(LocalDate cartDateCreate, int pageable) {
        return customerRepository.findForPotentialCustomers(cartDateCreate);
    }
}
