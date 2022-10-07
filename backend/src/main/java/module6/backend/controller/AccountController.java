package module6.backend.controller;


import module6.backend.entity.ClassDTO.Password;
import module6.backend.entity.account.Account;
import module6.backend.service.IAccountRoleService;
import module6.backend.service.IAccountService;
import module6.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
}
