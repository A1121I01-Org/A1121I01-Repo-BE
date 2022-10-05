package module6.backend.service;

import module6.backend.entity.employee.Employee;

import java.util.Optional;

public interface IEmployeeService {
    Optional<Employee> findEmployeeById(Long id);
}
