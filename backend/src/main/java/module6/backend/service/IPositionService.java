package module6.backend.service;

import module6.backend.entity.employee.Position;

import java.util.List;

public interface IPositionService {
    List<Position> findPositionNotManager();
}
