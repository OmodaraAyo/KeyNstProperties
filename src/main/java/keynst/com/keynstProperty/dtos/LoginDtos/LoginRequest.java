package keynst.com.keynstProperty.dtos.LoginDtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String email;
    private String password;
}
