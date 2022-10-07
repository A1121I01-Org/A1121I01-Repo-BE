package module6.backend.controller;


import module6.backend.entity.ClassDTO.Password;
import module6.backend.entity.account.Account;
import module6.backend.entity.account.Role;
import module6.backend.entity.employee.Employee;
import module6.backend.repository.IRoleRepository;
import module6.backend.service.IAccountRoleService;
import module6.backend.service.IAccountService;
import module6.backend.service.IEmployeeService;
import module6.backend.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountRoleService accountRoleService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PatchMapping("update/password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable("id") Long id, @RequestBody Password password) {
        Account account = accountService.findAccountById(id);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            String originalPasswordEncode = account.getPassword();
            boolean checkPassword = passwordEncoder.matches(password.getOldPassword(),originalPasswordEncode);
            if(checkPassword) {
                if(!password.getNewPassword().equals(password.getConfirmPassword())) {
                    return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
                }else {
                    String newPassWordEncode = new BCryptPasswordEncoder().encode(password.getNewPassword());
                    accountService.updatePassword(newPassWordEncode, id);

                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }else {
                return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
            }
        }
    }

    @Autowired


//    @PostConstruct
//    public void initRolesAndAccount(){
//        accountService.initRoleAndAccount();
//    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<Account> findAccountByUsername(@PathVariable("username") String username){
        Account account = accountService.findAccountByUsername(username);
        if (account!=null)
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-Account/{code}")
    public ResponseEntity<?> createAccount(@RequestBody(required = false) Account account, @PathVariable String code, BindingResult bindingResult){
        if (employeeService.findExistEmployeeHasAccount(code)!=null){
            bindingResult.rejectValue("username","Mã nhân viên đã tồn tại và đã có tài khoản");
            System.out.println("Mã nhân viên đã tồn tại và đã có tài khoản");
        }else if (accountService.existAccountByUsername(account.getUsername())){
            bindingResult.rejectValue("username", "Tên tài khoản đã tồn tại");
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        }else {
            accountService.createAccountForExistEmployee(account,code);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/create-EmployeeAndAccount")
    public ResponseEntity<?> createNewEmployeeAndAccount(@RequestBody Employee employee){
        return new ResponseEntity<>(accountService.createEmployeeAndAccount(employee),HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getEmployee(@PathVariable String code){
        Employee employee = employeeService.findEmployeeByCode(code);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }


    @PostMapping("/create-Account/{username}/{password}/{positionId}/{code}")
    public ResponseEntity<?> createTest(@RequestBody Employee employee,@PathVariable String username,@PathVariable String password,
                                        @PathVariable Long positionId,@PathVariable String code,BindingResult bindingResult){
//        Employee employee1 = employeeService.findEmployeeByCode(code);
//        if (employee1!=null){
//            accountService.createTest(employee1,username,password,roleId,code);
//        }
//        else accountService.createTest(employee,username,password,roleId,code);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);

        if (employeeService.findExistEmployeeHasAccount(code)!=null){
            bindingResult.rejectValue("employeeCode","Mã nhân viên đã tồn tại và đã có tài khoản");
            System.out.println("Mã nhân viên đã tồn tại và đã có tài khoản");
        }else if (accountService.existAccountByUsername(username)){
            bindingResult.rejectValue("employeeCode", "Tên tài khoản đã tồn tại");
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        }else {
            if (employeeService.findExistEmployeeDontHasAccount(code)!=null){
                Employee employee1 = employeeService.findEmployeeByCode(code);
                accountService.createTest(employee1,username,password,positionId,code);
            }else {
                accountService.createTest(employee,username,password,positionId,code);
            }
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
