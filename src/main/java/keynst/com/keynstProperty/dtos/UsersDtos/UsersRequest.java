package keynst.com.keynstProperty.dtos.UsersDtos;

import keynst.com.keynstProperty.models.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter
public class UsersRequest {
    private String firstName;
    private String lastName;
    private String username;
    private Gender gender;
    private String dob;
    private List<String> phone;
    private String email;
    private String password;

}
