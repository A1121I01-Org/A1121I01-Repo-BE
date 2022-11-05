package module6.backend.service.Impl;

import module6.backend.entity.Import;
import module6.backend.entity.employee.Employee;
import module6.backend.entity.employee.Position;
import module6.backend.repository.IEmployeeRepository;
import module6.backend.repository.IPositionRepository;
import module6.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements IEmployeeService {



    @Autowired
    private IEmployeeRepository employeeRepository;
    @Autowired
    private IPositionRepository positionRepository;


    @Override
    public void adminUpdateEmployee( String employeeCode,String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Double employeeSalary, Long employeePositionId, Long employeeId) {
        employeeRepository.adminUpdateEmployee( employeeCode, employeeName,employeeAvatar, employeeDateOfBirth, employeeGender, employeeAddress, employeePhone, employeeSalary, employeePositionId, employeeId);
    }

    @Override
    public Page<Employee> findAllEmployee(Pageable page) {
        return employeeRepository.findAllEmployee(page);
    }

    @Override
    public void deleteEmployeeById(long l, boolean b, Long id) {
        employeeRepository.deleteEmployeeById(l, b, id);

    }


    @Override
    public void saveEmployee(String employeeCode, String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Double employeeSalary, Long employeePositionId) {
        employeeRepository.saveEmployee(employeeCode, employeeName, employeeAvatar, employeeDateOfBirth, employeeGender, employeeAddress, employeePhone, employeeSalary, employeePositionId);
    }

    @Override
    public List<Position> getAllPosition() {
        return positionRepository.findAllPosition();
    }

    @Override
    public Map<String, String> validateEmployee(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        if(bindingResult.hasErrors()) {
            if(bindingResult.hasFieldErrors("employeeName")) {
                errors.put("employeeName", "Họ tên chưa đúng định dạng");
            }
            if(bindingResult.hasFieldErrors("employeeAddress")) {
                errors.put("employeeAddress", "Địa chỉ vượt quá độ dài cho phép");
            }
            if(bindingResult.hasFieldErrors("employeePhone")) {
                errors.put("employeePhone", "Số điện thoại không đúng định dạng");
            }
            if(bindingResult.hasFieldErrors("employeeDateOfBirth")) {
                errors.put("employeeDateOfBirth", "Ngày sinh không đúng định dạng hoặc tuổi nhỏ hơn 18");
            }
        }
        return errors;
    }

    @Override
    public Optional<Employee> findEmployeeByAccountId(Long accountId) {
        return this.employeeRepository.findEmployeeByAccountId(accountId);
    }


    @Override
    public void updateEmployee(String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Long employeeId) {
        employeeRepository.updateEmployee(employeeName, employeeAvatar, employeeDateOfBirth, employeeGender, employeeAddress, employeePhone, employeeId);

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

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployeeWithPagination(int index) {
        return employeeRepository.getAllEmployeeWithPagination(index);
    }

    @Override
    public Page<Employee> searchEmployeeByName(String name, Pageable page) {
        return employeeRepository.searchEmployeeByName(name, page);
    }

//    @Override
//    public List<Employee> searchEmployeeByName(String name) {
//        return employeeRepository.searchEmployeeByName(name);
//    }


    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }

}
