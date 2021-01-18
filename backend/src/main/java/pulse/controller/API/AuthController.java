package pulse.controller.API;

import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pulse.domain.Role;
import pulse.domain.User;
import pulse.payload.request.LoginRequest;
import pulse.payload.request.SignupRequest;
import pulse.payload.response.JwtResponse;
import pulse.domain.repos.UserRepo;
import pulse.service.security.AuthProviderImpl;
import pulse.service.security.JwtUtils;
import pulse.service.UserService;

import java.util.Collections;
import java.util.Set;


@CrossOrigin(origins = "https://pacific-fjord-28473.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepo userRepository;

    @Autowired
    private AuthProviderImpl authProvider;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        JSONObject resp = new JSONObject();
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            resp.put("msg", "User already exists");
            resp.put("success", false);
            return ResponseEntity.ok(resp.toString());
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),signUpRequest.getFirst_name(),signUpRequest.getLast_name(),signUpRequest.getMiddle_name(),signUpRequest.getBirth_date(),
                signUpRequest.getAddress(),signUpRequest.getFaculty(),signUpRequest.getSpeciality(),signUpRequest.getCourse(),signUpRequest.getPassword());

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.addUser(user);

        resp.put("success", true);
        resp.put("username", signUpRequest.getUsername());
        return ResponseEntity.ok(resp.toString());
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
        JSONObject resp = new JSONObject();
        Authentication authentication = authProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        resp.put("msg", "User already exists");
        resp.put("success", true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
        Set<Role> roles=user.getRoles();

        return ResponseEntity.ok(new JwtResponse(jwt,resp,
                user.getId(),
                user.getUsername(),
                roles));
    }
}
