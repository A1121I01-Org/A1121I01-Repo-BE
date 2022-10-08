package module6.backend.service.Impl;

import module6.backend.entity.account.Role;
import module6.backend.entity.employee.Employee;
import module6.backend.repository.IAccountRepository;
import module6.backend.repository.IEmployeeRepository;
import module6.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    //NhiVP code tim employee by code
    @Override
    public Employee findEmployeeByCode(String code) {
        return employeeRepository.findEmployeeByCode(code);
    }

    //NhiVP code tim employee da co account
    @Override
    public Employee findExistEmployeeHasAccount(String code) {
        return employeeRepository.findExistEmployeeHasAccount(code);
    }

    //NhiVP code tim employee chua co account
    @Override
    public Employee findExistEmployeeDontHasAccount(String code) {
        return employeeRepository.findExistEmployeeDontHasAccount(code);
    }
}
