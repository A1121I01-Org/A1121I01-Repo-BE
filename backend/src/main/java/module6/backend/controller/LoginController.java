package module6.backend.controller;

import module6.backend.payload.request.LoginRequest;
import module6.backend.payload.response.JwtResponse;
import module6.backend.security.jwt.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
//@RequestMapping("auth/login")
public class LoginController {
	@Autowired
	private JwtServiceImpl jwtService;

	@PostMapping({"/auth/login"})
	public JwtResponse createJwtToken(@RequestBody LoginRequest loginRequest) throws Exception {
		return jwtService.createJwtToken(loginRequest);
	}
}
