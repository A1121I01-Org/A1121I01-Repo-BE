package module6.backend.repository;

import module6.backend.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "select * from employee where employee_id = ?1 and employee_position_id > 0 and employee_account_id > 0 and employee_flag = 0 ", nativeQuery = true)
    Optional<Employee> findEmployeeById(Long id);
}
