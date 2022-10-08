package module6.backend.controller;


import module6.backend.entity.account.Account;
import module6.backend.entity.employee.Employee;
import module6.backend.entity.employee.Position;
import module6.backend.service.IAccountService;
import module6.backend.service.IEmployeeService;
import module6.backend.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


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


    //  SonLH  Tìm kiếm nhân viên theo id
    @GetMapping("/detail/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
        Optional<Employee> employeeOptional = employeeService.findEmployeeById(id);
        if (!employeeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);
    }

    //  SonLH  lấy danh sách account
    @GetMapping("/account")
    public ResponseEntity<List<Account>> findAllAccount() {
        List<Account> accounts = accountService.findAllAccount();
        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    //  SonLH  lấy danh sách chức vụ nhân viên
    @GetMapping("/position")
    public ResponseEntity<List<Position>> findAllPosition() {
        List<Position> positions = positionService.findAllPosition();
        if (positions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    //    AnDVH cập nhật nhân viên
    @PatchMapping("update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }

        Optional<Employee> foundEmployee = employeeService.findEmployeeById(id);
        if (!foundEmployee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            employeeService.updateEmployee(employee.getEmployeeName(), employee.getEmployeeAvatar(), employee.getEmployeeDateOfBirth(), employee.getEmployeeGender(), employee.getEmployeeAddress(), employee.getEmployeePhone(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
