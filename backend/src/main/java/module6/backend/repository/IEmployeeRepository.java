package module6.backend.repository;

import module6.backend.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "select * from employee where employee_id = ?1 and employee_position_id > 0 and employee_account_id > 0 and employee_flag = 0 ", nativeQuery = true)
    Optional<Employee> findEmployeeById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE employee SET employee_name = ?1, employee_avatar = ?2, employee_date_of_birth= ?3, employee_gender = ?4, employee_address = ?5, employee_phone = ?6 WHERE employee_id = ?7", nativeQuery = true)
    void updateEmployee(String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Long employeeId);

    //NhiVP code tim employee bang code
    @Query(value = "SELECT * from employee where employee_code =?1 and employee_flag = 0", nativeQuery = true)
    Employee findEmployeeByCode(String code);

    //NhiVP code tim employee da co account
    @Query(value = "select * from employee where employee_code =?1 and employee_account_id is not null and employee_flag = 0", nativeQuery = true)
    Employee findExistEmployeeHasAccount(String code);

    //NhiVP code tim employee chua co account
    @Query(value = "select * from employee where employee_code =?1 and employee_account_id is null and employee_flag = 0", nativeQuery = true)
    Employee findExistEmployeeDontHasAccount(String code);

    //NhiVP code lấy danh sách mã nhân viên đã có tài khoản
    @Query(value = "select employee_code from employee where employee_code is not null and employee_account_id is not null and employee_flag = 0", nativeQuery = true)
    List<String> findAllEmployeeHasAccount();
    @Query(value = "select employee_code from employee where employee_code is not null and employee_account_id is null and employee_flag = 0", nativeQuery = true)
    List<String> findAllEmployeeDontHasAccount();
}
