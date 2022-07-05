package ngoquangdai.eproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    private Integer roleId;

    private String roleName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Collection<User> listUser;
}
