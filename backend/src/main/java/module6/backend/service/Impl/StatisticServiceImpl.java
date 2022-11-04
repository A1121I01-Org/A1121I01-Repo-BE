package module6.backend.service.Impl;

import module6.backend.repository.ICartRepository;
import module6.backend.repository.ICustomerRepository;
import module6.backend.repository.IImportRepository;
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
    private IMaterialRepository materialRepository;

    @Autowired
    private IImportRepository importRepository;

    @Autowired
    private ICartRepository cartRepository;

    //HoangTND - Statistic Material
    @Override
    public List<String> findAllStatisticMaterial() {
        return materialRepository.findAllStatisticMaterial();
    }

    @Override
    public List<String> searchStatisticMaterial(String fromDate, String toDate) {
        return materialRepository.searchStatisticMaterial(fromDate, toDate);
    }

    @Override
    public String[] chartStatisticMaterial() {
        return materialRepository.chartStatisticMaterial();
    }

//    KimPBH - Thong ke tai chinh
//    @Autowired
//    private ISalaryRepository salaryRepository;

//    @Override
//    public Integer displayLuong() {
//        return salaryRepository.luong();
//    }

    @Override
    public Integer displayHuy() {
        return cartRepository.huy();
    }

    @Override
    public Integer searchHuy(String month, String year) {
        return cartRepository.searchhuy(month, year);
    }

    @Override
    public Integer displayTra() {
        return cartRepository.tra();
    }

    @Override
    public Integer searchTra(String month, String year) {
        return cartRepository.searchtra(month, year);
    }

    @Override
    public Integer displayBan() {
        return cartRepository.ban();
    }

    @Override
    public Integer searchBan(String month, String year) {
        return cartRepository.searchban(month, year);
    }

    @Override
    public Integer displayNhap() {
        return importRepository.nhap();
    }

    @Override
    public Integer searchNhap(String month, String year) {
        return importRepository.searchnhap(month, year);
    }

    @Override
    public List<String> getYear() {
        return cartRepository.getYear();
    }

    // HuyenNTD - Thong ke khach hang tiem nang
    @Override
    public List<String> findAllStatisticCustomer() {
        return customerRepository.findAllCustomer();
    }

    @Override
    public List<String> getYearSearch() {
        return customerRepository.getYearSearch();
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
