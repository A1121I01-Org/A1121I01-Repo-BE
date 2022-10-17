package module6.backend.repository;

import module6.backend.entity.employee.Employee;
import module6.backend.entity.employee.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into `employee` (`employee_code`,+ `employee_name`, + `employee_avatar`,+ `employee_date_of_birth`,+`employee_gender`,+` employee_address`,+ `employee_phone`,+`employee_salary`,+` employee_position_id)` +values(?1,?2,?3,?4,?5,?6,?7,?8,?9)" , nativeQuery = true)
    void saveEmployee(String employeeCode, String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress , String employeePhone, Double employeeSalary , Long employeePositionId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE `employee` SET `employee_name` = ?1,`employee_code` = ?2 , `employee_avatar` = ?3, `employee_date_of_birth`= ?4, `employee_gender` = ?5, `employee_address` = ?6, `employee_phone` = ?7 , `employee_salary` = ?8  , `employee_position` = ?9 WHERE `employee_id` = ?10", nativeQuery = true)
    void adminUpdateEmployee(String employeeName, String employeeCode, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone,Double employeeSalary,Long employeePositionId, Long employeeId );
}
