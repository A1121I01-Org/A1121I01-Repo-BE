package module6.backend.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import module6.backend.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * from employee left join position on position.position_id = employee.employee_position_id where employee.employee_flag = 0 group by employee.employee_id limit ?1,5", nativeQuery = true)
    List<Employee> getAllEmployeeWithPagination(int index);

    @Query(value = "UPDATE employee SET employee_flag = 1 , employee_id = ?1  WHERE (employee_id = ?2)", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteEmployeeById(Long id1, Long id2);

    @Query(value = "select * from employee where employee_id = ?1 and employee_position_id > 0 and employee_flag = 0 ", nativeQuery = true)
    Optional<Employee> findEmployeeById(Long id);

    @Query(value = "select * from employee where employee_position_id  > 0 and employee_flag = 0", nativeQuery = true)
    List<Employee> getAllEmployee();
}
