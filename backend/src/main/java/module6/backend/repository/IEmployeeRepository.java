package module6.backend.repository;

import module6.backend.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM employee WHERE employee_id = ?1", nativeQuery = true)
    Employee findEmployeeById(Long employeeId);

    @Query(value = "UPDATE employee SET employee_name = ?1, employee_avatar = ?2, employee_date_of_birth= ?3, employee_gender = ?4, employee_address = ?5, employee_phone = ?6 WHERE employee_id = ?7", nativeQuery = true)
    void updateEmployee(String employeeName, String employeeAvatar, LocalDate employeeDateOfBirth, String employeeGender, String employeeAddress, String employeePhone, Long employeeId);
}
