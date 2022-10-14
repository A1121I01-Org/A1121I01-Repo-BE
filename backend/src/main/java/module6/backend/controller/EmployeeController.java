package module6.backend.controller;

import module6.backend.entity.employee.Employee;
import module6.backend.entity.employee.Position;
import module6.backend.service.IAccountService;
import module6.backend.service.IEmployeeService;
import module6.backend.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeList = this.employeeService.getAllEmployee();
        if (employeeList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/employee-pagination/{index}")
    public ResponseEntity<Iterable<Employee>> getAllEmployeeWithPagination(@PathVariable("index") int index) {
        List<Employee> employees = (List<Employee>) employeeService.getAllEmployeeWithPagination(index);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping("/employee-delete/{id}")
    public ResponseEntity<Employee> deleteCustomerById(@PathVariable("id") Long id) {
        Optional<Employee> employeeOptional = employeeService.findEmployeeById(id);
        if (!employeeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployeeById(-id, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IPositionService positionService;

    @GetMapping("/position")
    public ResponseEntity<Iterable<Position>> findAllEmployeePosition() {
        List<Position> positionList = positionService.findAllEmployeePosition();
        if (positionList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

}
