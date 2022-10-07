package module6.backend.controller;

import module6.backend.entity.employee.Employee;
import module6.backend.service.IAccountService;
import module6.backend.service.IEmployeeService;
import module6.backend.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IPositionService positionService;

    @GetMapping("detail/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        if(employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }

        Employee foundEmployee = employeeService.findEmployeeById(id);
        if(foundEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            employeeService.updateEmployee(employee.getEmployeeName(),employee.getEmployeeAvatar(),employee.getEmployeeDateOfBirth(),employee.getEmployeeGender(),employee.getEmployeeAddress(),employee.getEmployeePhone(),id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
