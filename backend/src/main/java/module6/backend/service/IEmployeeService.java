package module6.backend.service;

import module6.backend.entity.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    Optional<Employee> findById(Long id);

    List<Employee> getAllEmployeeWithPagination(int index);


    void deleteEmployeeById(Long id1, Long id2);

    Optional<Employee> findEmployeeById(Long id);

    List<Employee> getAllEmployee();
}
