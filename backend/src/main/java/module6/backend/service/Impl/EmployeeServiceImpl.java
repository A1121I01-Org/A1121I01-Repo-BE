package module6.backend.service.Impl;

import module6.backend.entity.employee.Employee;
import module6.backend.repository.IEmployeeRepository;
import module6.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.time.LocalDate;


@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public void updateEmployee(String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Long employeeId) {
        employeeRepository.updateEmployee(employeeName,employeeAvatar,employeeDateOfBirth,employeeGender,employeeAddress,employeePhone, employeeId);

    }
}

