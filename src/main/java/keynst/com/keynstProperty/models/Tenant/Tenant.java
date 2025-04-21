package keynst.com.keynstProperty.models.Tenant;

import jakarta.persistence.*;
import keynst.com.keynstProperty.models.enums.Gender;
import keynst.com.keynstProperty.models.enums.JobTitle;
import keynst.com.keynstProperty.models.enums.Salary;
import keynst.com.keynstProperty.models.usersmodel.Users;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tenant")
@Data
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenantId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String currentAddress;
    private String nationalId;
    private boolean isSearchingForHome;
    private JobTitle jobTitle;
    private String currentEmployer;
    private Salary income;
    private int employmentLength;
    private boolean hasPet;
    @ElementCollection
    @CollectionTable(name = "tenant_phone_number", joinColumns = @JoinColumn(name = "tenant_tenant_id"))
    @Column(name = "phone_number")
    private List<String> phoneNumber;
    private String email;
    private String preferredContact;
    private boolean isAcknowledged;
    private boolean isActive;
    private String dateCreated;
    private String dateModified;
}
