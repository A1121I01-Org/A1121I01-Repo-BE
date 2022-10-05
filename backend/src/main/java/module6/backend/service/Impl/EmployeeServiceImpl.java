package module6.backend.service.Impl;

import module6.backend.entity.employee.Employee;
import module6.backend.repository.IEmployeeRepository;
import module6.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }
}
