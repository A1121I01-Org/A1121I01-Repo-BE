package module6.backend.repository;

import module6.backend.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {
    @Query(value = "select * from employee where employee_id = ?1 and employee_position_id > 0 and employee_account_id > 0 and employee_flag = 0 ", nativeQuery = true)
    Optional<Employee> findEmployeeById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE employee SET employee_name = ?1, employee_avatar = ?2, employee_date_of_birth= ?3, employee_gender = ?4, employee_address = ?5, employee_phone = ?6 WHERE employee_id = ?7", nativeQuery = true)
    void updateEmployee(String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Long employeeId);


    @Query(value = "SELECT * from employee left join position on position.position_id = employee.employee_position_id where employee.employee_flag = 0 group by employee.employee_id limit ?1,5", nativeQuery = true)
    List<Employee> getAllEmployeeWithPagination(int index);

    @Query(value = "UPDATE employee SET employee_flag = 1 , employee_id = ?1  WHERE (employee_id = ?2)", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteEmployeeById(Long id1, Long id2);

    @Query(value = "select * from employee where employee_position_id  > 0 and employee_flag = 0", nativeQuery = true)
    List<Employee> getAllEmployee();

    @Query(value = "select * from employee where employee_name like %:name%", nativeQuery = true)
    List<Employee> searchEmployeeByName(@Param("name") String name);

}
