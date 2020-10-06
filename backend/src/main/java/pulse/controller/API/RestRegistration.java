package pulse.controller.API;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pulse.domain.Role;
import pulse.domain.User;
import pulse.payload.request.SignupRequest;
import pulse.payload.response.MessageResponse;
import pulse.repos.UserRepo;
import pulse.service.UserService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class RestRegistration {

    @Autowired
    UserRepo userRepository;

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
}
