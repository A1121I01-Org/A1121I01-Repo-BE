package module6.backend.repository;

import module6.backend.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    //NhiVP code tim employee bang code
    @Query(value = "SELECT * from employee where employee_code =?1 and employee_flag = 0", nativeQuery = true)
    Employee findEmployeeByCode(String code);

    //NhiVP code tim employee da co account
    @Query(value = "select * from employee where employee_code =?1 and employee_account_id is not null and employee_flag = 0", nativeQuery = true)
    Employee findExistEmployeeHasAccount(String code);

    //NhiVP code tim employee chua co account
    @Query(value = "select * from employee where employee_code =?1 and employee_account_id is null and employee_flag = 0", nativeQuery = true)
    Employee findExistEmployeeDontHasAccount(String code);
}
