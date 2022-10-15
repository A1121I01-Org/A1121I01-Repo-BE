package module6.backend.service.Impl;

import module6.backend.entity.employee.Employee;
import module6.backend.repository.IEmployeeRepository;
import module6.backend.service.IEmployeeService;
import module6.backend.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

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
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployeeWithPagination(int index) {
        return employeeRepository.getAllEmployeeWithPagination(index);
    }

    @Override
    public void deleteEmployeeById(Long id1, Long id2) {
        employeeRepository.deleteEmployeeById(id1, id2);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }

}
