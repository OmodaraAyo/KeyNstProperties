package keynst.com.keynstProperty.dtos.LoginDtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private String response;
    private Boolean isLoggedIn;
}
