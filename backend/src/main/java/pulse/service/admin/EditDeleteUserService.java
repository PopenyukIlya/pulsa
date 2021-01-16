package pulse.service.admin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pulse.controller.dto.UserRoleDto;
import pulse.domain.Role;
import pulse.domain.User;
import pulse.domain.repos.UserRepo;

import java.util.List;

@Service
public class EditDeleteUserService {

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<?> findAll(User user) {
        String name=user.getFirst_name();
        List<User> users= userRepo.findAll();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUser(Long id){

        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<?> update(UserRoleDto users) {
       boolean isInDb=validateUsers(users.getChecked());
       if (isInDb){
           updateRole(users);
           List<User> updatedUsers= userRepo.findAll();
           return new ResponseEntity<>(updatedUsers,HttpStatus.OK);
       }else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    private boolean validateUsers(List<Long> checked) {
       boolean q=true;
        for (int i=0;i<checked.size();i++){
         if (!userRepo.existsById(checked.get(i))){
             q=false;
         }
        }
        return q;
    }

    public void updateRole(UserRoleDto userRoleDto){
        for (int i=0;i<userRoleDto.getChecked().size();i++){
            User user=userRepo.findById(userRoleDto.getChecked().get(i)).get();
            if (userRoleDto.getNewrole().equals("ADMIN")){
                user.getRoles().remove(Role.USER);
                user.getRoles().add(Role.ADMIN);
            }else {
                user.getRoles().remove(Role.ADMIN);
                user.getRoles().add(Role.USER);
            }
            userRepo.save(user);
        }
    }
}
