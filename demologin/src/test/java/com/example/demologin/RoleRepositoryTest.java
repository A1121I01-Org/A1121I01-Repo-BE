package com.example.demologin;

import com.example.demologin.entity.Account;
import com.example.demologin.entity.Role;
import com.example.demologin.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository repo;

    @Test
    void createRoleTest() {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        List<Role> roles = new ArrayList<>();
        roles.add(admin);
        roles.add(user);


        repo.saveAll(roles);

        long numberOfRoles = repo.count();
        assertEquals(2,numberOfRoles);
    }

    @Test
    public void getList() {
        List<Role> roleList = repo.findAll();
        assertThat(roleList.size()).isGreaterThan(0);

        roleList.forEach(System.out::println);
    }

}
