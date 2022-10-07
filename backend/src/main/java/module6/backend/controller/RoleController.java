package module6.backend.controller;

import module6.backend.entity.account.Role;
import module6.backend.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping("/list")
    public ResponseEntity<List<Role>> findAllRole(){
        List<Role> roleList = roleService.findAllRole();
        if (roleList.isEmpty()){
            return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Role>>(roleList, HttpStatus.OK);
    }
}
