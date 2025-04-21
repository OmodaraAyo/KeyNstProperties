package keynst.com.keynstProperty.dtos.TenantDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import keynst.com.keynstProperty.models.enums.Gender;
import keynst.com.keynstProperty.models.enums.JobTitle;
import keynst.com.keynstProperty.models.enums.Salary;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TenantResponse {
    private Long userId;
    private Long tenantId;
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
    private boolean isActive;
    private String dateCreated;
    private String dateModified;
}
