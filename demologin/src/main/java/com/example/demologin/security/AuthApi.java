package com.example.demologin.security;

import com.example.demologin.entity.Account;
import com.example.demologin.repository.AccountRepository;
import com.example.demologin.security.utils.JwtServiceImpl;
import com.example.demologin.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthApi {

    @Autowired private JwtServiceImpl service;

    @Autowired AuthenticationManager manager;

    @Autowired JwtTokenUtil jwtTokenUtil;

    @Autowired private AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/auth/login" )
    @PostMapping()
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
                    );
            Account account = (Account) authentication.getPrincipal();

            UserDetails userDetails = service.loadUserByUsername(authRequest.getUsername());
            String accessToken = jwtTokenUtil.generateToken(userDetails);
//            String accessToken = jwtTokenUtil.generateToken(account);
//            AuthResponse authResponse = new AuthResponse(account.getUsername(), accessToken);
            Account account1 = accountRepository.findAccountByUsername(authRequest.getUsername());
            JwtResponse jwtResponse = new JwtResponse(account1,accessToken);

            return ResponseEntity.ok(jwtResponse);

        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
