package module6.backend.service;

import java.util.List;

public interface IStatisticService {
    //HoangTND - Statistic Material
    List<String> findAllStatisticMaterial();
    List<String> searchStatisticMaterial(String fromDate, String toDate);

}
