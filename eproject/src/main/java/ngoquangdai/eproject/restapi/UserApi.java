package ngoquangdai.eproject.restapi;

import ngoquangdai.eproject.entity.Role;
import ngoquangdai.eproject.entity.User;
import ngoquangdai.eproject.service.RoleService;
import ngoquangdai.eproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project/user")
public class UserApi {
    // CURD
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getList(){
        return ResponseEntity.ok(userService.findAll());
    }
    //
    @RequestMapping(method = RequestMethod.GET, path = "{user_Id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer user_Id) {
        Optional<User> optionalUser = userService.findById(user_Id);
        if (!optionalUser.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalUser.get());
    }
    //
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody String userName, @RequestBody String password, @RequestBody int role) {
        User user1 = new User(userName, password);
        Role role1 = roleService.findById(role).get();
        role1.setListUser(Collections.singleton(user1));
        roleService.save(role1);
        return ResponseEntity.ok(user1);
    }

    //    Sua thong tin(U)
    @RequestMapping(method = RequestMethod.PUT, path = "{user_Id}")
    public ResponseEntity<User> update(@PathVariable Integer user_Id, @RequestBody User user) {
        Optional<User> optionalUser = userService.findById(user_Id);
        if (!optionalUser.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        User exitsUser = optionalUser.get();
        //     map object
        exitsUser.setUserId(user.getUserId());
        exitsUser.setUserName(user.getUserName());
        exitsUser.setPassword(user.getPassword());
//        exitsUser.setEnabled(user.getEnabled());
        exitsUser.setRole(user.getRole());
        return ResponseEntity.ok(userService.save(exitsUser));
    }
    //        Xoa thong tin
    @RequestMapping(method = RequestMethod.DELETE, path = "{user_Id}")
    public ResponseEntity<?> delete(@PathVariable Integer user_Id) {
        if (!userService.findById(user_Id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        userService.deleteById(user_Id);
        return ResponseEntity.ok().build();
    }
}
