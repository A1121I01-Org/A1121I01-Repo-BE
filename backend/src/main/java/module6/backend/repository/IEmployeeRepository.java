package module6.backend.repository;

import module6.backend.entity.Import;
import module6.backend.entity.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {

    @Query(value = "select * from employee where employee_id > 0 and employee_position_id > 0 and employee_account_id > 0 and employee_flag = 0", nativeQuery = true)
    Page<Employee> findAllEmployee(Pageable pageable);

    @Query(value = "select * from employee where employee_id = ?1 and employee_position_id > 0 and employee_account_id > 0 and employee_flag = 0 ", nativeQuery = true)
    Optional<Employee> findEmployeeById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE employee SET employee_name = ?1, employee_avatar = ?2, employee_date_of_birth= ?3, employee_gender = ?4, employee_address = ?5, employee_phone = ?6 WHERE employee_id = ?7", nativeQuery = true)
    void updateEmployee(String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Long employeeId);

    //NhiVP code tim employee bang code
    @Query(value = "SELECT * from employee where employee_code =?1 and employee_flag = 0", nativeQuery = true)
    Employee findEmployeeByCode(String code);

    //NhiVP code tim employee chua co account
    @Query(value = "select * from employee where employee_code =?1 and employee_account_id is null and employee_flag = 0", nativeQuery = true)
    Employee findExistEmployeeDontHasAccount(String code);

    //NhiVP code lấy danh sách mã nhân viên đã có tài khoản
    @Query(value = "select employee_code from employee where employee_code is not null and employee_account_id is not null and employee_flag = 0", nativeQuery = true)
    List<String> findAllEmployeeHasAccount();

    //NhiVP code lấy danh sách mã nhân viên chưa có tài khoản
    @Query(value = "select employee_code from employee where employee_code is not null and employee_account_id is null and employee_flag = 0", nativeQuery = true)
    List<String> findAllEmployeeDontHasAccount();

    //NhiVP code lấy danh sách số điện thoại
    @Query(value = "select employee_phone from employee where employee_flag = 0", nativeQuery = true)
    List<String> findAllPhone();

    // Thắng code lấy list admin người thực hiện
    @Query(value = "SELECT * FROM employee where (employee_position_id = 1 or employee_position_id = 3) and employee_account_id > 0 and employee_position_id > 0 and employee_id > 0;", nativeQuery = true)
    List<Employee> findAllEmployeeImport();

    @Query(value = "SELECT * from employee left join position on position.position_id = employee.employee_position_id where employee.employee_flag = 0 group by employee.employee_id limit ?1,5", nativeQuery = true)
    List<Employee> getAllEmployeeWithPagination(int index);


    @Query(value = "UPDATE employee SET employee_id = ?1, employee_flag = ?2  WHERE (`employee_id` = ?3)", nativeQuery = true)
    @javax.transaction.Transactional
    @Modifying
    void deleteEmployeeById(Long idAfterUpdate, Boolean flag, Long idBeforeUpdate);

    @Query(value = "select * from employee where employee_position_id  > 0 and employee_flag = 0", nativeQuery = true)
    List<Employee> getAllEmployee();

    @Query(value = "select * from employee where employee_name like %:name%", nativeQuery = true)
    List<Employee> searchEmployeeByName(@Param("name") String name);

    //AnDVH get employee by accountId
    @Query(value = "SELECT * FROM employee WHERE employee_account_id =:accountId AND employee_flag = 0", nativeQuery = true)
    Optional<Employee> findEmployeeByAccountId(@Param("accountId") Long accountId);

    @Transactional
    @Modifying
    @Query(value = "insert into `employee` (`employee_code`, `employee_name`,  `employee_avatar`, `employee_date_of_birth`,`employee_gender`,`employee_address`, `employee_phone`,`employee_salary`,`employee_position_id`) values(?1,?2,?3,?4,?5,?6,?7,?8,?9)", nativeQuery = true)
    void saveEmployee(String employeeCode, String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Double employeeSalary, Long employeePositionId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `employee` SET `employee_code` = ?1,`employee_name` = ?2 , `employee_avatar` = ?3, `employee_date_of_birth`= ?4, `employee_gender` = ?5, `employee_address` = ?6, `employee_phone` = ?7 , `employee_salary` = ?8  , `employee_position_id` = ?9 WHERE (`employee_id` = ?10);", nativeQuery = true)
    void adminUpdateEmployee(String employeeCode, String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Double employeeSalary, Long employeePositionId, Long employeeId);
}