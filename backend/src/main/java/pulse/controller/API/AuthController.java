package pulse.controller.API;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import pulse.payload.response.MessageResponse;
import pulse.repos.UserRepo;
import pulse.security.AuthProviderImpl;
import pulse.security.JwtUtils;
import pulse.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
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
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getPassword());

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
