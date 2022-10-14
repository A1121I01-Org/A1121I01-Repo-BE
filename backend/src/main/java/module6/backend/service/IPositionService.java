package module6.backend.service;

import module6.backend.entity.employee.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPositionService {
    List<Position> findAllEmployeePosition();
}
