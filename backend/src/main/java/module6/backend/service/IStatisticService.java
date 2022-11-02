package module6.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IStatisticService {
    //HoangTND - Statistic Material
    List<String> findAllStatisticMaterial();

    List<String> searchStatisticMaterial(String fromDate, String toDate);

    String[] chartStatisticMaterial();


//    KinPBH - Thong ke tai chinh

    Integer displayHuy();

    Integer searchHuy(@Param("month") String month, @Param("year") String year);

    Integer displayTra();

    Integer searchTra(@Param("month") String month, @Param("year") String year);

    Integer displayBan();

    Integer searchBan(@Param("month") String month, @Param("year") String year);

    Integer displayNhap();

    Integer searchNhap(@Param("month") String month, @Param("year") String year);

    List<String> getYear();



//    Integer displayLuong();

    // HuyenNTD - Thong ke khach hang tiem nang
    String[] searchForPotentialCustomers(String fromMonth, String toMonth, String year);

    List<String> findAllStatisticCustomer();

    List<String> getYearSearch();
//    String[] chartCustomer();
}
