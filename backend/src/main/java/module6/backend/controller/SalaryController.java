package module6.backend.controller;

import module6.backend.service.IEmployeeService;
import module6.backend.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/salary")
public class SalaryController {
    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/luongNV")
    public ResponseEntity<Integer> luongNV() {
        Integer luong = salaryService.displayBan();
        return new ResponseEntity<>(luong, HttpStatus.OK);
    }
}
