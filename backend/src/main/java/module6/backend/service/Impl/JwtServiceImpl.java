package module6.backend.service.Impl;

import module6.backend.entity.account.Account;
import module6.backend.jwt.JwtUtils;
import module6.backend.payload.request.LoginRequest;
import module6.backend.payload.response.JwtResponse;
import module6.backend.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtServiceImpl implements UserDetailsService {
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private IAccountRepository accountRepository;
	@Autowired
	private AuthenticationManager authenticationManager;

	private void authenticate(String userName, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	public JwtResponse createJwtToken(LoginRequest loginRequest) throws Exception {
		String userName = loginRequest.getUserName();
		String userPassword = loginRequest.getUserPassword();
		authenticate(userName, userPassword);

		UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtils.generateToken(userDetails);

		Account account = accountRepository.findAccountByUsername(userName);
		return new JwtResponse(account, newGeneratedToken);
	}
	private Set getAuthority(Account account) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		account.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findAccountByUsername(username);

		if (account != null) {
			return new org.springframework.security.core.userdetails.User(
					account.getUsername(),
					account.getPassword(),
					getAuthority(account)
			);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
