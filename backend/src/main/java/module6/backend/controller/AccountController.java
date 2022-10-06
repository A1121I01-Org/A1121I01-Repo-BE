package module6.backend.controller;


import module6.backend.entity.account.Account;
import module6.backend.service.IAccountRoleService;
import module6.backend.service.IAccountService;
import module6.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


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

}
