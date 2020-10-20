package pulse.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pulse.domain.User;
import pulse.repos.UserRepo;

import java.util.List;

@Service
public class EditDeleteUserService {

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<?> findAll() {
        List<User> users= userRepo.findAll();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id) {
      if (userRepo.existsById(id)){
          userRepo.deleteById(id);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }else {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }


    public ResponseEntity<?> update(Long id, User user) {
        if (userRepo.existsById(id)) {
            User userinDB=userRepo.findById(id).get();
            userinDB.setRoles(user.getRoles());
            userinDB.setUsername(user.getUsername());
            userRepo.save(userinDB);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
