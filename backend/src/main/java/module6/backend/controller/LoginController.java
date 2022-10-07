package module6.backend.controller;

import module6.backend.payload.request.LoginRequest;
import module6.backend.payload.response.JwtResponse;
import module6.backend.service.Impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/login")
public class LoginController {
	@Autowired
	private JwtServiceImpl jwtService;

	@PostMapping({"/authenticate"})
	public JwtResponse createJwtToken(@RequestBody LoginRequest loginRequest) throws Exception {
		return jwtService.createJwtToken(loginRequest);
	}
}
