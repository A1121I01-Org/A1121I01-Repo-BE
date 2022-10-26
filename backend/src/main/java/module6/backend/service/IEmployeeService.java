package module6.backend.service;

import module6.backend.entity.employee.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Optional<Employee> findEmployeeById(Long id);

    void updateEmployee(String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Long employeeId);

    List<Employee> getAllEmployeeWithPagination(int index);

    List<Employee> searchEmployeeByName( String name);

    void deleteEmployeeById(Long id1, Long id2);

    List<Employee> getAllEmployee();
}
