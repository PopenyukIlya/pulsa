package pulse.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pulse.controller.dto.UserRoleDto;
import pulse.domain.User;
import pulse.service.admin.EditDeleteUserService;


@CrossOrigin(origins = "https://pacific-fjord-28473.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/useredit")
@PreAuthorize("hasAuthority('ADMIN')")
public class EditUserController {

    @Autowired
    private EditDeleteUserService editDeleteUserService;

    @GetMapping
    public ResponseEntity<?> findAll(@AuthenticationPrincipal User user) {
        return editDeleteUserService.findAll(user);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody UserRoleDto userRoleDto){
        return new ResponseEntity<>(userRoleDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody UserRoleDto userRoleDto){
        return editDeleteUserService.update(userRoleDto);
    }

}
