package ngoquangdai.eproject.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer userId;

    private String userName;

    private String password;

//    @Column(name = "enabled")
//    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String name, String password) {
        this.setUserName(name);
        this.setPassword(password);
    }
}
