package module6.backend.repository;

import module6.backend.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    // Thắng code lấy list admin người thực hiện
    @Query(value = "SELECT * FROM employee where (employee_position_id = 1 or employee_position_id = 3) and employee_account_id > 0 and employee_position_id > 0 and employee_id > 0;", nativeQuery = true)
    List<Employee> findAllEmployeeImport();
}
