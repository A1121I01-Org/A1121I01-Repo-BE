package module6.backend.service.Impl;

import module6.backend.entity.employee.Employee;
import module6.backend.service.IEmployeeService;
import module6.backend.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public Optional<Employee> findById(Long id) {
        return iEmployeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployeeWithPagination(int index) {
        return iEmployeeRepository.getAllEmployeeWithPagination(index);
    }

    @Override
    public void deleteEmployeeById(Long id1, Long id2) {
        iEmployeeRepository.deleteEmployeeById(id1, id2);
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return iEmployeeRepository.findEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return iEmployeeRepository.getAllEmployee();
    }

}
