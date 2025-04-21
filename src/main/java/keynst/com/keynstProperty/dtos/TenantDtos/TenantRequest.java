package keynst.com.keynstProperty.dtos.TenantDtos;

import keynst.com.keynstProperty.models.enums.Gender;
import keynst.com.keynstProperty.models.enums.JobTitle;
import keynst.com.keynstProperty.models.enums.Salary;
import lombok.Data;

import java.util.List;
@Data
public class TenantRequest {
    private Long userId;
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
    private List<String> phoneNumber;
    private String email;
    private String preferredContact;
    private boolean isAcknowledged;
}
