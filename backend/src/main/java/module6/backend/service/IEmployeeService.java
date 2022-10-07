package module6.backend.service;

import module6.backend.entity.employee.Employee;

import java.time.LocalDate;

public interface IEmployeeService {
    Employee findEmployeeById(Long employeeId);
    void updateEmployee(String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Long employeeId);
}
