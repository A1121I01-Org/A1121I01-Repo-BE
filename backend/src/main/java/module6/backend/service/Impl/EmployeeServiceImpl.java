package module6.backend.service.Impl;

import module6.backend.entity.employee.Employee;
import module6.backend.repository.IEmployeeRepository;
import module6.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


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

    //NhiVP code tim employee theo code
    @Override
    public Employee findEmployeeByCode(String code) {
        return employeeRepository.findEmployeeByCode(code);
    }

    //NhiVP code lấy danh sách mã nhân viên đã có tài khoản
    @Override
    public List<String> findAllEmployeeHasAccount() {
        return employeeRepository.findAllEmployeeHasAccount();
    }

    //NhiVP code lấy danh sách mã nhân viên chưa có tài khoản
    @Override
    public List<String> findAllEmployeeDontHasAccount() {
        return employeeRepository.findAllEmployeeDontHasAccount();
    }

    @Override
    public List<String> findAllPhone() {
        return employeeRepository.findAllPhone();
    }
}

