package keynst.com.keynstProperty.dtos.UsersDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import keynst.com.keynstProperty.models.enums.Gender;
import lombok.Data;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UsersResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private Gender gender;
    private String dob;
    private List<String> phone;
    private String email;
    private String password;
    private String dateCreated;
    private String dateModified;
    private boolean isLoggedIn;

}
