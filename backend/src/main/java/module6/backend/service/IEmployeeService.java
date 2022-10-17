package module6.backend.service;

import module6.backend.entity.employee.Employee;
import module6.backend.entity.employee.Position;

import java.time.LocalDate;
import java.util.List;

public interface IEmployeeService {

    Employee findById(Long id);
    void adminUpdateEmployee(String employeeName, String employeeCode, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Double employeeSalary, Long employeePositionId, Long employeeId);

    void saveEmployee(String employeeCode, String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Double employeeSalary, Long employeePositionId);

    List<Position> getAllPosition();

}
