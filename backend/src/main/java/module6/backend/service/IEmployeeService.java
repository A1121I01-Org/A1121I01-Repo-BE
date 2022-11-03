package module6.backend.service;

import module6.backend.entity.Import;
import module6.backend.entity.employee.Employee;
import module6.backend.entity.employee.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IEmployeeService {
    Optional<Employee> findEmployeeById(Long id);

    void updateEmployee(String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Long employeeId);

    //NhiVP code tim employee bang code
    Employee findEmployeeByCode(String code);

    //NhiVP code tim employee da co account
    List<String> findAllEmployeeHasAccount();

    //NhiVP code lấy danh sách mã nhân viên chưa có tài khoản
    List<String> findAllEmployeeDontHasAccount();

    //NhiVP code lấy danh sách số điện thoại
    List<String> findAllPhone();

    Optional<Employee> findById(Long id);

    List<Employee> getAllEmployeeWithPagination(int index);

    List<Employee> searchEmployeeByName(String name);

    List<Employee> getAllEmployee();

    public Map<String, String> validateEmployee(BindingResult bindingResult);

    public Optional<Employee> findEmployeeByAccountId(Long accountId);

    void adminUpdateEmployee(String employeeName, String employeeCode, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Double employeeSalary, Long employeePositionId, Long employeeId);

    void saveEmployee(String employeeCode, String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Double employeeSalary, Long employeePositionId);

    List<Position> getAllPosition();

    Page<Employee> findAllEmployee(Pageable page);

    void deleteEmployeeById(long l, boolean b, Long id);
}
