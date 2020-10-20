package pulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pulse.domain.User;
import pulse.service.admin.EditDeleteUserService;

import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/useredit")
@PreAuthorize("hasAuthority('ADMIN')")
public class EditUserController {

    @Autowired
    private EditDeleteUserService editDeleteUserService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return editDeleteUserService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable ("id") Long id){
        return editDeleteUserService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable ("id") Long id,
                                    @RequestBody @Valid User user){

       return editDeleteUserService.update(id,user);
    }

}