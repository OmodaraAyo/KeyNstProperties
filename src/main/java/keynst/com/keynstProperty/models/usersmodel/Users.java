package keynst.com.keynstProperty.models.usersmodel;
import jakarta.persistence.*;
import keynst.com.keynstProperty.models.Tenant.Tenant;
import keynst.com.keynstProperty.models.enums.Gender;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private Gender gender;
    private String dob;
    @ElementCollection
    @CollectionTable(name = "user_phones", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "phone")
    private List<String> phone;
    private String email;
    private String password;
    private String dateCreated;
    private String dateModified;
    private boolean isLoggedIn;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tenant> tenants = new ArrayList<>();
}
