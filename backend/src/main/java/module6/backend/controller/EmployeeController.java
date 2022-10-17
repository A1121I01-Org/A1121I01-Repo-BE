package module6.backend.controller;

import module6.backend.entity.employee.Employee;
import module6.backend.entity.employee.Position;
import module6.backend.service.IAccountService;
import module6.backend.service.IEmployeeService;
import module6.backend.service.IPositionService;
import org.apache.tomcat.util.net.jsse.PEMFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.List;

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

    //    AnDVH cập nhật nhân viên
    @PatchMapping("update")
    public ResponseEntity<?> adminUpdateEmployee(@PathVariable("id") Long id,@RequestBody @Valid Employee employee,BindingResult bindingResult) {
        System.out.println(employee.getEmployeeCode());
        employeeService.adminUpdateEmployee(employee.getEmployeeName(),employee.getEmployeeCode(), employee.getEmployeeAvatar(), employee.getEmployeeDateOfBirth(), employee.getEmployeeGender(), employee.getEmployeeAddress(), employee.getEmployeePhone(), employee.getEmployeeSalary(),employee.getEmployeePositionId().getPositionId() ,id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult) {
        //tạo mới nhân viên
        try {
            System.out.println(employee.getEmployeeCode());
            employeeService.saveEmployee(employee.getEmployeeCode(), employee.getEmployeeName(), employee.getEmployeeAvatar(), employee.getEmployeeDateOfBirth(), employee.getEmployeeGender(), employee.getEmployeeAddress(), employee.getEmployeePhone(), employee.getEmployeeSalary(), employee.getEmployeePositionId().getPositionId());
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(employee,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("position/list")
    public ResponseEntity<List<Position>> getAllPosition() {
        List<Position> positions = employeeService.getAllPosition();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @GetMapping ("/getById/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long id){
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

}
