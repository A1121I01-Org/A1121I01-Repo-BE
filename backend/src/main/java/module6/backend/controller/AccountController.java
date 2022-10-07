package module6.backend.controller;


import module6.backend.entity.account.Account;
import module6.backend.entity.employee.Employee;
import module6.backend.service.IAccountRoleService;
import module6.backend.service.IAccountService;
import module6.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountRoleService accountRoleService;
    @Autowired
    private IEmployeeService employeeService;

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

//    @GetMapping("/{code}")
//    public ResponseEntity<?> getEmployee(@PathVariable String code){
//        Employee employee = employeeService.findEmployeeByCode(code);
//        return new ResponseEntity<>(employee,HttpStatus.OK);
//    }

}
