package module6.backend.service;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStatisticService {

//    KinPBH - Thong ke tai chinh

    Integer displayHuy();

    Integer searchHuy(@Param("month") String month , @Param("year") String year);

    Integer displayTra();

    Integer searchTra(@Param("month") String month , @Param("year") String year);

    Integer displayBan();

    Integer searchBan(@Param("month") String month , @Param("year") String year);

    Integer displayNhap();

    Integer searchNhap(@Param("month") String month , @Param("year") String year);

//    Integer displayLuong();

    // HuyenNTD - Thong ke khach hang tiem nang

    List<String> findAllStatisticCustomer();
    List<String> searchForPotentialCustomers(String fromMonth, String toMonth, String year);
}
