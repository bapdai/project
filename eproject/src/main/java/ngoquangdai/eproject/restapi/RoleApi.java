package ngoquangdai.eproject.restapi;

import ngoquangdai.eproject.entity.Role;
import ngoquangdai.eproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project/role")
public class RoleApi {
    // CURD
    @Autowired
    RoleService roleService;
    //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getList(){
        return ResponseEntity.ok(roleService.findAll());
    }
    //
    @RequestMapping(method = RequestMethod.GET, path = "{role_Id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer role_Id) {
        Optional<Role> optionalRole = roleService.findById(role_Id);
        if (!optionalRole.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalRole.get());
    }
    //
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.save(role));
    }

    //    Sua thong tin(U)
    @RequestMapping(method = RequestMethod.PUT, path = "{role_Id}")
    public ResponseEntity<Role> update(@PathVariable Integer role_Id, @RequestBody Role role) {
        Optional<Role> optionalRole = roleService.findById(role_Id);
        if (!optionalRole.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Role exitsRole = optionalRole.get();
        //     map object
        exitsRole.setRoleId(role.getRoleId());
        exitsRole.setRoleName(role.getRoleName());
        return ResponseEntity.ok(roleService.save(exitsRole));
    }
    //        Xoa thong tin
    @RequestMapping(method = RequestMethod.DELETE, path = "{role_Id}")
    public ResponseEntity<?> delete(@PathVariable Integer role_Id) {
        if (!roleService.findById(role_Id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        roleService.deleteById(role_Id);
        return ResponseEntity.ok().build();
    }
}
